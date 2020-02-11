package br.edu.udc.sistemas.ia6.emanuelvictor.controller;


public class ControllerBrand extends Controller {
	
	public ControllerBrand() throws Exception {
		super("Brand");
	}

	@Override
	public void goNew()  {
		request.setAttribute("nextPage", "./brand/keepBrand.jsp");
	}

	@Override
	public void goFind()  {
		request.setAttribute("nextPage", "./brand/consultBrand.jsp");

	}
	
	//Acima páginas de navegação

	@Override
	public void save(Object obj){
		this.goNew();
	}

	@Override
	public void delete(Object obj) throws Exception {
	}

	@Override
	public void find(Object obj)  {
		request.setAttribute("list",obj);
		this.goFind();
	}

	@Override
	public void detail(Object obj)  {
		request.setAttribute("object",obj);
		this.goNew();
	}
	
		
	@Override
	public void lastFind(Object obj) throws Exception{
		super.lastFind(obj);
	}

	@Override
	public void deleteList(Object obj) throws Exception {
		this.find(obj);
	}

	@Override
	public void findByPrimary(Object obj) throws Exception {
	}
}
