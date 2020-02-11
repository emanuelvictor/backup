package design.patterns.strategy.userdetails.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

public enum Perfil implements GrantedAuthority {
    /*-------------------------------------------------------------------
     *				 		     ENUMS
     *-------------------------------------------------------------------*/
    ADMINISTRADOR, // 0
    INSTRUTOR, // 1
    ALUNO; // 2

    public static final String ADMINISTRADOR_VALUE = "ADMINISTRADOR";
    public static final String INSTRUTOR_VALUE = "INSTRUTOR";
    public static final String ALUNO_VALUE = "ALUNO";

    /*
     * (non-Javadoc)
     * @see org.springframework.security.core.GrantedAuthority#getAuthority()
     */
    @Override
    public String getAuthority() {
        return this.name();
    }

    /**
     * @return
     */
    public Set<GrantedAuthority> getAuthorities() {
        final Set<GrantedAuthority> authorities = new HashSet<>();

        authorities.add(this);

        if (this.equals(Perfil.ADMINISTRADOR)) {
            authorities.add(Perfil.INSTRUTOR);
        }

        authorities.add(Perfil.ALUNO);

        return authorities;
    }
}
