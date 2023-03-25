package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@smoke",
        features ="src/test/resources/feaatures",
        glue = "step_defs",
        dryRun = false,
        snippets = CucumberOptions.SnippetType.CAMELCASE



)

public class RestRunner {
}
