package com.fernandomantoan.ponto.android.util;


public interface InterfaceFragment {
//	public int TAG = 0;
	public void goTo(Object obj) throws Exception;
	public void update()throws Exception;
	public void handle(Object obj)throws Exception;
	public void back()throws Exception;
	public void home()throws Exception;
	public void configureActionBar()throws Exception;
}
