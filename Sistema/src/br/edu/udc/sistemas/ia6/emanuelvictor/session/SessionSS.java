package br.edu.udc.sistemas.ia6.emanuelvictor.session;

import br.edu.udc.sistemas.ia6.emanuelvictor.dao.Dao;
import br.edu.udc.sistemas.ia6.emanuelvictor.entity.SS;
import br.edu.udc.sistemas.ia6.emanuelvictor.factory.FactoryDao;

public class SessionSS extends Session {

	public SessionSS(Dao dao) {
		super(dao);
	}

	public SessionSS() throws Exception {
		super(FactoryDao.createDao(new SS()));
	}

}