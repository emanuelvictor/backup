package br.com.limittraining.app.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.limittraining.app.entity.user.Usuario;



/**
 * Created by emanuelvictor on 31/03/16.
 */
public class SchemaResolver implements CurrentTenantIdentifierResolver {

    @Override
    public String resolveCurrentTenantIdentifier() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//            if (usuario.getPerfil() == Perfil.ALUNO) {
//                System.out.println("Perfil aluno"); //TODO
//                return "schema_" + usuario.getId().toString();
//            } else if (usuario.getPerfil() == Perfil.PERSONAL_TRAINER || usuario.getPerfil() == Perfil.FISIOTERAPEUTA) {
//                System.out.println("Perfil professor"); //TODO
                return "schema_" + usuario.getId().toString();
//            }
        }
        System.out.println("Perfil administrador"); //TODO
        return "public";
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return false;
    }
}