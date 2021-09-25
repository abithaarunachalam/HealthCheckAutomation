package resources.steps.prj1;

import io.cucumber.java.en.Then;
import pages.ActionPage;


public class ActionSteps {

    public static String stepDescription;
    public static String priorityTag;  
    public static String ENV;

    public ActionSteps() {
    	  
    }
     
    @Then("^I Search$")
    public void i_search() throws Throwable {    	
		ActionPage Actionpage = new ActionPage();
		Actionpage.search();
    }
}
