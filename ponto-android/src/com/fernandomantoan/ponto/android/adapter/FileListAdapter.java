package com.fernandomantoan.ponto.android.adapter;

import java.io.File;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FileListAdapter extends BaseAdapter {
	private Context context;
	private List<File> files;
	
	public FileListAdapter(Context context, List<File> files) {
		this.context = context;
		this.files = files;
	}

	@Override
	public int getCount() {
		return files.size();
	}

	@Override
	public Object getItem(int position) {
		return files.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					android.R.layout.simple_list_item_1, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.fileName = (TextView)convertView.findViewById(android.R.id.text1);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.fileName.setText(files.get(position).getName());
		
		return convertView;
	}
	
	static class ViewHolder {
		public TextView fileName;
	}
	
	public void clear() {
		files.clear();
		notifyDataSetChanged();
	}
	
	public void swapItems(List<File> files) {
	    this.files = files;
	    notifyDataSetChanged();
	}
	
	public String[] getList(){
		String[] vet = new String[files.size()];
		for (int i = 0; i < vet.length; i++) {
			vet[i] = files.get(i).getName();
		}
		return vet;
	}

}

