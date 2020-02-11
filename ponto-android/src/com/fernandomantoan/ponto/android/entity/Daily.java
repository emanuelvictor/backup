package com.fernandomantoan.ponto.android.entity;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import android.util.Log;

import com.fernandomantoan.ponto.android.repository.PointRepository;

public class Daily {
	private PointRepository repository;
	private List<Point> points = new LinkedList<Point>();
	private long morningDifference = 0;
	private long afternoonDifference = 0;
	private long dayDifference = 0;

	public Daily() {
		this.init(null);
	}

	public Daily(PointRepository repository, Calendar calendar) {
		this.repository = repository;
		this.init(calendar);
	}

	public Point getPoint(int location) {
		return this.points.get(location);
	}

	public Daily(List<Point> points) {
		this.points = points;
		this.init(null);
	}

	private void init(Calendar calendar) {
		while (this.points.size() < 4) {
			this.points.add(new Point());
		}
		if (calendar != null) {
			points = repository.findAllByDate(calendar);
//			Log.i("Tamanho dos pontos", String.valueOf(this.points.size()));
			for (int i = 0; i < this.points.size(); i++) {
				Log.i("asdfasdf",points.get(i).toString());
			}
			while (this.points.size() < 4) {
				this.points.add(new Point());
			}
			// if (points != null && points.size() > 0) {
			if ((this.points.get(0).getDate() != null)
					&& (this.points.get(1).getDate() != null)) {
				morningDifference = (points.get(1).getDate().getTimeInMillis() - points
						.get(0).getDate().getTimeInMillis())
						- (28800000 / 2);
				// }
				if (this.points.get(3).getDate() != null) {
					afternoonDifference = (points.get(3).getDate()
							.getTimeInMillis() - points.get(2).getDate()
							.getTimeInMillis())
							- (28800000 / 2);
					dayDifference = ((points.get(1).getDate().getTimeInMillis() - points
							.get(0).getDate().getTimeInMillis()) + (points
							.get(3).getDate().getTimeInMillis() - points.get(2)
							.getDate().getTimeInMillis())) - 28800000;
				}
			}
		}
	}

	public void setObs(String obs, int location) {
		this.init(null);
		this.points.get(location).setObs(obs);
		this.repository.save(this.points.get(location));
	}

	public long getMorningDifference() {
		return this.morningDifference;
	}

	public long getAfternoonDifference() {
		return this.afternoonDifference;
	}

	public long getDayDifference() {
		return this.dayDifference;
	}

	public List<Point> getPoints() {
		return this.points;
	}

}
