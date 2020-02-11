package br.edu.udc.sistemas.ia6.emanuelvictor.session;

import br.edu.udc.sistemas.ia6.emanuelvictor.dao.Dao;
import br.edu.udc.sistemas.ia6.emanuelvictor.entity.Brand;
import br.edu.udc.sistemas.ia6.emanuelvictor.factory.FactoryDao;

public class SessionBrand extends Session {

	public SessionBrand(Dao dao) {
		super(dao);
	}

	public SessionBrand() throws Exception {
		super(FactoryDao.createDao(new Brand()));
	}

	@Override
	public void save(Object obj) throws Exception {
		super.save(obj);
	}

	@Override
	public void save(Object obj, Boolean bCommit) throws Exception {
		super.save(obj, bCommit);
	}

	@Override
	public Object[] deleteList(Object objDelete, Object objFilter)
			throws Exception {
		return super.deleteList(objDelete, objFilter);
	}

	@Override
	public void delete(Object obj) throws Exception {
		super.delete(obj);
	}

	@Override
	public void delete(Object obj, Boolean bCommit) throws Exception {
		super.delete(obj, bCommit);
	}

	@Override
	public Object[] find(Object obj) throws Exception {
		return super.find(obj);
	}

	@Override
	public Object detail(Object obj) throws Exception {
		return super.detail(obj);
	}

	@Override
	public Object findByPrimaryKey(Object obj) throws Exception {
		return super.findByPrimaryKey(obj);
	}
	
	
}