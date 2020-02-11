package br.org.pti.senha.domain.repository.impl;

import br.org.pti.senha.domain.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Repository
public class UserRepoImpl implements UserRepo {

    private LdapTemplate ldapTemplate;

    @Autowired
    public UserRepoImpl(final LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    @Override
    public List<String> getAllPersonNames() {
        return ldapTemplate.search(
                query().where("objectclass").is("person"),
                (AttributesMapper<String>) attrs -> (String) attrs.get("cn").get());
    }
}
