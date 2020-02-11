package com.fernandomantoan.ponto.android.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.fernandomantoan.ponto.android.R;
import com.fernandomantoan.ponto.android.util.InterfaceFragment;

@SuppressWarnings("serial")
public class MainActivity extends Navigation {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void goTo(Object obj) throws Exception {
		if (obj instanceof Fragment) {
			Log.i("Objeto", "Objeto");
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.main_activity, ((Fragment) obj)).commit();
		} else {
			Log.i("tag", "tag");
			((InterfaceFragment) this.getSupportFragmentManager()
					.findFragmentById(R.id.main_activity)).goTo(obj);
		}
	}

	@Override
	public void update() throws Exception {
		((InterfaceFragment) this.getSupportFragmentManager().findFragmentById(
				R.id.main_activity)).update();
	}

	@Override
	public void handle(Object obj) throws Exception {
		((InterfaceFragment) this.getSupportFragmentManager().findFragmentById(
				R.id.main_activity)).handle(obj);
	}

	@Override
	public void back() throws Exception {
		((InterfaceFragment) this.getSupportFragmentManager().findFragmentById(
				R.id.main_activity)).back();
	}

	@Override
	public void home() throws Exception {
		((InterfaceFragment) this.getSupportFragmentManager().findFragmentById(
				R.id.main_activity)).home();
	}

	@Override
	public void configureActionBar() throws Exception {
		((InterfaceFragment) this.getSupportFragmentManager().findFragmentById(
				R.id.main_activity)).configureActionBar();
	}

}