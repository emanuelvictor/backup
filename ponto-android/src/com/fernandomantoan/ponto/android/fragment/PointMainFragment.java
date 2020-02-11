package com.fernandomantoan.ponto.android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fernandomantoan.ponto.android.R;
import com.fernandomantoan.ponto.android.activity.Navigation;
import com.fernandomantoan.ponto.android.util.InterfaceFragment;
import com.fernandomantoan.ponto.android.util.PointVariables;

public class PointMainFragment extends Fragment implements InterfaceFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		((Navigation) getActivity()).setVariables(new PointVariables(getActivity()));
		getActivity()
				.getSupportFragmentManager()
				.beginTransaction()
				.replace(
						R.id.main_fragment,
						((Navigation) getActivity()).getVariables()
								.getFragment()).commit();
		return inflater.inflate(R.layout.main_fragment, container, false);
	}

	@Override
	public void back() throws Exception {
		((InterfaceFragment) getActivity().getSupportFragmentManager()
				.findFragmentById(R.id.main_fragment)).back();
	}

	@Override
	public void configureActionBar() throws Exception {
		((InterfaceFragment) getActivity().getSupportFragmentManager()
				.findFragmentById(R.id.main_fragment)).configureActionBar();
	}

	@Override
	public void goTo(Object obj) throws Exception {
		((InterfaceFragment) getActivity().getSupportFragmentManager()
				.findFragmentById(R.id.main_fragment)).goTo(obj);

	}

	@Override
	public void handle(Object obj) throws Exception {
		((InterfaceFragment) getActivity().getSupportFragmentManager()
				.findFragmentById(R.id.main_fragment)).handle(obj);
	}

	@Override
	public void home() throws Exception {
		((InterfaceFragment) getActivity().getSupportFragmentManager()
				.findFragmentById(R.id.main_fragment)).home();

	}

	@Override
	public void update() throws Exception {
		((InterfaceFragment) getActivity().getSupportFragmentManager()
				.findFragmentById(R.id.main_fragment)).update();

	}
}
