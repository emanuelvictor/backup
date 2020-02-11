package br.edu.udc.sistemas.ia6.emanuelvictor.dao.daoDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import br.edu.udc.sistemas.ia6.emanuelvictor.dao.Dao;
import br.edu.udc.sistemas.ia6.emanuelvictor.factory.FactoryEntity;
import br.edu.udc.sistemas.ia6.emanuelvictor.util.DatabasePool;
import br.edu.udc.sistemas.ia6.emanuelvictor.util.Query;
import br.edu.udc.sistemas.ia6.emanuelvictor.util.Reflection;

public class DaoDatabase implements Dao {
private Connection con;
	
	public DaoDatabase() throws Exception {
		this.con = DatabasePool.getInstance().getConnection();
	}

	public DaoDatabase(Connection con) throws Exception {
		this.con = con;
	}
	
	@Override
	public Connection getConnection() {
		return this.con;
	}
	
	@Override
	public void save(Object obj) throws Exception {
		try {
			Statement stmt = this.con.createStatement();
			String sql = "";
			Object idValue = Reflection.getIdFieldValue(obj);
			if ((idValue != null) && (!idValue.equals(""))) {
				sql = Query.getSQLUpdate(obj);
				System.out.println(sql);
				stmt.execute(sql);
			} else {
				sql = Query.getSQLInsert(obj).toUpperCase();
				System.out.println(sql);
				stmt.execute(sql,Statement.RETURN_GENERATED_KEYS);
				ResultSet rst = stmt.getGeneratedKeys();
				if (rst.next()) {
					Reflection.setIdFieldValue(obj, rst.getInt(1));
				}
			}			
		} catch (Exception e) {
			if (this.con != null) {
				try {
					this.con.rollback();					
				} catch (Exception e2) {
				}
			}
			throw e;
		}
	}

	@Override
	public void delete(Object obj) throws Exception {
		Object idValue = Reflection.getIdFieldValue(obj);
		if ((idValue != null) && (!idValue.equals(""))) {
			try {
				Statement stmt = this.con.createStatement();
				String sql = Query.getSQLDelete(obj);
				System.out.println(sql);
				stmt.execute(sql);
			} catch (Exception e) {
				if (this.con != null) {
					try {
						this.con.rollback();					
					} catch (Exception e2) {
					}
				}
				throw e;
			}
		}
	}

	@Override
	public Object[] find(Object obj) throws Exception {
		try {
			Statement stmt = this.con.createStatement();
			String sql = Query.getSQLSelect(obj);
			System.out.println(sql);
			ResultSet rst = stmt.executeQuery(sql);
			return FactoryEntity.createByResultSet(rst,obj.getClass());
		} catch (Exception e) {
			if (this.con != null) {
				try {
					this.con.rollback();					
				} catch (Exception e2) {
				}
			}
			throw e;
		}
	}

	@Override
	public Object findByPrimaryKey(Object obj) throws Exception {
		Object idValue = Reflection.getIdFieldValue(obj);
		if ((idValue != null) && (!idValue.equals(""))) {
			Object objAux = obj.getClass().newInstance();
			Reflection.setIdFieldValue(objAux, idValue);
			Object list[] = this.find(objAux);
			if (list.length > 0) {
				return list[0];
			}
		}
		return null;
	}
	
	@Override
	public void commit() throws Exception {
		 this.con.commit();
	}

	@Override
	public void roolback() throws Exception {
		 this.con.rollback();
	}
}