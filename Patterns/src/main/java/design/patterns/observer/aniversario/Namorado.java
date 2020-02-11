package design.patterns.observer.aniversario;

import java.util.Scanner;

/**
 * Created by emanuelvictor on 29/10/15.
 */
public class Namorado extends Thread {


    public Scanner getEvento() {
        return new Scanner(System.in);
    }
}
