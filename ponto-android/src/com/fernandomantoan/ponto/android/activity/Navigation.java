package com.fernandomantoan.ponto.android.activity;

import java.io.Serializable;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.fernandomantoan.ponto.android.R;
import com.fernandomantoan.ponto.android.util.InterfaceFragment;
import com.fernandomantoan.ponto.android.util.PointVariables;

public abstract class Navigation extends ActionBarActivity implements
		InterfaceFragment, OnClickListener, Serializable {

	private static final long serialVersionUID = 1L;
	// /Essa classe pode mudar de aplicação para aplicação, por exemplo se for o
	// vaiCafé pode ser um VariablesVaicafé
	private PointVariables variables;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main_activity);
		// Aqui a variável variables poderia receber classe genérica Variables,
		// desta forma redirecionaria para o menú selecionado, como por exmeplo
		// o menu principal this.variables = new PointVariables(this);
		this.variables = new PointVariables(this);
		if (savedInstanceState != null) {
			this.setVariables((PointVariables) savedInstanceState
					.getSerializable("variables"));
		}
		try {
			this.goTo(getVariables().getFragment());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putSerializable("variables", this.getVariables());
		super.onSaveInstanceState(outState);
	}

	// Lida com os clicks internos
	@Override
	public void onClick(View v) {
		try {
			this.handle(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Navegação dos botões voltar
	@Override
	public void onBackPressed() {
		try {
			this.back();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Lida com os CLicks dos menu
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		try {
			this.handle(item);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.onOptionsItemSelected(item);
	}

	public PointVariables getVariables() {
		return variables;
	}

	public void setVariables(PointVariables variables) {
		this.variables = variables;
	}

}
