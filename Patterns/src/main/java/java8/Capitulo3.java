package java8;

/**
 * Created by emanuelvictor on 27/10/15.
 */
public class Capitulo3 {
    public static void main(String... args) {
//        //        maneira antiga
//        Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i <= 1000; i++) {
//                    System.out.println(i);
//                }
//            }
//        };
//        new Thread(r).start();

//        // Com lambda
//        Runnable r = () -> {
//            for (int i = 0; i <= 1000; i++) {
//                System.out.println(i);
//            }
//        };
//        new Thread(r).start();

//        //Com lambda maneira mais enxuta
//        new Thread(() -> {
//            for (int i = 0; i <= 1000; i++) {
//                System.out.println(i);
//            }
//        }).start();

////        Lâmbda com buttons
//        new Button().addActionListener(
//                event -> System.out.println("evento do click acionado"));

        ////Utilizando nossa própria interface funcional
//        Validador<String> validadorCEP =
//                valor -> {
//                    return valor.matches("[0-9]{5}-[0-9]{3}");
//                };

// De forma mais resumida
        // O compilador entende o método único da interface, e instancia a classe
//        Validador<String> validadorCEP = valor -> valor.matches("[0-9]{5}-[0-9]{3}");
//        Assert.isTrue(validadorCEP.valida("04101-300"));

// // Vendo a classe gerada pelo lambda
// Runnable o = () -> {
//            System.out.println("O que sou eu? Que lambda?");
//        };
//        System.out.println(o);
//        System.out.println(o.getClass());

//        // O lambda permite ler variáveis finais
//        final int numero = 5;
//        new Thread(() -> {
//            System.out.println(numero);
//        }).start();

//        // O lambda permite ler variáveis que não estejam declaradas como final, se essas se comportarem como final
//        // ou seja se o valor da variável não for alterada
//        int numero = 5;
//        new Thread(() -> {
//            System.out.println(numero);
//        }).start();

//        // o código não compila, pois a variável não comporta-se como final
//        int numero = 5;
//        new Thread(() -> {
//            System.out.println(numero); // não compila
//        }).start();
//        numero = 10; // por causa dessa linha

//        E isso também vale para as classes anônimas a partir do Java 8. Você não precisa
//        mais declarar as variáveis locais como final, basta não alterá-las que o Java vai
//        permitir acessá-las.

//        int num = 4;
//        Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(num);  // não compila
//            }
//        };
//        num = 8; // por causa dessa linha
//        new Thread(r).start();

    }
}
