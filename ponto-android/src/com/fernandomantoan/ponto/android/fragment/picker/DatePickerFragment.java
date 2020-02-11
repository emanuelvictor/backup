package com.fernandomantoan.ponto.android.fragment.picker;

import java.util.Calendar;

import com.fernandomantoan.ponto.android.activity.Navigation;
import com.fernandomantoan.ponto.android.util.InterfaceFragment;
import com.fernandomantoan.ponto.android.util.PointVariables;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.Toast;

public class DatePickerFragment extends DialogFragment implements
		OnDateSetListener {

	private boolean cancel = false;
	private Calendar calendar = Calendar.getInstance();
	private DatePickerDialog datePickerDialog;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		this.datePickerDialog = new DatePickerDialog(getActivity(), this,
				Calendar.getInstance().get(Calendar.YEAR), Calendar
						.getInstance().get(Calendar.MONTH), Calendar
						.getInstance().get(Calendar.DAY_OF_MONTH));
		return this.datePickerDialog;
	}

	@Override
	public void onCancel(DialogInterface dialog) {
		this.cancel = true;
		super.onCancel(dialog);
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		this.calendar.set(Calendar.YEAR, year);
		this.calendar.set(Calendar.MONTH, monthOfYear);
		this.calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		if (!cancel) {
			try {
				((PointVariables) ((Navigation) getActivity()).getVariables())
						.getHistoryNavigation().incrementHistory(this.calendar);
				((InterfaceFragment) getActivity()).update();
				((InterfaceFragment) getActivity()).goTo(0);
				java.text.DateFormat df = java.text.DateFormat
						.getDateInstance();
				Toast.makeText(
						getActivity(),
						"Editando os pontos na data "
								+ df.format(this.calendar.getTime()),
						Toast.LENGTH_SHORT).show();
			} catch (Exception e) {
				// TODO: Gambia
			}
		}
		super.onDismiss(dialog);
	}

}
