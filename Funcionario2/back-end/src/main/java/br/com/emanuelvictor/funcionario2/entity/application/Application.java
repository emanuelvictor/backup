package br.com.emanuelvictor.funcionario2.entity.application;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import java.util.*;

/**
 * Created by emanuelvictor on 03/05/15.
 */
@Entity
public class Application implements ClientDetails {

    private static final long serialVersionUID = 1L;

    @Id
    private String clientId;

    private String clientSecret;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "application")
    private Set<Scope> scopes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "application")
    private Set<RedirectUri> registeredRedirectUris;

    @Column(nullable = false)
    @ElementCollection(targetClass = GrantType.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "grant_type", joinColumns = @JoinColumn(name = "client_id"))
    private Set<GrantType> authorizedGrantTypes;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;


    @Override
    public Set<String> getScope() {
        Set<String> stringScopes = new HashSet<String>();
        this.scopes.forEach(scope -> stringScopes.add(scope.getDescription()));
        return stringScopes;
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

    //NAO E NECESSARIO
    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    //nao e necessario
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new LinkedList<GrantedAuthority>();
        return list;
    }

    public Set<Scope> getScopes() {
        return scopes;
    }

    public void setScopes(Set<Scope> scopes) {
        this.scopes = scopes;
    }

    public Set<RedirectUri> getRegisteredRedirectUris() {
        return registeredRedirectUris;
    }

    public void setRegisteredRedirectUris(Set<RedirectUri> registeredRedirectUris) {
        this.registeredRedirectUris = registeredRedirectUris;
    }
}
