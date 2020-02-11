package br.edu.udc.sistemas.ia6.emanuelvictor.controller;


public class ControllerSupplier extends Controller {

	public ControllerSupplier() throws Exception {
		super("Supplier");
	}

	@Override
	public void goNew() {
		request.setAttribute("nextPage", "./supplier/keepSupplier.jsp");
	}

	@Override
	public void goFind() {
		request.setAttribute("nextPage", "./supplier/consultSupplier.jsp");
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