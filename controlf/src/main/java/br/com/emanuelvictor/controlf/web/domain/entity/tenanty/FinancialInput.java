package br.com.emanuelvictor.controlf.web.domain.entity.tenanty;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Created by emanuelvictor on 04/04/16.
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class FinancialInput extends Movimentation {
    /*-------------------------------------------------------------------
     * 		 					STATIC
	 *-------------------------------------------------------------------*/

    /*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/

    /*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/

    /*-------------------------------------------------------------------
	 * 		 					BEHAVIORS
	 *-------------------------------------------------------------------*/

    /*-------------------------------------------------------------------
	 * 		 					SETTERS AND GETTERS
    *-------------------------------------------------------------------*/
}
