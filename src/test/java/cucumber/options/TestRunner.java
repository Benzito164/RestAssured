package cucumber.options;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;

@CucumberOptions(
		features = "src/test/java/Features",
		glue = {"Steps"},
		tags = "@ecom",
		plugin = "json:target/jsonReports/cucumber-report.json"
)
@RunWith(Cucumber.class)
public class TestRunner {

}
