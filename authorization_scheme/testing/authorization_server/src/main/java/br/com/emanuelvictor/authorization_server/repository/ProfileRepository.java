package br.com.emanuelvictor.authorization_server.repository;

import br.com.emanuelvictor.authorization_server.entity.position.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@Transactional
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Profile findByName(@Param("name") String name);
}
