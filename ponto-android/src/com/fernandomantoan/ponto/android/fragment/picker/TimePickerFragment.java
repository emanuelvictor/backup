package com.fernandomantoan.ponto.android.fragment.picker;

import java.util.Calendar;
import java.util.List;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.TimePicker;

import com.fernandomantoan.ponto.android.activity.Navigation;
import com.fernandomantoan.ponto.android.entity.Daily;
import com.fernandomantoan.ponto.android.entity.Point;
import com.fernandomantoan.ponto.android.repository.PointRepository;
import com.fernandomantoan.ponto.android.support.DatabaseHelper;
import com.fernandomantoan.ponto.android.util.InterfaceFragment;
import com.fernandomantoan.ponto.android.util.PointVariables;

public class TimePickerFragment extends DialogFragment implements
		OnTimeSetListener {

	private boolean cancel = false;
	private Calendar calendar;
	private int minute;
	private int hourOfDay;
	private TimePickerDialog timePickerDialog;
	private int index;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		this.index = getArguments().getInt("index", 100);
		this.calendar = ((PointVariables) ((Navigation) getActivity())
				.getVariables()).getHistoryNavigation().getCurrent();

//		PointRepository repository = new PointRepository(new DatabaseHelper(
//				getActivity()));
//		Daily dailyHandler = new Daily(repository, calendar);

//		List<Point> points = dailyHandler.getPoints();

//		Boolean isEditing = getArguments().getBoolean("isEditing", false);
//
//		if (isEditing) {
//			if (points.get(index).getDate() == null) {
//				this.calendar.set(Calendar.HOUR_OF_DAY, Calendar.getInstance()
//						.get(Calendar.HOUR_OF_DAY));
//				this.calendar.set(Calendar.MINUTE,
//						Calendar.getInstance().get(Calendar.MINUTE));
//			} else {
//				calendar = points.get(index).getDate();
//			}
//		}

		this.hourOfDay = this.calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = this.calendar.get(Calendar.MINUTE);

		this.timePickerDialog = new TimePickerDialog(getActivity(), this,
				hourOfDay, minute, true);

		return this.timePickerDialog;

	}

	@Override
	public void onCancel(DialogInterface dialog) {
		this.cancel = true;
		super.onCancel(dialog);
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		this.hourOfDay = hourOfDay;
		this.minute = minute;

	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		if (!cancel) {
			try {
				PointRepository repository = new PointRepository(
						new DatabaseHelper(getActivity()));
				Daily dailyHandler = new Daily(repository, calendar);

				List<Point> points = dailyHandler.getPoints();

				// Boolean isEditing = getArguments().getBoolean("isEditing",
				// false);

				this.calendar.set(Calendar.MINUTE, minute);
				this.calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);

				Point p = new Point(Calendar.getInstance());
				// if (isEditing) {
				// p.id = points.get(index).id;
				p.setId(points.get(index).getId());
				// }
				p.setDate(this.calendar);
				// p.date = this.calendar;
				this.insert(
						p,
						new PointRepository(new DatabaseHelper(this
								.getActivity())));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		super.onDismiss(dialog);
	}

	public void insert(Point p, PointRepository repository) throws Exception {
		Log.i("tOsTRING", p.toString());
		repository.save(p);
		((InterfaceFragment) this.getActivity()).update();

	}

}
