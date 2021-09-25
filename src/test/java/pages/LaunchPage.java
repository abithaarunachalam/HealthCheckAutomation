package pages;

import config.Config;
import utils.LogUtil;
import utils.DriverUtil;
import utils.WebDriverHelperUtil;

/**
 * @author abitha
 * @implNote This class is contains functions for different scenarios
 *
 */

public class LaunchPage extends DriverUtil {


	private WebDriverHelperUtil WDHelper; 
	private LogUtil Logthis;
	private Config setup;

	public LaunchPage() {
		WDHelper = new WebDriverHelperUtil();
		Logthis = new LogUtil();
		setup = new Config();
	}

	public void launchURL() {   
		try {
			driver.get(setup.getProperty("website_URL"));  
		}catch (Exception e) {
			Logthis.root_logger.error(this.getClass().getName()+ "Unable to launch URL" + e.getMessage());
		}
	}  

	public void openURL() {
		String appEnv = setup.getProperty("Env");
		if(appEnv.equalsIgnoreCase("XXX") || appEnv.equalsIgnoreCase("UAT")) {
			driver.get(setup.getProperty("XXX_URL"));
		} else {
			driver.get(setup.getProperty("UAT_URL"));
		}
	}  


}