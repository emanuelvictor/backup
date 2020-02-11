package design.patterns.observer.quartel;

/**
 *
 */
public class TestsObserverPatternQuartel {

//    @Test
//    public void testFuga(){
//
//        Soldado soldado = new Soldado();
//        Sentinela sentinela = new Sentinela();
//        sentinela.avisa(soldado);
//
//        sentinela.start();
//    }

    public static void  main(String ... args){

        Soldado soldado = new Soldado();

        Sentinela sentinela = new Sentinela();

        sentinela.avisa(soldado);

        Evento evento = new Evento();

        sentinela.observar(evento);
    }

}
