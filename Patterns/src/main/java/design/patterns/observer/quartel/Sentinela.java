package design.patterns.observer.quartel;

import java.util.Scanner;

/**
 * Created by emanuelvictor on 28/10/15.
 */
public class Sentinela extends Thread {

    Evento evento = null;

    Observador observador = null;

    public void avisa(Observador observador) {
        this.observador = observador;
    }

    public void observar(Evento evento) {
        this.evento = evento;
        this.start();
    }

    @Override
    public void run() {

        Scanner scanner = evento.getEvento();

        while (true) {

            observador.chegou(scanner);

        }
    }
}
