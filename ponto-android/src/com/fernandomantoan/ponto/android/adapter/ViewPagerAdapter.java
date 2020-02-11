package com.fernandomantoan.ponto.android.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


//Esta classe cuidará da lista de fragments

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

	// Variavel privada que guarda a lista de fragments
	private List<Fragment> mFragments;

	// Construtor alterado para receber uma lista de fragments
	public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
		super(fm);
		this.mFragments = fragments;
	}

	// O metodo getItem foi alterado para retornar o Fragment pelo indice
	@Override
	public Fragment getItem(int i) {
		
		return mFragments.get(i);
	}

	// O metodo getCount foi alterado para retornar a quantidade de fragments na
	// lista
	@Override
	public int getCount() {
		
		return mFragments.size();
	}

}
