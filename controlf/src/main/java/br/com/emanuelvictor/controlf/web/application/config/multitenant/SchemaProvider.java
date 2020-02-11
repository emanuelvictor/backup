package br.com.emanuelvictor.controlf.web.application.config.multitenant;

import br.com.emanuelvictor.controlf.web.domain.entity.master.user.People;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by emanuelvictor on 06/04/16.
 */
public class SchemaProvider implements CurrentTenantIdentifierResolver {

    @Override
    public String resolveCurrentTenantIdentifier() {
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && ((People) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId() != 1) {
            System.out.println("ROLOU");
            return "schema_" + ((People) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId().toString();
        }
        return "public";
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return false;
    }
}