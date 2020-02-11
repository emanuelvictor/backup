package com.fernandomantoan.ponto.android.loader;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.fernandomantoan.ponto.android.util.HistoryPath;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class FileListLoader extends AsyncTaskLoader<List<File>> {

	private List<File> files;
	private String path;

	public FileListLoader(Context context, String path) {
		super(context);
		this.path = path;
	}
	
	public FileListLoader(Context context, String path,  HistoryPath history) {
		super(context);
		this.path = path;
		if (this.path.length() > ((String)history.getCurrent()).length()) {
			history.increment(path);
		}
	}

	@Override
	public List<File> loadInBackground() {
		ArrayList<File> result = new ArrayList<File>();
		
		// Obtém todos os arquivos, ignorando os ocultos.
		final File[] dirs = new File(path).listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return !pathname.isHidden();
			}
		});

		if (dirs != null) {
			// Ordena colocando os diretórios primeiro.
			Arrays.sort(dirs, new Comparator<File>() {
				@Override
				public int compare(File lhs, File rhs) {
					if (lhs.isDirectory() && !rhs.isDirectory())
						return -1;

					if (!lhs.isDirectory() && rhs.isDirectory())
						return 1;

					return lhs.getName().compareToIgnoreCase(rhs.getName());
				}
			});

			for (File dir : dirs) {
				result.add(dir);
			}
		}

		return result;

	}

	@Override
	public void deliverResult(List<File> data) {
		files = data;

		if (isStarted()) {
			super.deliverResult(data);
		}
	}

	@Override
	protected void onStartLoading() {
		if (files != null) {
			deliverResult(files);
		}

		if (takeContentChanged() || files == null) {
			forceLoad();
		}
	}

	@Override
	protected void onStopLoading() {
		cancelLoad();
	}

}
