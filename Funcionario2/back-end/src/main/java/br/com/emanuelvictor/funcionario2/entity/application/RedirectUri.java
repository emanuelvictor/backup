package br.com.emanuelvictor.funcionario2.entity.application;

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
@JsonIgnoreProperties({"application"})
public class RedirectUri {

    private static final long serialVersionUID = 1L;

    @Id
    private Long redirectUriId;

    //    private WEBApplication WEBApplication;
    @Column
    private URI uri;

    @ManyToOne(optional = false)
    private Application application;

    public Long getRedirectUriId() {
        return redirectUriId;
    }

    public void setRedirectUriId(Long redirectUriId) {
        this.redirectUriId = redirectUriId;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
