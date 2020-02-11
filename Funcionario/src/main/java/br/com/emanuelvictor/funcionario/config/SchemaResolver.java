package br.com.emanuelvictor.funcionario.config;

import br.com.emanuelvictor.funcionario.entity.public_schema.Funcionario;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by emanuelvictor on 31/03/16.
 */
public class SchemaResolver implements CurrentTenantIdentifierResolver {

    @Override
    public String resolveCurrentTenantIdentifier() {
        if (SecurityContextHolder.getContext().getAuthentication() != null && ((Funcionario) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId() != 1){
            System.out.println("ROLOU");
            return "schema_" + ((Funcionario) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId().toString();
        }

        return "public";
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return false;
    }
}