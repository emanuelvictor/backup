package br.edu.udc.sistemas.ia6.emanuelvictor.session;

import br.edu.udc.sistemas.ia6.emanuelvictor.dao.Dao;
import br.edu.udc.sistemas.ia6.emanuelvictor.dao.daoDatabase.DaoBrand;
import br.edu.udc.sistemas.ia6.emanuelvictor.dao.daoDatabase.DaoClient;
import br.edu.udc.sistemas.ia6.emanuelvictor.dao.daoDatabase.DaoModel;
import br.edu.udc.sistemas.ia6.emanuelvictor.entity.Brand;
import br.edu.udc.sistemas.ia6.emanuelvictor.entity.Client;
import br.edu.udc.sistemas.ia6.emanuelvictor.entity.Model;
import br.edu.udc.sistemas.ia6.emanuelvictor.entity.Vehicle;
import br.edu.udc.sistemas.ia6.emanuelvictor.factory.FactoryDao;

public class SessionVehicle extends Session {

	public SessionVehicle(Dao dao) {
		super(dao);
	}

	public SessionVehicle() throws Exception {
		super(FactoryDao.createDao(new Vehicle()));
	}
	
	@Override
	public Object[] find(Object obj) throws Exception {
		Object list[] = super.find(obj);
		DaoModel daoModel = new DaoModel();
		DaoClient daoClient = new DaoClient();
		DaoBrand daoBrand = new DaoBrand();
		for (int i = 0; i < list.length; i++) {
			Vehicle vehicle = (Vehicle) list[i];
			vehicle.setModel((Model) daoModel.findByPrimaryKey(vehicle.getModel()));
			vehicle.setClient((Client) daoClient.findByPrimaryKey(vehicle.getClient()));
			vehicle.getModel().setBrand((Brand) daoBrand.findByPrimaryKey(vehicle.getModel().getBrand()));
		}
		return list;
	}

	@Override
	public Object findByPrimaryKey(Object obj) throws Exception {
		Vehicle vehicle = (Vehicle) super.findByPrimaryKey(obj);
		DaoModel daoModel = new DaoModel();
		DaoClient daoClient = new DaoClient();
		DaoBrand daoBrand = new DaoBrand();
		vehicle.setModel((Model) daoModel.findByPrimaryKey(vehicle.getModel()));
		vehicle.setClient((Client) daoClient.findByPrimaryKey(vehicle.getClient()));
		vehicle.getModel().setBrand((Brand) daoBrand.findByPrimaryKey(vehicle.getModel().getBrand()));
		return vehicle;
	}
}