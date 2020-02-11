package br.edu.udc.sistemas.ia6.emanuelvictor.controller;

import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import br.edu.udc.sistemas.ia6.emanuelvictor.factory.FactoryEntity;
import br.edu.udc.sistemas.ia6.emanuelvictor.session.Session;

public abstract class Controller {

	protected String entityName;
	protected String action;
	protected Class <?> entityClass;
	protected HttpServletRequest request;
	protected Object obj;
	protected Boolean saveFilter;
	
	public void goNew() throws Exception {
		request.setAttribute("nextPage", "./notImplemented.jsp");
	}
	public void goFind() throws Exception  {
		request.setAttribute("nextPage", "./notImplemented.jsp");
	}
	
	public abstract void save(Object obj) throws Exception;
	public abstract void delete(Object obj) throws Exception;
	public abstract void deleteList(Object obj) throws Exception;
	public abstract void detail(Object obj) throws Exception;
	public abstract void find(Object obj) throws Exception;
	public abstract void findByPrimary(Object obj) throws Exception;	


	protected Controller(String entityName) throws Exception {
		this.entityName = entityName;
		this.entityClass = Class.forName("br.edu.udc.sistemas.ia6.emanuelvictor.entity." + entityName);
	}
	
	private HashMap <String, String> getPostData() {		
		Enumeration <String> parameters = this.request.getParameterNames();
		HashMap <String, String> postData = new HashMap<String, String>();
		while(parameters.hasMoreElements()) {
			String paramName = (String) parameters.nextElement();
			if ((!paramName.equals("newAction")) && 
				(!paramName.equals("entityName")) &&
				(!paramName.equals("id"))) {
				postData.put(paramName, this.request.getParameter(paramName));
			}
		}
		return postData;
	}

	private Object getDeleteObject() throws Exception {
		Integer id = null;
		try {
			id = new Integer(Integer.parseInt(request.getParameter("id")));
		} catch (Exception e) {
			return null;
		}
		return FactoryEntity.createById(id,this. entityClass);
	}
	
	private Object getFilterObject() {
		if (this.action.equals("find")) {
			this.request.getSession().setAttribute("filter" + this.entityName, this.obj);
		} else if ((this.action.equals("goNew")) || (this.action.equals("clean"))) {
			this.request.getSession().removeAttribute("filter" + this.entityName);
		} else if ((this.action.equals("goFind")) || (this.action.equals("delete"))) {
			return (Object) this.request.getSession().getAttribute("filter" + this.entityName);
 		}
		return null;
	}
	
	public void execute(HttpServletRequest request, String action) throws Exception {
		this.request = request;
		this.action = action;
		this.request.setAttribute("action", this.action);
		this.obj = FactoryEntity.createByPost(this.getPostData(), this.entityClass);
		Object objFilter = this.getFilterObject();
		Object objId = this.getDeleteObject();
		
		Class <?> sessionClass = Class.forName("br.edu.udc.sistemas.ia6.emanuelvictor.session.Session" + this.entityName);
		Session session = (Session) sessionClass.newInstance();
		Object objReturn = null;
		try {
			Method sessionMethod = sessionClass.getMethod(this.action, Object.class);
			if (objId != null) {
				objReturn = sessionMethod.invoke(session, objId);
			} else {
				objReturn = sessionMethod.invoke(session, this.obj);
			}
			
			if (objFilter != null) {
				this.action = "find";
				sessionMethod = sessionClass.getMethod(this.action, Object.class);
				objReturn = sessionMethod.invoke(session, objFilter);
			}
			
			Method controllerMethod = this.getClass().getMethod(this.action, Object.class);
 			controllerMethod.invoke(this,objReturn);
			
		} catch (NoSuchMethodException e) {
			if (objFilter != null) {
				this.action = "find";
				Method sessionMethod = sessionClass.getMethod(this.action, Object.class);
				objReturn = sessionMethod.invoke(session, objFilter);
				
				Method controllerMethod = this.getClass().getMethod(this.action, Object.class);
	 			controllerMethod.invoke(this,objReturn);
			} else {
				Method controllerMethod = this.getClass().getMethod(this.action);
				controllerMethod.invoke(this);
			}
		}
	}

	public void lastFind(Object obj) throws Exception{
		this.find(obj);
	}
	
}