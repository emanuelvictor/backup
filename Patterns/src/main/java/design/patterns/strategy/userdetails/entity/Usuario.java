package design.patterns.strategy.userdetails.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 *
 */
public class Usuario implements UserDetails {
    /*-------------------------------------------------------------------
     *				 		     ATTRIBUTES
     *-------------------------------------------------------------------*/
    /**
     *
     */
    protected Perfil perfil;
    /**
     *
     */
    protected String username;
    /**
     *
     */
    protected String password;

    /*-------------------------------------------------------------------
     *						GETTERS AND SETTERS
     *-------------------------------------------------------------------*/
    /**
     *
     */
    public Perfil getPerfil() {
        return this.perfil;
    }
    /**
     *
     */
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    /**
     *
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     *
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /*-------------------------------------------------------------------
     *							BEHAVIORS
	 *-------------------------------------------------------------------*/
    /**
     *
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getPerfil().getAuthorities();
    }
    /**
     *
     */
    @Override
    public String getPassword() {
        return this.password;
    }
    /**
     *
     */
    @Override
    public String getUsername() {
        return this.username;
    }
    /**
     *
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }
    /**
     *
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }
    /**
     *
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
    /**
     *
     */
    @Override
    public boolean isEnabled() {
        return false;
    }

    /**
     * Imprime todas as roles do usuário
     */
    public void displayRoles() {
        System.out.println(" Eu tenho poderes de: ");
        this.getPerfil().getAuthorities().forEach(authority -> System.out.println("  " + authority));
    }
    /**
     * Imprime todas o perfil do usuário
     */
    public void displayMe(){
            System.out.println("Eu sou um " + this.getPerfil().name());
    }
}
