package com.fernandomantoan.ponto.android.fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.fernandomantoan.ponto.android.R;
import com.fernandomantoan.ponto.android.activity.MainActivity;
import com.fernandomantoan.ponto.android.activity.Navigation;
import com.fernandomantoan.ponto.android.entity.Daily;
import com.fernandomantoan.ponto.android.fragment.picker.TimePickerFragment;
import com.fernandomantoan.ponto.android.repository.PointRepository;
import com.fernandomantoan.ponto.android.support.DatabaseHelper;
import com.fernandomantoan.ponto.android.util.DateFormatter;
import com.fernandomantoan.ponto.android.util.Export;
import com.fernandomantoan.ponto.android.util.HistoryPath;
import com.fernandomantoan.ponto.android.util.InterfaceFragment;
import com.fernandomantoan.ponto.android.util.PointVariables;

@SuppressLint("SimpleDateFormat")
public abstract class EditPointFragment extends Fragment implements
		InterfaceFragment {

	protected Daily dailyHandler;
	// protected boolean isEditing;

	protected Calendar calendar = null;

	protected TextView firstMorningHourTextView = null;
	protected TextView secondMorningHourTextView = null;
	protected TextView morningDifferenceTextView = null;
	protected TextView firstAfternoonHourTextView = null;
	protected TextView secondAfternoonHourTextView = null;
	protected TextView afternoonDifferenceTextView = null;
	protected TextView dayDifferenceTextView = null;
	protected TextView date = null;
	protected View view = null;
	protected EditText firstMorningEditText = null;
	protected EditText secondMorningEditText = null;
	protected EditText firstAfternoonEditText = null;
	protected EditText secondAfternoonEditText = null;

	// CLASSE MÃE PARA PARA FRAGMETNS DE EDIÇÃO
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.point_edit_item, null);
		setHasOptionsMenu(true);
		this.update();
		return view;
	}

	public void references() {
		firstMorningHourTextView = (TextView) view
				.findViewById(R.id.first_morning);
		secondMorningHourTextView = (TextView) view
				.findViewById(R.id.second_morning);
		morningDifferenceTextView = (TextView) view
				.findViewById(R.id.morning_difference);
		firstAfternoonHourTextView = (TextView) view
				.findViewById(R.id.first_afternoon);
		secondAfternoonHourTextView = (TextView) view
				.findViewById(R.id.second_afternoon);
		afternoonDifferenceTextView = (TextView) view
				.findViewById(R.id.afternoon_difference);
		dayDifferenceTextView = (TextView) view
				.findViewById(R.id.day_difference);
		date = (TextView) view.findViewById(R.id.dateedit);
		firstMorningEditText = (EditText) view
				.findViewById(R.id.first_morning_obs);
		secondMorningEditText = (EditText) view
				.findViewById(R.id.second_morning_obs);
		firstAfternoonEditText = (EditText) view
				.findViewById(R.id.first_afternoon_obs);
		secondAfternoonEditText = (EditText) view
				.findViewById(R.id.second_afternoon_obs);

		firstMorningHourTextView.setText(R.string.empty_hour);
		secondMorningHourTextView.setText(R.string.empty_hour);

		morningDifferenceTextView.setText(R.string.balance);
		firstAfternoonHourTextView.setText(R.string.empty_hour);
		secondAfternoonHourTextView.setText(R.string.empty_hour);
		afternoonDifferenceTextView.setText(R.string.balance);
		dayDifferenceTextView.setText(R.string.empty_hour);
		date.setText(R.string.date);

		firstMorningEditText.setHint(R.string.obs);
		secondMorningEditText.setHint(R.string.obs);
		firstAfternoonEditText.setHint(R.string.obs);
		secondAfternoonEditText.setHint(R.string.obs);
	}

	@Override
	public void update() {
		this.references();
		this.calendar = ((PointVariables) ((Navigation) getActivity())
				.getVariables()).getHistoryNavigation().getCurrent();
		// Log.i("Log calendar", this.calendar.toString());
		java.text.DateFormat df = java.text.DateFormat.getDateInstance();

		this.date
				.setText((Calendar.getInstance().get(Calendar.DAY_OF_YEAR) == this.calendar
						.get(Calendar.DAY_OF_YEAR)) ? (df.format(this.calendar
						.getTime()) + " (Hoje)") : df.format(this.calendar
						.getTime()));

		this.dailyHandler = new Daily(new PointRepository(new DatabaseHelper(
				getActivity())), this.calendar);

		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

		// Morning
		if (this.dailyHandler.getPoint(0).getDate() != null) {
			Log.i("asdfas",dateFormat.format(this.dailyHandler.getPoint(0).getDate().getTime()));
			this.firstMorningHourTextView.setText(dateFormat
					.format(this.dailyHandler.getPoint(0).getDate().getTime()));
		}
		if (this.dailyHandler.getPoint(1).getDate() != null) {
			this.secondMorningHourTextView.setText(dateFormat
					.format(this.dailyHandler.getPoint(1).getDate().getTime()));
		}
		if (this.dailyHandler.getMorningDifference() != 0) {
			this.morningDifferenceTextView.setText(getString(R.string.balance)
					+ " "
					+ DateFormatter.fromMilis(this.dailyHandler
							.getMorningDifference()));
			this.morningDifferenceTextView.setVisibility(TextView.VISIBLE);
			if (this.dailyHandler.getMorningDifference() < 0)
				this.morningDifferenceTextView.setTextColor(getResources()
						.getColor(R.color.red));
			else
				this.morningDifferenceTextView.setTextColor(getResources()
						.getColor(R.color.green));
		} else {
			this.morningDifferenceTextView.setVisibility(TextView.INVISIBLE);
		}
		// Afternoon
		if (this.dailyHandler.getPoint(2).getDate() != null) {
			this.firstAfternoonHourTextView.setText(dateFormat
					.format(this.dailyHandler.getPoint(2).getDate().getTime()));
		}
		if (this.dailyHandler.getPoint(3).getDate() != null) {
			this.secondAfternoonHourTextView.setText(dateFormat
					.format(this.dailyHandler.getPoint(3).getDate().getTime()));
		}
		if (this.dailyHandler.getAfternoonDifference() != 0) {
			this.afternoonDifferenceTextView
					.setText(getString(R.string.balance)
							+ " "
							+ DateFormatter.fromMilis(this.dailyHandler
									.getAfternoonDifference()));
			this.afternoonDifferenceTextView.setVisibility(TextView.VISIBLE);

			if (this.dailyHandler.getAfternoonDifference() < 0)
				this.afternoonDifferenceTextView.setTextColor(getResources()
						.getColor(R.color.red));
			else
				this.afternoonDifferenceTextView.setTextColor(getResources()
						.getColor(R.color.green));
		} else {
			this.afternoonDifferenceTextView.setVisibility(TextView.INVISIBLE);
		}
		// Day
		if (this.dailyHandler.getPoints().get(3).getDate() != null) {
			this.dayDifferenceTextView.setText(DateFormatter
					.fromMilis(this.dailyHandler.getDayDifference()));
			if (this.dailyHandler.getDayDifference() < 0)
				this.dayDifferenceTextView.setTextColor(getResources()
						.getColor(R.color.red));
			else
				this.dayDifferenceTextView.setTextColor(getResources()
						.getColor(R.color.green));
			if (dailyHandler.getDayDifference() > 1800000
					|| dailyHandler.getDayDifference() < -1800000)
				Toast.makeText(this.getActivity(), R.string.dont_forget_form,
						Toast.LENGTH_LONG).show();
		}

		// Obs
		firstMorningEditText.setText(this.dailyHandler.getPoint(0).getObs());
		secondMorningEditText.setText(this.dailyHandler.getPoint(1).getObs());
		firstAfternoonEditText.setText(this.dailyHandler.getPoint(2).getObs());
		secondAfternoonEditText.setText(this.dailyHandler.getPoint(3).getObs());
		// Listeners obs
		firstMorningEditText
				.setOnEditorActionListener(new OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						if (actionId == EditorInfo.IME_ACTION_DONE) {
							try {
								if (dailyHandler.getPoint(0).getDate() == null) {
									dailyHandler.getPoint(0).setDate(
											((Navigation) getActivity())
													.getVariables()
													.getHistoryNavigation()
													.getCurrentDate());
								}
								dailyHandler.setObs(v.getText().toString(), 0);
								((MainActivity) getActivity()).update();
								Toast.makeText(getActivity(),
										"Observação inserida",
										Toast.LENGTH_SHORT).show();
							} catch (Exception e) {
								e.printStackTrace();
								Toast.makeText(getActivity(),
										"Erro ao inserir observação",
										Toast.LENGTH_SHORT).show();
							}
							return true;
						}
						return false;
					}
				});

		secondMorningEditText
				.setOnEditorActionListener(new OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						if (actionId == EditorInfo.IME_ACTION_DONE) {
							try {
								if (dailyHandler.getPoint(1).getDate() == null) {
									dailyHandler.getPoint(1).setDate(
											((Navigation) getActivity())
													.getVariables()
													.getHistoryNavigation()
													.getCurrentDate());
								}
								dailyHandler.setObs(v.getText().toString(), 1);
								((MainActivity) getActivity()).update();
								Toast.makeText(getActivity(),
										"Observação inserida",
										Toast.LENGTH_SHORT).show();
							} catch (Exception e) {
								e.printStackTrace();
								Toast.makeText(getActivity(),
										"Erro ao inserir observação",
										Toast.LENGTH_SHORT).show();
							}
							return true;
						}
						return false;
					}
				});

		firstAfternoonEditText
				.setOnEditorActionListener(new OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						if (actionId == EditorInfo.IME_ACTION_DONE) {
							try {
								if (dailyHandler.getPoint(2).getDate() == null) {
									dailyHandler.getPoint(2).setDate(
											((Navigation) getActivity())
													.getVariables()
													.getHistoryNavigation()
													.getCurrentDate());
								}
								dailyHandler.setObs(v.getText().toString(), 2);
								((MainActivity) getActivity()).update();
								Toast.makeText(getActivity(),
										"Observação inserida",
										Toast.LENGTH_SHORT).show();
							} catch (Exception e) {
								e.printStackTrace();
								Toast.makeText(getActivity(),
										"Erro ao inserir observação",
										Toast.LENGTH_SHORT).show();
							}
							return true;
						}
						return false;
					}
				});
		secondAfternoonEditText
				.setOnEditorActionListener(new OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						if (actionId == EditorInfo.IME_ACTION_DONE) {
							try {
								if (dailyHandler.getPoint(3).getDate() == null) {
									dailyHandler.getPoint(3).setDate(
											((Navigation) getActivity())
													.getVariables()
													.getHistoryNavigation()
													.getCurrentDate());
								}
								dailyHandler.setObs(v.getText().toString(), 3);
								((MainActivity) getActivity()).update();
								Toast.makeText(getActivity(),
										"Observação inserida",
										Toast.LENGTH_SHORT).show();
							} catch (Exception e) {
								e.printStackTrace();
								Toast.makeText(getActivity(),
										"Erro ao inserir observação",
										Toast.LENGTH_SHORT).show();
							}
							return true;
						}
						return false;
					}
				});
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		try {
			this.configureActionBar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		inflater.inflate(R.menu.menueditfragment, menu);
	}

	@Override
	public void handle(Object obj) throws Exception {
		if (obj instanceof MenuItem) {
			switch (((MenuItem) obj).getItemId()) {
			case R.id.action_export_json:
				if (HistoryPath.isExternalStorageAvailable()) {
					Export.exportToJSON(getActivity());
				} else {
					Toast.makeText(getActivity(),
							"Cartão de memória indisponível",
							Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.action_export_csv:
				if (HistoryPath.isExternalStorageAvailable()) {
					Export.exportToCSV(getActivity());
				} else {
					Toast.makeText(getActivity(),
							"Cartão de memória indisponível",
							Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.action_import:
				if (HistoryPath.isExternalStorageAvailable()) {
					((InterfaceFragment) getActivity())
							.goTo(new ListFilesFragment());
				} else {
					Toast.makeText(getActivity(),
							"Cartão de memória indisponível",
							Toast.LENGTH_SHORT).show();
				}
				break;
			default:
				break;
			}
		} else if (obj instanceof View) {
			switch (((View) obj).getId()) {
			case R.id.first_morning:
				showPicker(0);
				break;
			case R.id.second_morning:
				showPicker(1);
				break;
			case R.id.first_afternoon:
				showPicker(2);
				break;
			case R.id.second_afternoon:
				showPicker(3);
				break;
			}
		}

	}

	private void showPicker(int indexEdition) {

		// if ((this.dailyHandler.getPoints().get(indexEdition).getDate() !=
		// null)
		// || ((indexEdition > 1) && (this.dailyHandler.getPoints()
		// .get(indexEdition - 1)).getDate() != null)) {
		// List<Point> points = this.dailyHandler.getPoints();

		// if (points.get(indexEdition).getDate() == null) {
		// isEditing = false;
		// } else {
		// isEditing = true;
		// }TODO focusableChanged no editText

		DialogFragment newFragment = new TimePickerFragment();
		Bundle bundle = new Bundle();
		// bundle.putBoolean("isEditing", isEditing);
		bundle.putInt("index", indexEdition);
		newFragment.setArguments(bundle);

		newFragment.show(getActivity().getSupportFragmentManager(),
				"timePicker");
	}

	// }

}