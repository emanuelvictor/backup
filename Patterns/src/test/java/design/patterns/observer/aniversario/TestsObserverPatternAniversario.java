package design.patterns.observer.aniversario;

/**
 *
 */
public class TestsObserverPatternAniversario {

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

        Namorada namorada = new Namorada();

        Porteiro porteiro = new Porteiro();

        porteiro.avisa(namorada);

        Namorado namorado = new Namorado();

        porteiro.observar(namorado);
    }

}
