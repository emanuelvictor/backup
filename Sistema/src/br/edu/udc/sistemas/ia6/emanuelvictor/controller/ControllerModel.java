package br.edu.udc.sistemas.ia6.emanuelvictor.controller;

import br.edu.udc.sistemas.ia6.emanuelvictor.entity.Brand;
import br.edu.udc.sistemas.ia6.emanuelvictor.session.SessionBrand;

public class ControllerModel extends Controller {

	public ControllerModel() throws Exception {
		super("Model");
	}

	@Override
	public void goNew() throws Exception {
		SessionBrand sessionBrand = new SessionBrand();
		Object listBrand[] = sessionBrand.find(new Brand());
		request.setAttribute("listBrand", listBrand);
		request.setAttribute("nextPage", "./model/keepModel.jsp");
	}

	@Override
	public void goFind() throws Exception {
		SessionBrand sessionBrand = new SessionBrand();
		Object listBrand[] = sessionBrand.find(new Brand());
		request.setAttribute("listBrand", listBrand);
		request.setAttribute("nextPage", "./model/consultModel.jsp");

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
