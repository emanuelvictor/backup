/**
 * 
 */
package br.mil.mar.dabm.autenticador.infrastructure.dao;

import br.mil.mar.dabm.singra.domain.entity.OrganizacaoMilitar;
import br.mil.mar.dpmm.rh.domain.entity.Pessoa;

/**
 * @author emanuelvictor
 *         Classe auxiliar contendo pessoas e organizações militares em memória
 */
public class Memory
{
	/*-------------------------------------------------------------------
	 *						Esquema
	 *-------------------------------------------------------------------*/

	Pessoa test = new Pessoa( 1007L, null, "01270848194", "test", "Usuario Test Autenticador", "test@mailinator.com", "T. Autenticador", "Primeiro Sargento" );
	
	OrganizacaoMilitar MAR = new OrganizacaoMilitar( 2001L, "Marinha do Brasil", null, test, "MAR" , "(45) 3577-6544");
	
	Pessoa administrador = new Pessoa( 1001L, MAR, "59655284000", "administrador", "Usuário Administrador Autenticador", "administrador@mailinator.com", "ADM. Autenticador", "Almirante" );
		
	OrganizacaoMilitar DAbM = new OrganizacaoMilitar( 2002L, "Departamento de abastecimento da Marinha", MAR, null,/*.administrador,*/ "DAbM" , "(45) 3577-6543");
	
	Pessoa administrador_gos = new Pessoa( 1002L, DAbM, "53623183733", "administrador_gos", "Usuário Administrador GOS", "administrador_gos@mailinator.com", "ADM. GOS", "Almirante de Esquadra" );
	
	OrganizacaoMilitar DPMM = new OrganizacaoMilitar( 2003L, "Departamento de planejamento da Marinha", MAR, administrador_gos, "DPMM" , "(45) 3577-6519");

	Pessoa administrador_pdti = new Pessoa( 1003L, DPMM, "77371355709", "administrador_pdti", "Usuário Administrador PDTI", "administrador_pdti@mailinator.com", "ADM. PDTI", "Capitão de Fragata" );
	
	OrganizacaoMilitar COM = new OrganizacaoMilitar( 2004L, "Centro de Obtenção da Marinha", DPMM, administrador_pdti, "COM", "(45) 3577-6654" );
	
	Pessoa usuario = new Pessoa( 1004L, DAbM, "92174671204", "usuario", "Usuário Normal Autenticador", "usuario@mailinator.com", "N. Autenticador", "Primeiro Tenente");
	
	Pessoa usuario_gos = new Pessoa( 1005L, DAbM, "58148237557", "usuario_gos", "Usuário Normal GOS", null, "N. GOS", "Segundo Tenente" );
	
	Pessoa usuario_pdti = new Pessoa( 1006L, DAbM, "33333819740", "usuario_pdti", "Usuario Normal PDTI", "usuario_pdti@mailinator.com", "N. PDTI", "Suboficial");
	
	Pessoa TestSynchronize = new Pessoa( 1008L, DAbM, "01270844564", "TestSynchronize", "TestSynchronize", "TestSynchronize@mailinator.com", "T. S. Autenticador", "Primeiro Sargento");

	
	/*-------------------------------------------------------------------
	 *						Arrays
	 *-------------------------------------------------------------------*/
	
	public final OrganizacaoMilitar[] ORGANIZACOES_MILITARES = new OrganizacaoMilitar[]
	{

			MAR,
					 
			DAbM,
					 
			DPMM,
					
			COM

	};

	public final Pessoa[] PESSOAS = new Pessoa[]
	{

		administrador,
		
		administrador_gos,
		
		administrador_pdti,
		
		usuario,
		
		usuario_gos,
		
		usuario_pdti,
		
		test,
		
		TestSynchronize

	};

}
