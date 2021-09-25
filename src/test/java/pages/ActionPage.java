package pages;

import org.openqa.selenium.By;

import config.Config;
import utils.LogUtil;
import utils.DriverUtil;
import utils.WebDriverHelperUtil;
import utils.Constants;

/**
 * @author abitha
 * @implNote This class contains functions for different scenarios
 *
 */

public class ActionPage extends DriverUtil {

	private static final By input_text = By.xpath(Constants.INPUT_TEXT);
    
	private WebDriverHelperUtil WDHelper; 
	private LogUtil Logthis;
	private Config Setup;

	public ActionPage() {
		WDHelper = new WebDriverHelperUtil();
		Logthis = new LogUtil();
	}

	/*
	 * 
	 * 
	 * 
	 */
	public void search() {   
		try {
			WDHelper.click(input_text);  
			WDHelper.setText(input_text,"hello"); 
			WDHelper.hardWait(5);
		}catch (Exception e) {
			Logthis.root_logger.error(this.getClass().getName()+ Constants.UNABLE_TO_SEARCH + e.getMessage());
		}
	}
}