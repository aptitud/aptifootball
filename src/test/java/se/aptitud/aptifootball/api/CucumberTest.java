package se.aptitud.aptifootball.api;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Boilerplate code
 */
@RunWith(Cucumber.class)
// print reports, json / xml for teamcity?
@CucumberOptions(format={"pretty",
        "html:target/test-report",
        "json:target/test-report.json",
        "junit:target/test-report.xml"}
        , features="src/test/resources"
        )
public class CucumberTest {
}
