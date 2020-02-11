package com.fernandomantoan.ponto.android.util;

import java.io.Serializable;
import java.util.Calendar;

import com.fernandomantoan.ponto.android.fragment.smartphone.PointMainFragmentSmartphone;
import com.fernandomantoan.ponto.android.fragment.tablet.PointMainFragmentTablet;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;

public class PointVariables implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer tagViewPager = 0;
	private HistoryPath historyPath = HistoryPath.getInstance();
	private HistoryNavigation historyNavigation = HistoryNavigation
			.newInstance(Calendar.getInstance());
	private Class<?> fragmentClass;

	private static Activity activity;

	public PointVariables(Activity activity) {
		PointVariables.activity = activity;
		this.historyNavigation.restart();
		this.historyPath.restart();

	}

	public HistoryPath getHistory() {
		return historyPath;
	}

	public void setHistory(HistoryPath history) {
		this.historyPath = history;
	}

	public void setFragmentClass(Class<?> fragmentClass) {
		this.fragmentClass = fragmentClass;
	}

	public Fragment getFragment() {
		try {
			return (Fragment) this.fragmentClass.newInstance();
		} catch (Exception e) {
			// TODO Aqui poderão haver várias outras verificações
			// COmo por exemplo: SE é landscape, se é 2.3.3 etc
			if (isTablet()) {
				this.setFragmentClass(null);
				return new PointMainFragmentTablet();
			}
			this.setFragmentClass(null);
			return new PointMainFragmentSmartphone();
		}

	}

	public HistoryNavigation getHistoryNavigation() {
		return historyNavigation;
	}

	public void setHistoryNavigation(HistoryNavigation historyNavigation) {
		this.historyNavigation = historyNavigation;
	}

	public Integer getTagViewPager() {
		return tagViewPager;
	}

	public void setTagViewPager(Integer tagViewPager) {
		this.tagViewPager = tagViewPager;
	}

	protected static boolean isTablet() {
		if ((activity.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE)
			return true;
		if ((activity.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE)
			return true;
		else
			return false;
	}

}
