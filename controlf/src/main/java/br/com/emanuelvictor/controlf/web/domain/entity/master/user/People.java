package br.com.emanuelvictor.controlf.web.domain.entity.master.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.Set;

/**
 * Created by emanuelvictor on 04/04/16.
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(schema = "public")
public class People implements UserDetails {

     /*-------------------------------------------------------------------
     * 		 					STATIC
	 *-------------------------------------------------------------------*/

    /*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/

    /*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     *
     */
    @Column(nullable = true)
    private Calendar inactive;

    /**
     *
     */
    @Column(nullable = false)
    private String password;

    /**
     *
     */
    @Column(nullable = false, unique = true)
    private String username;


    /*-------------------------------------------------------------------
	 * 		 					BEHAVIORS
	 *-------------------------------------------------------------------*/

    /**
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //TODO
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     *
     * @return
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        //TODO
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        //TODO
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        //TODO
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        //TODO
        return true;
    }

    /*-------------------------------------------------------------------
	 * 		 					SETTERS AND GETTERS
	 *-------------------------------------------------------------------*/

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public Calendar getInactive() {
        return inactive;
    }

    /**
     *
     * @param inactive
     */
    public void setInactive(Calendar inactive) {
        this.inactive = inactive;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
