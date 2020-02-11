package design.patterns.observer.aniversario;

import java.util.Scanner;

/**
 * Created by emanuelvictor on 29/10/15.
 */
public class Porteiro extends Thread {

    Namorado namorado = null;

    Observador observador = null;

    public void avisa(Observador observador) {
        this.observador = observador;
    }

    public void observar(Namorado namorado) {
        this.namorado = namorado;
        this.start();
    }

    @Override
    public void run() {

        Scanner scanner = namorado.getEvento();

        while (true) {

            observador.chegou(scanner);

        }
    }
}
