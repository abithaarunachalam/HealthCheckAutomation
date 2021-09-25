package cucumber_runner;

///**
//* This test 'driver' does not execute the actual test cases, but does a strict
//* dry run instead. 
//* <p>
//* A dry run tests the Gherkin syntax for validity. Doing this strictly also
//* checks for all Cucumber glue code (step definitions) being available.
//*/

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
features = ("src/test/features"),
glue = {"resources.steps.prj1"}
)
//plugin = "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html")


   
public class CucumberRunnerTest {

}

