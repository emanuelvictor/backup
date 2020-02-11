package br.com.emanuelvictor.controlf.web.domain.entity.tenanty;

import br.com.emanuelvictor.controlf.web.domain.entity.master.user.People;

import javax.persistence.*;

/**
 * Created by emanuelvictor on 04/04/16.
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Contact {

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
    @ManyToOne(optional = false)
    private People people;
    /*-------------------------------------------------------------------
	 * 		 					BEHAVIORS
	 *-------------------------------------------------------------------*/

    /*-------------------------------------------------------------------
	 * 		 					SETTERS AND GETTERS
    *-------------------------------------------------------------------*/

    /**
     *
     * @return
     */
    public People getPeople() {
        return people;
    }

    /**
     *
     * @param people
     */
    public void setPeople(People people) {
        this.people = people;
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
}
