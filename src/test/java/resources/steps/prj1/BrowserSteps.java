package resources.steps.prj1;

import javax.swing.text.Utilities;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LaunchPage;
import utils.CommonUtil;
import utils.ExecLog;
//import utils.ExtentReportUtil;
import utils.LogUtil;
import utils.DriverUtil;
import utils.WebDriverHelperUtil;


public class BrowserSteps {

    public static String stepDescription;
    public static String priorityTag;  
    private DriverUtil runthis;
    private LogUtil logthis;
    private CommonUtil myutilities;
    private WebDriverHelperUtil wdhelper;
   // private ExtentReportUtil extentReport; 
    //	Claims specific new methods
    public static String ENV;
    public static String claimid;
    //private Properties config;

    public BrowserSteps() {
    	runthis = new DriverUtil();       
        myutilities = new CommonUtil();
        wdhelper = new WebDriverHelperUtil();
       // extentReport = new ExtentReportUtil();
        logthis = new LogUtil();
        
        
       // fileStream = new FileStream();
        
    }

    @Before
    public void setUp(Scenario scenario) {
        String scenarioName = scenario.getName();
       // priorityTag = returnPriority(scenario.getSourceTagNames());
//        System.out.println("Priority tag name is "+priorityTag);
       // TestData.setTestPriority(priorityTag);
        ExecLog.filedata_logger.info("****************************************************************************************************");
        ExecLog.filedata_logger.info("STARTED - " + scenarioName);
        // Get ScenarioID for reporting and test progression
        String[] parts = scenarioName.split("-");
        String scenarioID = parts[0];
        //TestData.setScenarioID(scenarioID);
        ExecLog.filedata_logger.info("SCENARIO ID: " + scenarioID);
        //extent report
       // extentReport.createReport(scenarioName, scenario.getId());
        //extentReport.createScenario(scenarioName);       
    }
    
    @Given("^I start the web browser$")
    public void i_start_the_web_browser() throws Throwable {   	

        //extentReport.createStep("STEP - Given I start the web browser");
        runthis.setitup();
        myutilities.clickBrowserMaximizeButton();
       // throw new PendingException();
    } 
     
    @Then("^I close the Browser$")
    public void i_close_the_web_browser() throws Throwable {    	

       // extentReport.createStep("STEP - Then I close the browser");
        runthis.cleanup();
       // throw new PendingException();
    }  
    
	@When("^I launch URL$")
    public void i_open_URL() {
		LaunchPage launch_page = new LaunchPage();
		launch_page.launchURL();
    }
}
