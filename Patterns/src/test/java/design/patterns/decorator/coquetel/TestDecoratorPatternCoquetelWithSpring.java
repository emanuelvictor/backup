package design.patterns.decorator.coquetel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDecoratorPatternCoquetelWithSpring.class, loader = SpringApplicationContextLoader.class)
@ComponentScan
@EnableAutoConfiguration
public class TestDecoratorPatternCoquetelWithSpring {

    // Armazenará o contexto da aplicação
    ApplicationContext application = null;


    @Before
    public void setUp() {
        // Instancia o spring
        application = SpringApplication.run(TestDecoratorPatternCoquetelWithSpring.class);
    }

    @Test
    public void testInstanceCoquetel() {
        Coquetel mixCachacaRefrigerante = application.getBean(Cachaca.class);

        Assert.isTrue((mixCachacaRefrigerante.getNome() + " = " + mixCachacaRefrigerante.getPreco()).equals("Cachaça = 1.5"));

        mixCachacaRefrigerante = new Refrigerante(mixCachacaRefrigerante);

        Assert.isTrue((mixCachacaRefrigerante.getNome() + " = " + mixCachacaRefrigerante.getPreco()).equals("CachaçaRefrigerante = 2.5"));
    }
}
