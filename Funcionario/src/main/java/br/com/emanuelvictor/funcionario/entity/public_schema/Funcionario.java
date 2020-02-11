package br.com.emanuelvictor.funcionario.entity.public_schema;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.directwebremoting.annotations.DataTransferObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@DataTransferObject(/*type = "hibernate3",
        params = @Param(name = "exclude", value = "password")*/)
@Table(schema = "public")
public class Funcionario extends PessoaFisica implements UserDetails, Serializable {

    private static final long serialVersionUID = 1121147605946406759L;

    @Column(length = 50, unique = true, nullable = false)
    private String email;

    @Column(nullable = false, length = 100000)
    private String password;

    @ManyToOne(optional = false)
    private Cargo cargo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    //AUTENTICAÇÃO
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + "ADMINISTRADOR"/*this.getPerfil()*/);
//        ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        authorities.add(authority);
        return null;
    }

}
