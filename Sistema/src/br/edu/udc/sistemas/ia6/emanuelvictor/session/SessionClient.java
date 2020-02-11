package br.edu.udc.sistemas.ia6.emanuelvictor.session;

import br.edu.udc.sistemas.ia6.emanuelvictor.dao.Dao;
import br.edu.udc.sistemas.ia6.emanuelvictor.entity.Client;
import br.edu.udc.sistemas.ia6.emanuelvictor.factory.FactoryDao;

public class SessionClient extends Session {

	public SessionClient(Dao dao) {
		super(dao);
	}

	public SessionClient() throws Exception {
		super(FactoryDao.createDao(new Client()));
	}

}