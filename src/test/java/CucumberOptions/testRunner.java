package CucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;



@RunWith(Cucumber.class)
@CucumberOptions(
		features="src\\test\\java\\Features",
		glue="StepDefinitions",tags = "@Smoke",
		stepNotifications = true,
		//dryRun = true,
		monochrome=true,
		plugin= {"pretty","html:target/htmlReport.html","json:target/jsonReport.json","junit:target/junitReport.xml"}
		)
public class testRunner {

}
