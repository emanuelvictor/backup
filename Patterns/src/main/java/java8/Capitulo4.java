package java8;

/**
 * Created by emanuelvictor on 27/10/15.
 */
public class Capitulo4 {
    public static void main(String... args) {
//        Usuario user1 = new Usuario("Paulo Silveira", 150);
//        Usuario user2 = new Usuario("Rodrigo Turini", 120);
//        Usuario user3 = new Usuario("Guilherme Silveira", 190);
//
//        List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
//
//        Consumer<Usuario> mostraMensagem = u -> System.out.println("antes de imprimir os nome");
//
//        Consumer<Usuario> imprimeNome = u -> System.out.println(u.getNome());
//
//        usuarios.forEach(mostraMensagem.andThen(imprimeNome));

//        Podemos então ter implementações comuns de Consumer, e utilizá-las
//        em diferentes momentos do nosso código, passando-os como argumentos e depois
//        compondo-os de maneira a reutilizá-los. Por exemplo, se você tem um
//        Consumer<Usuario> auditor que guarda no log que aquele usuário realizou
//        algo no sistema, você pode reutilizá-lo, com ou sem o andThen. Um bom exemplo
//        do pattern decorator.

//// A classe predicado testa uma determinada condição.
//        // a nova função removeIf adicionada a collections testa essa condição e com base no retorno remove ou não o item
//        Predicate<Usuario> predicado = new Predicate<Usuario>() {
//            public boolean test(Usuario u) {
//                return u.getPontos() > 160;
//            }
//        };
//
//        Usuario user1 = new Usuario("Paulo Silveira", 150);
//        Usuario user2 = new Usuario("Rodrigo Turini", 120);
//        Usuario user3 = new Usuario("Guilherme Silveira", 190);
//
//        // a lista devolvida de Arrays.asLista é imutável, e o removeIf, por implementar o remove da collection
//        // não pode remover itens de listas imutáveis
//        List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
//
//
//        usuarios.removeIf(predicado);
//        usuarios.forEach(u -> System.out.println(u));

//        // A classe predicado testa uma determinada condição.
//        // a nova função removeIf adicionada a collections testa essa condição e com base no retorno remove ou não o item
//        Predicate<Usuario> predicado = new Predicate<Usuario>() {
//            public boolean test(Usuario u) {
//                return u.getPontos() > 160;
//            }
//        };
//
//        Usuario user1 = new Usuario("Paulo Silveira", 150);
//        Usuario user2 = new Usuario("Rodrigo Turini", 120);
//        Usuario user3 = new Usuario("Guilherme Silveira", 190);
//
//        List<Usuario> usuarios = new ArrayList<>();
//
//        usuarios.add(user1);
//        usuarios.add(user2);
//        usuarios.add(user3);
//
//        usuarios.removeIf(predicado);
//        usuarios.forEach(u -> System.out.println(u.getNome()));

//        //MESMO EXEMPLO ANTERIOR, utilizando-se lambda
//        Usuario user1 = new Usuario("Paulo Silveira", 150);
//        Usuario user2 = new Usuario("Rodrigo Turini", 120);
//        Usuario user3 = new Usuario("Guilherme Silveira", 190);
//
//        List<Usuario> usuarios = new ArrayList<>();
//
//        usuarios.add(user1);
//        usuarios.add(user2);
//        usuarios.add(user3);
//
//        // Observe o lambda do predicado
//        usuarios.removeIf(u -> u.getPontos() > 160);
//        usuarios.forEach(u -> System.out.println(u.getNome()));

    }
}
