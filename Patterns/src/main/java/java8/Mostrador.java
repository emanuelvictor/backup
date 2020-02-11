package java8;

import java.util.function.Consumer;

/**
 * Created by emanuelvictor on 27/10/15.
 */
class Mostrador implements Consumer<Usuario> {

    @Override
    public void accept(Usuario usuario) {
        System.out.println(usuario.getNome());
    }
}
