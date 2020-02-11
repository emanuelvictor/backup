package br.com.emanuelvictor.controlf.web.domain.repository;

import br.com.emanuelvictor.controlf.web.domain.entity.master.user.People;
import br.com.emanuelvictor.controlf.web.domain.entity.tenanty.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by emanuelvictor on 05/04/16.
 */
public interface IPeopleRepository extends JpaRepository<People, Long> {
    People findByUsername(String username);
}
