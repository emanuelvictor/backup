package br.edu.udc.sistemas.ia6.emanuelvictor.session;

import br.edu.udc.sistemas.ia6.emanuelvictor.dao.Dao;
import br.edu.udc.sistemas.ia6.emanuelvictor.entity.SO;
import br.edu.udc.sistemas.ia6.emanuelvictor.factory.FactoryDao;

public class SessionOS extends Session {

	public SessionOS(Dao dao) {
		super(dao);
	}

	public SessionOS() throws Exception {
		super(FactoryDao.createDao(new SO()));
	}
}