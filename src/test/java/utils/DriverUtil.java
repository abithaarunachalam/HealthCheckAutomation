package utils;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import config.Config;


public class DriverUtil {

    public static WebDriver driver;
    public static Config setup;
    public static String envNISP;


    public DriverUtil() {
    	setup = new Config();
    }

    public void setitup() {
    	 String chromePath = System.getProperty("user.dir") +"/src/test/java/resources/conf/drivers/";
    	 //System.out.println(chromePath);
    	 System.setProperty("webdriver.chrome.driver", chromePath+"chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    public void cleanup() {
        driver.quit();
    }   

}


