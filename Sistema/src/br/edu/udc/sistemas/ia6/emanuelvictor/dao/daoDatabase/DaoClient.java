package br.edu.udc.sistemas.ia6.emanuelvictor.dao.daoDatabase;

import java.sql.Connection;

public class DaoClient extends DaoDatabase {

	public DaoClient() throws Exception {
		super();
	}

	public DaoClient(Connection con) throws Exception {
		super(con);
	}

}
