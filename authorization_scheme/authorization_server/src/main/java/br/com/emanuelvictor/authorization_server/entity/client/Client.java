package br.com.emanuelvictor.authorization_server.entity.client;

import br.com.emanuelvictor.authorization_server.entity.position.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import java.util.*;

/**
 * Created by emanuelvictor on 03/05/15.
 */
@Entity
public class Client implements ClientDetails {

    @Id
    private String clientId;

    private String clientSecret;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Profile> profiles;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "client")
    private List<RedirectUri> registeredRedirectUris;

    @Column(nullable = false)
    @ElementCollection(targetClass = GrantType.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "grant_type", joinColumns = @JoinColumn(name = "client_id"))
    private Set<GrantType> authorizedGrantTypes;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    @Override
    public Set<String> getScope() {
        Set<String> scopes = new HashSet<String>();
        this.profiles.forEach(profile -> scopes.add(profile.getName()));
        return scopes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        Set<String> stringRedirectUris = new HashSet<String>();
        this.registeredRedirectUris.forEach(redirectUri -> stringRedirectUris.add(redirectUri.getUri().toString()));
        return stringRedirectUris;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        Set<String> stringAuthorizedGrantTypes = new HashSet<String>();
        this.authorizedGrantTypes.forEach(authorizedGrantTypes -> stringAuthorizedGrantTypes.add(authorizedGrantTypes.getGrantType().toString()));
        return stringAuthorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(Set<GrantType> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    @Override
    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String getClientSecret() {
        return this.clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return this.accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return this.refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    @Override
    public boolean isSecretRequired() {
        return false;
    }

    @Override
    public boolean isScoped() {
        return false;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }

    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new LinkedList<GrantedAuthority>();
        return list;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public List<RedirectUri> getRegisteredRedirectUris() {
        return registeredRedirectUris;
    }

    public void setRegisteredRedirectUris(List<RedirectUri> registeredRedirectUris) {
        this.registeredRedirectUris = registeredRedirectUris;
    }
}
