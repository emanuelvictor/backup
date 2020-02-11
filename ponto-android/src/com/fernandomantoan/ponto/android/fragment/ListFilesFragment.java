package com.fernandomantoan.ponto.android.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.fernandomantoan.ponto.android.R;
import com.fernandomantoan.ponto.android.activity.MainActivity;
import com.fernandomantoan.ponto.android.activity.Navigation;
import com.fernandomantoan.ponto.android.adapter.FileListAdapter;
import com.fernandomantoan.ponto.android.loader.FileListLoader;
import com.fernandomantoan.ponto.android.util.Export;
import com.fernandomantoan.ponto.android.util.Import;
import com.fernandomantoan.ponto.android.util.InterfaceFragment;
import com.fernandomantoan.ponto.android.util.PointVariables;

public class ListFilesFragment extends ListFragment implements
		LoaderCallbacks<List<File>>, InterfaceFragment {

	private FileListAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		adapter = new FileListAdapter(getActivity(), new ArrayList<File>());
		setListAdapter(adapter);
		((PointVariables) ((Navigation) getActivity()).getVariables())
				.setFragmentClass(ListFilesFragment.class);
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		File file = (File) adapter.getItem(position);
		if (file.isDirectory()) {
			((PointVariables) ((Navigation) getActivity()).getVariables())
					.getHistory().increment(file.getAbsolutePath());
			try {
				// Deveria ser um update, consertar isso outro dia se retirando
				// a classe estendida e se criando uma adapter prórpia
				goTo(this.getClass().newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Import.onFileSelected(((MainActivity) getActivity()), file);
		}
	}

	@Override
	public Loader<List<File>> onCreateLoader(int arg0, Bundle arg1) {
		return new FileListLoader(getActivity(),
				(String) ((PointVariables) ((Navigation) getActivity())
						.getVariables()).getHistory().getCurrent(),
				((PointVariables) ((Navigation) getActivity()).getVariables())
						.getHistory());
	}

	@Override
	public void onLoadFinished(Loader<List<File>> arg0, List<File> data) {
		adapter.swapItems(data);
	}

	@Override
	public void onLoaderReset(Loader<List<File>> arg0) {
		adapter.clear();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		menu.clear();
		inflater.inflate(R.menu.main, menu);
		this.configureActionBar();

	}

	@Override
	public void configureActionBar() {
		((Navigation) this.getActivity()).getSupportActionBar()
				.setDisplayHomeAsUpEnabled(true);
		((Navigation) getActivity()).getSupportActionBar().setTitle(
				(CharSequence) ((PointVariables) ((Navigation) getActivity())
						.getVariables()).getHistory().getCurrent());
	}

	@Override
	public void update() {

	}

	@Override
	public void handle(Object obj) throws java.lang.InstantiationException,
			IllegalAccessException, Exception {
		switch (((MenuItem) obj).getItemId()) {
		case R.id.action_export_json:
			Export.exportToJSON(getActivity());
			break;
		case R.id.action_export_csv:
			Export.exportToCSV(getActivity());
			break;
		case R.id.action_import:
			((PointVariables) ((Navigation) getActivity()).getVariables())
					.getHistory().restart();
			this.goTo(this.getClass().newInstance());
			break;
		case android.R.id.home:
			this.home();
			break;
		}

	}

	@Override
	public void goTo(Object obj) throws Exception {
		((InterfaceFragment) getActivity()).goTo(obj);
	}

	@Override
	public void back() throws Exception {
		if (((PointVariables) ((Navigation) getActivity()).getVariables())
				.getHistory().isRoot()) {
			((PointVariables) ((Navigation) getActivity()).getVariables())
					.setFragmentClass(null);
			goTo(((PointVariables) ((Navigation) getActivity()).getVariables())
					.getFragment());
		} else {
			((PointVariables) ((Navigation) getActivity()).getVariables())
					.getHistory().decrement();
			// TODO este goTo deveria ser o update, mas como estou com pressa
			// via ser assim mesmo
			goTo(this.getClass().newInstance());
		}

	}

	@Override
	public void home() throws Exception {
		this.back();
	}
}
