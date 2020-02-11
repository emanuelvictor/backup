package br.com.agenciaiguassu.application.controller.schooling;

import br.com.agenciaiguassu.domain.entity.schooling.CategoriaCurso;
import br.com.agenciaiguassu.domain.service.schooling.ServiceCategoriaCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerCategoria {
    @Autowired
    ServiceCategoriaCurso serviceCategoriaCurso;

    // Paises
    @RequestMapping(value = "/categoriascursos", method = RequestMethod.POST)
    public
    @ResponseBody
    Object salve(@RequestBody CategoriaCurso categoriaCurso) {
        return this.serviceCategoriaCurso.save(categoriaCurso);
    }

    @RequestMapping(value = "/categoriascursos", method = RequestMethod.GET)
    public
    @ResponseBody
    Object find(
            @RequestParam(required = false) String nome) {
        return serviceCategoriaCurso.find(nome);
    }

    @RequestMapping(value = "/categoriascursos/{id}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    void delete(@PathVariable Long id) {
        serviceCategoriaCurso.delete(id);
    }

}
