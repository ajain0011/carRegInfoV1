package com.carRegInfoV1.testCases;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {

	public String baseURL = "https://cartaxcheck.co.uk";
	public static WebDriver driver;
	public static Logger logger;
	
	@BeforeClass
	public static void setup()
	{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		logger=Logger.getLogger("CarCheck");
		PropertyConfigurator.configure("Log4j.properties");
	}
	
	
	@AfterClass
	public static void tearDown()
	{
		driver.quit();
	}
}
