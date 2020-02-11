package com.fernandomantoan.ponto.android.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class HistoryNavigation implements Serializable {

	private static final long serialVersionUID = 1L;
	// Variável que identifica se entrou pelo histórico ou pela today
	private List<Calendar> history = new LinkedList<Calendar>();

	public void restart() {
		this.history = new LinkedList<Calendar>();
		this.incrementHistory(Calendar.getInstance());
	}

	public static HistoryNavigation newInstance(Calendar calendar) {
		return new HistoryNavigation(calendar);
	}

	private HistoryNavigation(Calendar calendar) {
		this.incrementHistory(calendar);
	}

	public HistoryNavigation newInstance() {
		return this;
	}

	public void incrementHistory(Calendar calendar) {
		this.history.add(calendar);
	}

	public void decrementHistory() {
		if (this.history.size() > 0) {
			this.history.remove(this.history.size() - 1);
		}
	}

	public List<Calendar> getHistory() {
		return history;
	}

	public void setHistory(List<Calendar> history) {
		this.history = history;
	}

	public boolean isHome() {
		if (this.history == null) {
			return true;
		}
		if (this.history.size() <= 1) {
			return true;
		}
		return false;
	}

	public Calendar getBack() {
		return this.history.get(this.history.size() - 2);
	}

	public Calendar getCurrent() {
		if (history.isEmpty()) {
			this.history.add(Calendar.getInstance());
		}
		return this.history.get(this.history.size() - 1);
	}
	
	//TODO não será necessário
	public Calendar getCurrentDate() {
		Calendar c = this.getCurrent();
		c.clear(Calendar.HOUR);
		c.clear(Calendar.HOUR_OF_DAY);
		c.clear(Calendar.MINUTE);
		c.clear(Calendar.ZONE_OFFSET);
		c.clear(Calendar.MILLISECOND);
		c.clear(Calendar.SECOND);
		c.clear(Calendar.DST_OFFSET);
		return c;
	}

}
