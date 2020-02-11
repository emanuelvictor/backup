package br.com.emanuelvictor.authorization_server.controller;


import br.com.emanuelvictor.authorization_server.entity.position.Profile;
import br.com.emanuelvictor.authorization_server.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfileController {

    @Autowired
    ProfileService profileService;


    @RequestMapping(value = "/profiles", method = RequestMethod.POST)
    public Profile postPosition(@RequestBody Profile profile) {
        return this.profileService.save(profile);
    }

    @RequestMapping(value = "/profiles", method = RequestMethod.GET)
    public List<Profile> getPositions() {
        return this.profileService.find();
    }

    @RequestMapping(value = "/profiles/{id}", method = RequestMethod.GET)
    public Profile getPosition(@PathVariable("id") Integer id) {
        return this.profileService.find(id);
    }

    @RequestMapping(value = "/profiles/{id}", method = RequestMethod.DELETE)
    public void deletePosition(@PathVariable("id") Integer id) {
        this.profileService.delete(id);
    }

}
