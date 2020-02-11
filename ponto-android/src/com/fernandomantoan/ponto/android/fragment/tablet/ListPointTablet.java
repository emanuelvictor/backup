package com.fernandomantoan.ponto.android.fragment.tablet;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fernandomantoan.ponto.android.R;
import com.fernandomantoan.ponto.android.activity.Navigation;
import com.fernandomantoan.ponto.android.fragment.ListPointFragment;
import com.fernandomantoan.ponto.android.fragment.picker.DatePickerFragment;
import com.fernandomantoan.ponto.android.util.InterfaceFragment;
import com.fernandomantoan.ponto.android.util.PointVariables;

public class ListPointTablet extends ListPointFragment {

	@Override
	public void handle(Object obj) throws Exception {
		if (obj instanceof MenuItem) {
			switch (((MenuItem) obj).getItemId()) {
			case R.id.action_new_calendar:
				DatePickerFragment newFragment = new DatePickerFragment();
				newFragment.show(getActivity().getSupportFragmentManager(),
						"timePicker");
				break;
			}
		} else if (obj instanceof View) {
			switch (((View) obj).getId()) {
			case R.id.point_list_item:
				try {
					java.text.DateFormat df = java.text.DateFormat
							.getDateInstance();
					TextView textView = (TextView) ((View) obj)
							.findViewById(R.id.date);

					Date date = new Date(df
							.parse(textView.getText().toString()).getTime());

					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					((PointVariables) ((Navigation) getActivity())
							.getVariables()).getHistoryNavigation()
							.incrementHistory(calendar);
					((InterfaceFragment) getActivity()).update();
					this.goTo(0);
					Toast.makeText(
							getActivity(),
							"Editando os pontos na data "
									+ df.format(((PointVariables) ((Navigation) getActivity())
											.getVariables())
											.getHistoryNavigation()
											.getCurrent().getTime()),
							Toast.LENGTH_SHORT).show();
				} catch (ParseException e) {
					Toast.makeText(getActivity(), "Erro ao editar ",
							Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void goTo(Object obj) throws Exception {
	}

	@Override
	public void back() throws Exception {
	}

	@Override
	public void configureActionBar() throws Exception {
		
	}

	@Override
	public void home() throws Exception {
	}
}
