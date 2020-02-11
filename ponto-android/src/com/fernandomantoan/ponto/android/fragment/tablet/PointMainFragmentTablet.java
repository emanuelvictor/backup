package com.fernandomantoan.ponto.android.fragment.tablet;

import java.util.LinkedList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.fernandomantoan.ponto.android.R;
import com.fernandomantoan.ponto.android.activity.MainActivity;
import com.fernandomantoan.ponto.android.activity.Navigation;
import com.fernandomantoan.ponto.android.util.InterfaceFragment;
import com.fernandomantoan.ponto.android.util.PointVariables;

public class PointMainFragmentTablet extends Fragment implements
		InterfaceFragment {
	private List<Fragment> fragments = new LinkedList<Fragment>();
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		fragments.add(new EditPointTablet());
		fragments.add(new ListPointTablet());
		setHasOptionsMenu(true);
		view = inflater.inflate(R.layout.multi_pane_layout, null);
		((MainActivity) getActivity()).getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.container_frame1, fragments.get(0)).commit();
		((MainActivity) getActivity()).getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.container_frame2, fragments.get(1)).commit();
		((MainActivity) this.getActivity()).getSupportActionBar().setTitle(
				"Relógio Ponto");
		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		menu.clear();
		inflater.inflate(R.menu.main, menu);
	}

	@Override
	public void goTo(Object obj) throws Exception {
	}

	@Override
	public void update() throws Exception {
		for (int i = 0; i < fragments.size(); i++) {
			((InterfaceFragment) this.fragments.get(i)).update();
		}
		this.configureActionBar();
	}

	@Override
	public void handle(Object obj) throws Exception {
		if (obj instanceof MenuItem) {
			switch (((MenuItem) obj).getItemId()) {
			case R.id.action_new_calendar:
				((InterfaceFragment) this.fragments.get(1)).handle(obj);
				break;
			default:
				((InterfaceFragment) this.fragments.get(0)).handle(obj);
				break;
			}
		} else if (obj instanceof View) {
			switch (((View) obj).getId()) {
			case R.id.point_list_item:
				((InterfaceFragment) this.fragments.get(1)).handle(obj);
				break;
			default:
				((InterfaceFragment) this.fragments.get(0)).handle(obj);
				break;
			}
		}
		this.configureActionBar();
	}

	@Override
	public void back() throws Exception {
		((InterfaceFragment) this.fragments.get(0)).back();
		this.configureActionBar();
	}

	@Override
	public void home() throws Exception {
		((InterfaceFragment) this.fragments.get(0)).home();
		this.configureActionBar();
	}

	@Override
	public void configureActionBar() throws Exception {
		// Esse fragment é inflado por ultimo, por isso é ele quem determina a
		// configuração da actionbar
		if (((PointVariables) ((Navigation) getActivity()).getVariables())
				.getHistoryNavigation().isHome()) {
			((ActionBarActivity) this.getActivity()).getSupportActionBar()
					.setDisplayHomeAsUpEnabled(false);
		} else {
			((ActionBarActivity) this.getActivity()).getSupportActionBar()
					.setDisplayHomeAsUpEnabled(true);
		}
	}
}
