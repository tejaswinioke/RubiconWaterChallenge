package runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * TestRunner class to execute BDD features
 */

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinition"},       
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt"
})
public class TestRunner extends AbstractTestNGCucumberTests{
    
    @DataProvider
    public Object[][] features() {
    	 return super.scenarios();
    }
 
  
}
