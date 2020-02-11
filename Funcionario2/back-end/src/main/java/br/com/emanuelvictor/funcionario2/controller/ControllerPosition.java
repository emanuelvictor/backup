package br.com.emanuelvictor.funcionario2.controller;


import br.com.emanuelvictor.funcionario2.entity.position.Position;
import br.com.emanuelvictor.funcionario2.service.ServicePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerPosition {

    @Autowired
    ServicePosition servicePosition;


    @RequestMapping(value = "/positions", method = RequestMethod.POST)
    public @ResponseBody Position postPosition(@RequestBody Position position) {
        return this.servicePosition.save(position);
    }

    @RequestMapping(value = "/positions", method = RequestMethod.GET)
    public @ResponseBody List<Position> getPositions() {
        return this.servicePosition.find();
    }

    @RequestMapping(value = "/positions/{id}", method = RequestMethod.GET)
    public @ResponseBody Position getPosition(@PathVariable("id") Integer id) {
        return this.servicePosition.find(id);
    }

    @RequestMapping(value = "/positions/{id}", method = RequestMethod.DELETE)
    public void deletePosition(@PathVariable("id") Integer id) {
        this.servicePosition.delete(id);
    }

}
