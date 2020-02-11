package com.fernandomantoan.ponto.android.adapter;

import java.util.Calendar;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fernandomantoan.ponto.android.R;
import com.fernandomantoan.ponto.android.activity.MainActivity;
import com.fernandomantoan.ponto.android.entity.Daily;
import com.fernandomantoan.ponto.android.entity.Point;
import com.fernandomantoan.ponto.android.util.DateFormatter;

public class PointsListViewAdapter extends BaseAdapter {

	private MainActivity activity;
	private List<Daily> points;

	// Views
	private TextView balance;
	private TextView time1;
	private TextView time2;
	private TextView time3;
	private TextView time4;
	private TextView dateTitle;
	private TextView obs;

	public PointsListViewAdapter(MainActivity activity, List<Daily> listDaily) {
		this.activity = activity;
		this.points = listDaily;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public long getItemId(int i) {
		return points.get(i).hashCode();
	}

	@Override
	public int getCount() {
		return points.size();
	}

	@Override
	public Object getItem(int i) {
		return points.get(i);
	}

	public void reference(View itemView) {
		this.balance = (TextView) itemView.findViewById(R.id.balance);
		this.time1 = (TextView) itemView.findViewById(R.id.time1);
		this.time2 = (TextView) itemView.findViewById(R.id.time2);
		this.time3 = (TextView) itemView.findViewById(R.id.time3);
		this.time4 = (TextView) itemView.findViewById(R.id.time4);
		this.dateTitle = (TextView) itemView.findViewById(R.id.date);
		this.obs = (TextView) itemView.findViewById(R.id.obs);

		this.balance.setText(R.string.empty_hour);
		this.time1.setText(R.string.empty_hour);
		this.time2.setText(R.string.empty_hour);
		this.time3.setText(R.string.empty_hour);
		this.time4.setText(R.string.empty_hour);
		this.dateTitle.setText(R.string.empty_hour);
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {

		LayoutInflater inflater = activity.getLayoutInflater();
		View itemView = inflater.inflate(R.layout.point_list_item, viewGroup,
				false);
		Daily p = new Daily();
		p = (Daily) getItem(i);
		List<Point> itens = p.getPoints();

		this.reference(itemView);

		if (itens != null && itens.size() > 0) {

			java.text.DateFormat dateFormat = java.text.DateFormat
					.getDateInstance();
			java.text.DateFormat timeFormat = java.text.DateFormat
					.getTimeInstance(java.text.DateFormat.SHORT);

			Calendar c = itens.get(0).getDate();

			this.dateTitle
					.setText((Calendar.getInstance().get(Calendar.DAY_OF_YEAR) == c
							.get(Calendar.DAY_OF_YEAR)) ? (dateFormat.format(c
							.getTime()) + " (Hoje)") : dateFormat.format(c
							.getTime()));

			if (itens.get(0).getDate() != null) {
				time1.setText(timeFormat.format(itens.get(0).getDate()
						.getTime()));
			}
			if (itens.get(1).getDate() != null) {
				time2.setText(timeFormat.format(itens.get(1).getDate()
						.getTime()));
			}
			if (itens.get(2).getDate() != null) {
				time3.setText(timeFormat.format(itens.get(2).getDate()
						.getTime()));
			}
			if (itens.get(3).getDate() != null) {
				time4.setText(timeFormat.format(itens.get(3).getDate()
						.getTime()));
				Long difference = ((itens.get(1).getDate().getTimeInMillis() - itens
						.get(0).getDate().getTimeInMillis()) + (itens.get(3)
						.getDate().getTimeInMillis() - itens.get(2).getDate()
						.getTimeInMillis())) - 28800000;
				balance.setText(DateFormatter.fromMilis(difference));
				if (difference < 0)
					balance.setTextColor(activity.getResources().getColor(
							R.color.red));
				else
					balance.setTextColor(activity.getResources().getColor(
							R.color.green));
			} else {
				((LinearLayout) itemView.findViewById(R.id.differenceLayout))
						.setVisibility(ListView.GONE);
			}
			for (int j = 0; j < itens.size(); j++) {
				if (itens.get(j).getObs() != null) {
					this.obs.setText(itens.get(j).getObs());
				}
			}
		}

		return itemView;
	}
}