package design.patterns.observer.aniversario;

import design.patterns.observer.aniversario.Observador;

import java.util.Scanner;

/**
 * Created by emanuelvictor on 29/10/15.
 */
public class Namorada implements Observador {
    @Override
    public void chegou(Scanner scanner) {

        int valor = scanner.nextInt();

        if (valor == 1) {
            System.out.println("Todo mundo quetinho");
            System.out.println("Silêncio");
            System.out.println("Apaguem as luzes");
            System.out.println("Namorado chegou");
            throw new RuntimeException("Surpresaaaa .... !!!");
        } else {
            System.out.println("Alarme falso!");
        }

    }
}
