package br.com.emanuelvictor.controlf.web.application.controller;

import br.com.emanuelvictor.controlf.web.domain.entity.tenanty.Category;
import br.com.emanuelvictor.controlf.web.domain.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by emanuelvictor on 05/04/16.
 */
@RestController
public class CategoryController {

    /**
     *
     */
    @Autowired
    ICategoryRepository categoryRepository;

    /**
     * @return
     */
    @RequestMapping("/categories")
    public
    @ResponseBody
    List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
