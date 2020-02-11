package br.mil.mar.dabm.autenticador.domain.repository;
import java.util.concurrent.Future;

import br.mil.mar.dabm.autenticador.domain.entity.usuario.Usuario;
 
/**
 * Interface para o envio de e-mails
 *
 * @author rodrigo@eits.com.br
 * @since 02/10/2014
 * @version 1.0
 */
public interface IUsuarioMailRepository
{
    /*-------------------------------------------------------------------
     *                          BEHAVIORS
     *-------------------------------------------------------------------*/
    /**
     * @param usuario
     */
    public Future<Void> sendRecuperarSenha( Usuario usuario );
}