package com.fernandomantoan.ponto.android.fragment.smartphone;

import java.util.LinkedList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fernandomantoan.ponto.android.R;
import com.fernandomantoan.ponto.android.activity.MainActivity;
import com.fernandomantoan.ponto.android.activity.Navigation;
import com.fernandomantoan.ponto.android.adapter.ViewPagerAdapter;
import com.fernandomantoan.ponto.android.util.InterfaceFragment;
import com.fernandomantoan.ponto.android.util.PointVariables;

public class PointMainFragmentSmartphone extends Fragment implements InterfaceFragment,
		OnPageChangeListener {
	private ViewPager viewPager;
	private ViewPagerAdapter pagerAdapter;
	private List<Fragment> fragments;
	private View view;

	public ViewPager getViewPager() {
		return viewPager;
	}

	public void setViewPager(ViewPager viewPager) {
		this.viewPager = viewPager;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// ((MainActivity) getActivity()).getSupportActionBar()
		// .setDisplayHomeAsUpEnabled(false);
		setHasOptionsMenu(true);
		view = inflater.inflate(R.layout.view_pager, null);
		this.initialiseViewPager();
		((MainActivity) this.getActivity()).getSupportActionBar().setTitle(
				"Relógio Ponto");
		return view;
	}

	public void initialiseViewPager() {
		fragments = new LinkedList<Fragment>();
		fragments.add(new EditPointSmartphone());
		fragments.add(new ListPointSmartphone());
		pagerAdapter = new ViewPagerAdapter(
				(getActivity().getSupportFragmentManager()), fragments);
		viewPager = (ViewPager) view.findViewById(R.id.view_pager);
		viewPager.setAdapter(this.pagerAdapter);
		viewPager.setOnPageChangeListener(this);
		viewPager.setCurrentItem(((PointVariables) ((Navigation) getActivity())
				.getVariables()).getTagViewPager());
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// Log.i("arg0", String.valueOf(arg0));
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// Log.i("arg0", String.valueOf(arg0));
		// Log.i("arg2", String.valueOf(arg2));
	}

	@Override
	public void onPageSelected(int tag) {
		((PointVariables) ((Navigation) getActivity()).getVariables())
				.setTagViewPager(tag);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		menu.clear();
		inflater.inflate(R.menu.main, menu);
	}

	@Override
	public void update() throws Exception {
		((InterfaceFragment) this.fragments.get(0)).update();
		((InterfaceFragment) this.fragments.get(1)).update();
	}

	@Override
	public void handle(Object obj) throws Exception {
		((InterfaceFragment) this.fragments.get(viewPager.getCurrentItem()))
				.handle(obj);
	}

	@Override
	public void goTo(Object obj) throws Exception {
		this.viewPager.setCurrentItem((Integer) obj);
	}

	@Override
	public void back() throws Exception {
		((InterfaceFragment) this.fragments.get(viewPager.getCurrentItem())).back();
	}

	@Override
	public void home() throws Exception {
		((InterfaceFragment) this.fragments.get(viewPager.getCurrentItem())).home();
	}

	@Override
	public void configureActionBar() throws Exception {
		((InterfaceFragment) this.fragments.get(viewPager.getCurrentItem()))
				.configureActionBar();

	}
}
