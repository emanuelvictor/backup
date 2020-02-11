/**
 * 
 */
package br.com.emanuelvictor.authorization_server.entity.user;

import br.com.emanuelvictor.authorization_server.entity.position.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author emanuelvictor
 *
 */
@Entity
public class UserAccount implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4500885040652391731L;

	@Id
    private String email;

	@Column(length = 100, nullable = false)
	private String name;

    @Column(nullable = false)
	private boolean enabled = true;

	@Column(nullable = false, length = 100000)
	private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Profile> profiles;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        this.profiles.forEach(profile -> authorities.add(new SimpleGrantedAuthority(profile.getName())));
        return authorities;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
