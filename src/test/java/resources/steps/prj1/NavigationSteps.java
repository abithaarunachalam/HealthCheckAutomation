package resources.steps.prj1;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.LaunchPage;
import utils.CommonUtil;
import utils.LogUtil;
import utils.DriverUtil;


public class NavigationSteps extends DriverUtil  {
	
	private LogUtil Logthis;
	public NavigationSteps()
	{
		
	//	extentReport = new ExtentReport();
		
	}	
    @When("^I open Website as \"([^\"]*)\"$")
    public void i_open_website_as_(String arg1) {
    	// extentReport.createStep("STEP - When I open website as " + arg1); 
    }
}
