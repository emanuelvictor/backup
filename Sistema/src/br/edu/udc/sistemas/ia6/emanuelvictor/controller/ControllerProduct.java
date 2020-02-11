package br.edu.udc.sistemas.ia6.emanuelvictor.controller;

import br.edu.udc.sistemas.ia6.emanuelvictor.entity.Brand;
import br.edu.udc.sistemas.ia6.emanuelvictor.entity.Supplier;
import br.edu.udc.sistemas.ia6.emanuelvictor.entity.Model;
import br.edu.udc.sistemas.ia6.emanuelvictor.session.SessionBrand;
import br.edu.udc.sistemas.ia6.emanuelvictor.session.SessionSupplier;
import br.edu.udc.sistemas.ia6.emanuelvictor.session.SessionModel;

public class ControllerProduct extends Controller {

	public ControllerProduct() throws Exception {
		super("Product");
	}

	@Override
	public void goNew() throws Exception {
		SessionSupplier sessionSupplier = new SessionSupplier();
		SessionModel sessionModel = new SessionModel();
		SessionBrand sessionBrand = new SessionBrand();
		Object listModel[] = sessionModel.find(new Model());
		Object listSupplier[] = sessionSupplier.find(new Supplier());
		Object listBrand[] = sessionBrand.find(new Brand());
		request.setAttribute("listBrand", listBrand);
		request.setAttribute("listSupplier", listSupplier);
		request.setAttribute("listModel", listModel);
		request.setAttribute("nextPage", "./product/keepProduct.jsp");
	}

	@Override
	public void goFind() throws Exception {
		SessionSupplier sessionSupplier = new SessionSupplier();
		SessionModel sessionModel = new SessionModel();
		SessionBrand sessionBrand = new SessionBrand();
		Object listSupplier[] = sessionSupplier.find(new Supplier());
		Object listModel[] = sessionModel.find(new Model());
		Object listBrand[] = sessionBrand.find(new Brand());
		request.setAttribute("listBrand", listBrand);
		request.setAttribute("listSupplier", listSupplier);
		request.setAttribute("listModel", listModel);
		request.setAttribute("nextPage", "./product/consultProduct.jsp");
	}

	// Acima páginas de navegação

	@Override
	public void save(Object obj) throws Exception {
		this.goNew();
	}

	@Override
	public void delete(Object obj) throws Exception {
		this.goFind();
	}

	@Override
	public void find(Object obj) throws Exception {
		request.setAttribute("list", obj);
		this.goFind();
	}

	@Override
	public void detail(Object obj) throws Exception {
		request.setAttribute("object", obj);
		this.goNew();
	}

	@Override
	public void lastFind(Object obj) throws Exception {
		super.lastFind(obj);
	}

	@Override
	public void deleteList(Object obj) throws Exception {

	}

	@Override
	public void findByPrimary(Object obj) throws Exception {

	}

}