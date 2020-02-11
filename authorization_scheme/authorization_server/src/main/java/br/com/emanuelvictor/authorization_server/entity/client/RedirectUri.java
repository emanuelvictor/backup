package br.com.emanuelvictor.authorization_server.entity.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.net.URI;

/**
 * Created by emanuelvictor on 03/05/15.
 */
@Entity
@JsonIgnoreProperties({"client"})
public class RedirectUri {

    @Id
    private Integer redirectUriId;

    @Column
    private URI uri;

    @ManyToOne(optional = false)
    private Client client;

    public Integer getRedirectUriId() {
        return redirectUriId;
    }

    public void setRedirectUriId(Integer redirectUriId) {
        this.redirectUriId = redirectUriId;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
