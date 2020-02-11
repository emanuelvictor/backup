package br.org.pti.senha.domain.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Repository
public class PersonRepo {

    private LdapTemplate ldapTemplate;

    @Autowired
    public PersonRepo(final LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    /**
     * Retrieves all the persons in the ldap server
     *
     * @return list of person names
     */
    public List<String> getAllPersonNames() {
        return ldapTemplate.search(
                query().where("objectclass").is("person"),
                (AttributesMapper<String>) attrs -> (String) attrs.get("cn").get());
    }

}