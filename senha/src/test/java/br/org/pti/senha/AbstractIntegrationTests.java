package br.org.pti.senha;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestApplication.class, Application.class})
public abstract class AbstractIntegrationTests {
    public static Logger LOGGER = LoggerFactory.getLogger(AbstractIntegrationTests.class);
}
