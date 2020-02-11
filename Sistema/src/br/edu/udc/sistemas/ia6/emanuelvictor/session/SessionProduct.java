package br.edu.udc.sistemas.ia6.emanuelvictor.session;

import br.edu.udc.sistemas.ia6.emanuelvictor.dao.Dao;
import br.edu.udc.sistemas.ia6.emanuelvictor.dao.daoDatabase.DaoBrand;
import br.edu.udc.sistemas.ia6.emanuelvictor.dao.daoDatabase.DaoModel;
import br.edu.udc.sistemas.ia6.emanuelvictor.dao.daoDatabase.DaoSupplier;
import br.edu.udc.sistemas.ia6.emanuelvictor.entity.Brand;
import br.edu.udc.sistemas.ia6.emanuelvictor.entity.Model;
import br.edu.udc.sistemas.ia6.emanuelvictor.entity.Product;
import br.edu.udc.sistemas.ia6.emanuelvictor.entity.Supplier;
import br.edu.udc.sistemas.ia6.emanuelvictor.factory.FactoryDao;

public class SessionProduct extends Session {

	public SessionProduct(Dao dao) {
		super(dao);
	}

	public SessionProduct() throws Exception {
		super(FactoryDao.createDao(new Product()));
	}
	
	@Override
	public Object[] find(Object obj) throws Exception {
		Object list[] = super.find(obj);
		DaoModel daoModel = new DaoModel();
		DaoSupplier daoSupplier = new DaoSupplier();
		DaoBrand daoBrand = new DaoBrand();
		for (int i = 0; i < list.length; i++) {
			Product product = (Product) list[i];
			product.setModel((Model) daoModel.findByPrimaryKey(product.getModel()));
			product.setSupplier((Supplier) daoSupplier.findByPrimaryKey(product.getSupplier()));
			product.getModel().setBrand((Brand) daoBrand.findByPrimaryKey(product.getModel().getBrand()));
		}
		return list;
	}

	@Override
	public Object findByPrimaryKey(Object obj) throws Exception {
		Product product = (Product) super.findByPrimaryKey(obj);
		DaoModel daoModel = new DaoModel();
		DaoSupplier daoSupplier = new DaoSupplier();
		DaoBrand daoBrand = new DaoBrand();
		product.setModel((Model) daoModel.findByPrimaryKey(product.getModel()));
		product.setSupplier((Supplier) daoSupplier.findByPrimaryKey(product.getSupplier()));
		product.getModel().setBrand((Brand) daoBrand.findByPrimaryKey(product.getModel().getBrand()));
		return product;
	}
}