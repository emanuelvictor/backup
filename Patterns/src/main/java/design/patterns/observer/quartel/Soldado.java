package design.patterns.observer.quartel;

import java.util.Scanner;

/**
 * Created by emanuelvictor on 28/10/15.
 */
public class Soldado implements Observador {
    @Override
    public void chegou(Scanner scanner) {

        int valor = scanner.nextInt();

        if (valor == 1) {
            System.out.println("Deu merda corre negada");
            throw new RuntimeException("Deu merda");
        } else {
            System.out.println("Alarme falso!");
        }

    }
}
