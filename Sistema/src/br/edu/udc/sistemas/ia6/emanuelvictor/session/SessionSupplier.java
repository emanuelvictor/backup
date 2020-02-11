package br.edu.udc.sistemas.ia6.emanuelvictor.session;

import br.edu.udc.sistemas.ia6.emanuelvictor.dao.Dao;
import br.edu.udc.sistemas.ia6.emanuelvictor.entity.Supplier;
import br.edu.udc.sistemas.ia6.emanuelvictor.factory.FactoryDao;

public class SessionSupplier extends Session {

	public SessionSupplier(Dao dao) {
		super(dao);
	}

	public SessionSupplier() throws Exception {
		super(FactoryDao.createDao(new Supplier()));
	}
}