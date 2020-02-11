package br.edu.udc.sistemas.ia6.emanuelvictor.controller;

public class ControllerJuridicClient extends Controller {

	public ControllerJuridicClient() throws Exception {
		super("Client");
	}

	@Override
	public void goNew() {
		request.setAttribute("nextPage", "./client/keepJuridicClient.jsp");
	}

	@Override
	public void goFind() {
		request.setAttribute("nextPage", "./client/consultJuridicClient.jsp");
	}

	@Override
	public void save(Object obj) {
		this.goNew();
	}

	@Override
	public void delete(Object obj) {
		this.goFind();
	}

	@Override
	public void find(Object obj) {
		request.setAttribute("list", obj);
		this.goFind();
	}

	@Override
	public void detail(Object obj) {
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