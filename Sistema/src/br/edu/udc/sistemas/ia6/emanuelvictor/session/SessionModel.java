package br.edu.udc.sistemas.ia6.emanuelvictor.session;

import br.edu.udc.sistemas.ia6.emanuelvictor.dao.Dao;
import br.edu.udc.sistemas.ia6.emanuelvictor.dao.daoDatabase.DaoBrand;
import br.edu.udc.sistemas.ia6.emanuelvictor.entity.Brand;
import br.edu.udc.sistemas.ia6.emanuelvictor.entity.Model;
import br.edu.udc.sistemas.ia6.emanuelvictor.factory.FactoryDao;

public class SessionModel extends Session {

	public SessionModel(Dao dao) {
		super(dao);
	}

	public SessionModel() throws Exception {
		super(FactoryDao.createDao(new Model()));
	}
	
	@Override
	public Object[] find(Object obj) throws Exception {
		Object list[] = super.find(obj);
		DaoBrand daoBrand = new DaoBrand();
		for (int i = 0; i < list.length; i++) {
			Model model = (Model) list[i];
			model.setBrand((Brand) daoBrand.findByPrimaryKey(model.getBrand()));
		}
		return list;
	}

	@Override
	public Object findByPrimaryKey(Object obj) throws Exception {
		Model model = (Model) super.findByPrimaryKey(obj);
		DaoBrand daoBrand = new DaoBrand();
		model.setBrand((Brand) daoBrand.findByPrimaryKey(model.getBrand()));
		return model;
	}
}