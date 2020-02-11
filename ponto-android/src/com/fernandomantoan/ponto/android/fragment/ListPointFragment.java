package com.fernandomantoan.ponto.android.fragment;

import java.text.SimpleDateFormat;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fernandomantoan.ponto.android.R;
import com.fernandomantoan.ponto.android.activity.MainActivity;
import com.fernandomantoan.ponto.android.adapter.PointsListViewAdapter;
import com.fernandomantoan.ponto.android.entity.Point;
import com.fernandomantoan.ponto.android.repository.PointRepository;
import com.fernandomantoan.ponto.android.support.DatabaseHelper;
import com.fernandomantoan.ponto.android.util.Export;
import com.fernandomantoan.ponto.android.util.HistoryPath;
import com.fernandomantoan.ponto.android.util.InterfaceFragment;

@SuppressLint("SimpleDateFormat")
public abstract class ListPointFragment extends ListFragment implements
		InterfaceFragment {
	//CLASSE MÃE PARA PARA FRAGMETNS DE LISTA DE PONTOS
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		this.update();
		return inflater.inflate(R.layout.list, null);
	}

	@Override
	public void handle(Object obj) throws Exception {
		if (obj instanceof MenuItem) {
			switch (((MenuItem) obj).getItemId()) {
			case R.id.action_export_json:
				if (HistoryPath.isExternalStorageAvailable()) {
					Export.exportToJSON(getActivity());
				}else {
					Toast.makeText(getActivity(), "Cartão de memória indisponível",
							Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.action_export_csv:
				if (HistoryPath.isExternalStorageAvailable()) {
					Export.exportToCSV(getActivity());
				}else {
					Toast.makeText(getActivity(), "Cartão de memória indisponível",
							Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.action_import:
				if (HistoryPath.isExternalStorageAvailable()) {
					((InterfaceFragment) getActivity())
							.goTo(new ListFilesFragment());
				}else {
					Toast.makeText(getActivity(), "Cartão de memória indisponível",
							Toast.LENGTH_SHORT).show();
				}
				break;
			default:
				break;
			}
		}

	}

	@Override
	public void update() {
		PointRepository repository = new PointRepository(new DatabaseHelper(
				getActivity()));

		List<Point> points = repository.findAllByDate(null);

		if (points.size() > 0) {
			String[] pointsArray = new String[points.size()];

			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");

			for (int i = 0; i < points.size(); i++) {
				pointsArray[i] = df.format(points.get(i).getDate().getTime());
			}

			PointsListViewAdapter adapter = new PointsListViewAdapter(
					(MainActivity) getActivity(), repository.findDaily());

			setListAdapter(adapter);

		}
	}

	@Override
	public void home() throws Exception {
		((InterfaceFragment) this.getActivity()).back();

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		try {
			this.configureActionBar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		inflater.inflate(R.menu.menupointlistfragment, menu);
	}

}
