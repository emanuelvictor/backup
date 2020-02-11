package br.edu.udc.sistemas.ia6.emanuelvictor.controller;

public class ControllerOS extends Controller {

	public ControllerOS() throws Exception {
		super("OS");
	}

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

	public void goNew() throws Exception {
		request.setAttribute("nextPage", "./os/keepOS.jsp");
	}

	public void goFind() throws Exception {
		request.setAttribute("nextPage", "./os/consultOS.jsp");
	}
}
