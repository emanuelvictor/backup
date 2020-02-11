package br.com.emanuelvictor.funcionario2.entity.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by emanuelvictor on 03/05/15.
 */
@Entity
@JsonIgnoreProperties({"application"})
public class Scope {

    private static final long serialVersionUID = 1L;

    @Id
    private Long scopeId;

    @ManyToOne(optional = false)
    private Application application;

    @Column
    private String description;

    public Scope() {
    }

    public Scope(Long scopeId, String description, Application application) {
        this.scopeId = scopeId;
        this.description = description;
        this.application = application;
    }

    public Long getScopeId() {
        return scopeId;
    }

    public void setScopeId(Long scopeId) {
        this.scopeId = scopeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
