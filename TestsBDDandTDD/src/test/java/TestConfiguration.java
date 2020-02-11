


import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.Locale;

/**
 * Created by emanuelvictor on 02/09/14.
 */
public class TestConfiguration extends JUnitStory {

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new GreenToYellow());
    }

        public Configuration configuration() {

        Keywords keywords = new LocalizedKeywords(new Locale("pt"));
            return new MostUsefulConfiguration()
                    .useKeywords(keywords)
                    .useStoryLoader(new RegexStoryParser(keywords))
                            // Onde procurar pelas estórias
                    .useStoryLoader(new LoadFromClasspath(this.getClass()))
                            // Para onde fazer os reports
                    .useStoryReporterBuilder(new StoryReporterBuilder()
//                            .withDefaultFormats()
                            .withFormats(StoryReporterBuilder.Format.CONSOLE, StoryReporterBuilder.Format.HTML));

    }

}
