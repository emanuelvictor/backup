package br.com.emanuelvictor.controlf.web.domain.entity.tenanty;


import br.com.emanuelvictor.controlf.web.domain.entity.master.user.People;

import javax.persistence.*;

/**
 * Created by emanuelvictor on 04/04/16.
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Movimentation {

    //TODO no futuro inlcuir uma pessoa física ou jurídica na movimentação.
    // Desta forma o usuário pode identificar pra quem esta devendo o u quem esta devendo pra ele
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
    @Column(nullable = false)
    private Double value;

    /**
     *
     */
    @Column(nullable = true)
    private String description;

    /**
     *
     */
    @ManyToOne(optional = false)
    private Category category;

    /**
     *
     */
    @ManyToOne(optional = false)
    private People people;

    /*-------------------------------------------------------------------
	 * 		 					BEHAVIORS
	 *-------------------------------------------------------------------*/

    /*-------------------------------------------------------------------
	 * 		 					SETTERS AND GETTERS
    *-------------------------------------------------------------------*/

    /**
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return
     */
    public Double getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return
     */
    public People getPeople() {
        return people;
    }

    /**
     * @param people
     */
    public void setPeople(People people) {
        this.people = people;
    }

}
