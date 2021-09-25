package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import utils.CommonUtil;
import utils.LogUtil;
import utils.Constants;

public class Config {
    private Properties config;
    private CommonUtil myutil;
    //private String basepath = "/src/test/java/resources/conf/";
    private String basePath = File.separator+Constants.SRC+File.separator+"test"+File.separator+"java"+
    							File.separator+"resources"+File.separator+"conf"+File.separator;
    private String profilePath = basePath + "profiles/";
    private String envPath = basePath + "environment/";
    private String sysPath = basePath + "system/";

    public Config() {
    	initConfig();
    }
    
    public void loadSystemConfig() {
        try {
            FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + sysPath + "framework.properties");

            Properties config = new Properties();
            config.load(fs);

            this.config = config;
        } catch (IOException e) {
        	LogUtil.root_logger.error("Cannot find " + e);
        }
    }
    
    public void loadUserConfig(String pcname) {
        try {
            FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + profilePath + pcname + ".properties");

            Properties config = new Properties();
            config.load(fs);

            this.config = config;
        } catch (IOException e) {
        	LogUtil.root_logger.error("Cannot find User config file: " + pcname);
        }
    }
    
    private void loadEnvConfig(String pcname, String userenv) {
        loadConfig(pcname, userenv);
    }

    public void loadConfig(String pcname, String configname) {
        try {
            FileInputStream fs1 = new FileInputStream(System.getProperty("user.dir") + profilePath + "default.properties");
            FileInputStream fs2 = new FileInputStream(System.getProperty("user.dir") + envPath +  "Environment.properties");

            Properties config = new Properties();
            config.load(fs1);
            config.load(fs2);

            this.config = config;
        } catch (IOException e) {
            LogUtil.root_logger.error("Cannot find config file");
        }
    }


    public void loadRunConfig() {
        try {
        	System.out.println(basePath);
        	System.out.println("TESTLOG BASE PATH AFTER FILE SEPARATOR IS " + basePath);
        	System.out.println(envPath);
        	System.out.println(System.getProperty("user.dir") + envPath);
        	Properties config = new Properties();
        	FileInputStream fs4 = new FileInputStream(System.getProperty("user.dir") + envPath + "Env.properties");
            config.load(fs4);   // Environment
            this.config = config;
        } catch (IOException e) {
            LogUtil.root_logger.error("Cannot find properties files");
        }
    }

    public String getProperty(String propertyName) {
        return config.getProperty(propertyName);
    }

    private void initConfig() {
        loadRunConfig();
    }

    public String getReportConfigPath(){
     Properties config = new Properties();
     String reportConfigPath = config.getProperty("Env.reportConfigPath");
     if(reportConfigPath!= null) return reportConfigPath;
     else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath"); 
    }
    
}
