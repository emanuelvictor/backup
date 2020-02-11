package br.edu.udc.sistemas.ia6.emanuelvictor.dao;

import java.sql.Connection;

public interface Dao {
	public Connection getConnection();
	public void save(Object obj) throws Exception;
	public void delete(Object obj) throws Exception;
	public void commit() throws Exception;
	public void roolback() throws Exception;
	public Object[] find(Object obj) throws Exception;
	public Object findByPrimaryKey(Object obj) throws Exception;
	
}