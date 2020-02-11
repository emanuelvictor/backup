package br.com.agenciaiguassu.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.agenciaiguassu.domain.entity.Empresa;

import br.com.agenciaiguassu.domain.service.ServiceEmpresa;

//TODO
@Controller
public class ControllerEmpresa {
	@Autowired
	ServiceEmpresa serviceEmpresa;
	

	@RequestMapping(value = "/empresas", method = RequestMethod.POST)
	public @ResponseBody Object save(@RequestBody Empresa empresa) {
		return this.serviceEmpresa.save(empresa);
    }

	@RequestMapping(value = "/empresas/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable Long id) {
			serviceEmpresa.delete(id);
	}

	@RequestMapping(value = "/empresas/{id}", method = RequestMethod.GET)
	public @ResponseBody Object find(@PathVariable Long id) {
		return serviceEmpresa.find(id);
	}

    @RequestMapping(value = "/empresas", method = RequestMethod.GET)
    public @ResponseBody Object find() {
        return serviceEmpresa.find();
    }

	@RequestMapping(value = "/empresas/{pagina}", method = RequestMethod.POST)
	public @ResponseBody Object find(@RequestBody(required = false) Empresa empresa,
                                     @PathVariable Integer pagina) {
        return serviceEmpresa.find(empresa, new PageRequest(pagina,20));
	}

}
