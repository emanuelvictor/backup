package br.com.emanuelvictor.authorization_server.service;

import br.com.emanuelvictor.authorization_server.entity.position.Profile;
import br.com.emanuelvictor.authorization_server.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

	@Autowired
	ProfileRepository profileRepository;

	public Profile save(Profile profile) {
		return this.profileRepository.save(profile);
	}


	public Profile find(Integer id) {
		return this.profileRepository.findOne(id);
	}

	public void delete(Integer id) {
		this.profileRepository.delete(id);
	}

	public List<Profile> find() {
		return this.profileRepository.findAll();
	}

}
