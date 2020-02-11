package br.com.emanuelvictor.controlf.web.domain.entity.tenanty;

import javax.persistence.*;

/**
 * Created by emanuelvictor on 04/04/16.
 */
@Entity
public class Category {

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
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    /**
     *
     */
    @Column(nullable = false)
    private String description;

    /**
     *
     */
    @Column(nullable = false, unique = true)
    private String identifier;

    /**
     * categoria superior
     */
    @ManyToOne
    private Category cateogory;

    /*-------------------------------------------------------------------
	 * 		 					BEHAVIORS
	 *-------------------------------------------------------------------*/
    /**
     *
     * @return
     */
    public String getIdentifier() {
        if (this.getCateogory() != null && this.getDescription() != null)
            identifier = this.getCateogory().getIdentifier() + "." + this.getDescription();
        else if (this.getCateogory() == null && this.getDescription() != null)
            identifier = this.getDescription();
        return this.identifier;
    }

    /**
     *
     * @param identifier
     */
    public void setIdentifier(String identifier) {
        if (this.getCateogory() != null && this.getDescription() != null)
            this.identifier = this.getCateogory().getIdentifier() + "." + this.getDescription();
        else if (this.getCateogory() == null && this.getDescription() != null)
            this.identifier = this.getDescription();
        else
            this.identifier = identifier;
    }
    /*-------------------------------------------------------------------
	 * 		 					SETTERS AND GETTERS
    *-------------------------------------------------------------------*/

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
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return
     */
    public Category getCateogory() {
        return cateogory;
    }

    /**
     * @return
     */
    public void setCateogory(Category cateogory) {
        this.cateogory = cateogory;
    }


}
