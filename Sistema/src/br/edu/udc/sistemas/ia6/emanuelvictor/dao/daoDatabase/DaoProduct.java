package br.edu.udc.sistemas.ia6.emanuelvictor.dao.daoDatabase;

import java.sql.Connection;

import br.edu.udc.sistemas.ia6.emanuelvictor.entity.Brand;
import br.edu.udc.sistemas.ia6.emanuelvictor.entity.Model;
import br.edu.udc.sistemas.ia6.emanuelvictor.entity.Product;

public class DaoProduct extends DaoDatabase {

	public DaoProduct() throws Exception {
		super();
	}

	public DaoProduct(Connection con) throws Exception {
		super(con);
	}
	@Override
	public void save(Object obj) throws Exception {

		Product product = (Product) obj;
		Model model = (Model) this.findByPrimaryKey(product.getModel());
		Brand brand = (Brand) this.findByPrimaryKey(model.getBrand());
		product.setBrand(brand.getDescription());
		super.save(obj);
	}
	
}
