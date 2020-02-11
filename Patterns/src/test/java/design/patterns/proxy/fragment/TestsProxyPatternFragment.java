package design.patterns.proxy.fragment;

import design.patterns.proxy.fragment.configuration.Configuration;
import design.patterns.proxy.fragment.configuration.Position;
import design.patterns.proxy.fragment.configuration.Scale;

/**
 *
 */
public class TestsProxyPatternFragment {

//    @Test
//    public void testFuga(){
//
//        Soldado soldado = new Soldado();
//        Sentinela sentinela = new Sentinela();
//        sentinela.avisa(soldado);
//
//        sentinela.start();
//    }

    public static void main(String... args) {

//        new Activity().getFragments();

        Configuration configuration = Configuration.getInstance();
        configuration.setPosition(Position.LANDSCAPE);

        new Activity();

        configuration.setPosition(Position.LANDSCAPE);
        configuration.setScale(Scale.TABLET);

        new Activity();
    }

}
