package com.attainia.UserActivity.TestScripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestBase {
	
	public WebDriver driver;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	public static String projectLocation = System.getProperty("user.dir");
	public static String configPath = projectLocation +"/log4j.properties";
	
	/*
	 * initialize the logger class 
	 */
	public static final Logger logger = Logger.getLogger(TestBase.class.getName());
	
	@BeforeTest
	public void initalizeThis() throws IOException {
		
		//Using Extent Reports to create Reports
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/ExtentReport.html");
		extent = new ExtentReports();  //create object of ExtentReports
		extent.attachReporter(htmlReporter);

		htmlReporter.config().setDocumentTitle("Automation Report"); // Tittle of Report
		htmlReporter.config().setReportName("Extent Report V4"); // Name of the report
		htmlReporter.config().setTheme(Theme.DARK);//Default Theme of Report

		// General information releated to application
		extent.setSystemInfo("Application Name", "Attainia Test");
		extent.setSystemInfo("User Name", "Rajath Rao");
		extent.setSystemInfo("Envirnoment", "Production");
		
		
		
		
	//initiated properties class 
	
	Properties prop = new Properties();
	
	//Reading from property file
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\main\\java\\Resources\\config.properties");
	prop.load(fis);
	String browserName=prop.getProperty("webBrowser");
	String urlName=prop.getProperty("URL");
	int waitTime = Integer.parseInt(prop.getProperty("implicitWaitTime"));
	if(browserName.contains("firefox")){
		
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\driver\\geckodriver.exe");
		    driver = new FirefoxDriver();
//
        // driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
         System.out.println(urlName);

             driver.get(urlName);
             driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
	}
	
	else if (browserName.contains("chrome")) {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\driver\\chromedriver.exe");
	    driver = new ChromeDriver();
//
    // driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
     System.out.println(urlName);

         driver.get(urlName);
         driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
	}
	

}
	
	@BeforeClass
	public static void getlogger() {
		System.out.println(configPath);
		// reading the property file at this stage 
		PropertyConfigurator.configure(configPath);
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws Exception
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{
			//MarkupHelper is used to display the output in different colors
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));

			//To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
			//We do pass the path captured by this method in to the extent reports using "logger.addScreenCapture" method. 

			//	String Scrnshot=TakeScreenshot.captuerScreenshot(driver,"TestCaseFailed");
			String screenshotPath = TakeScreenshot(driver, result.getName());
			//To add it in the extent report 

			test.fail("Test Case Failed Snapshot is below " + test.addScreenCaptureFromPath(screenshotPath));


		}
		else if(result.getStatus() == ITestResult.SKIP){
			//logger.log(Status.SKIP, "Test Case Skipped is "+result.getName());
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE)); 
		} 
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
		}
	
	}

	public static String TakeScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		// after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	@AfterTest
	public void closeThis()throws IOException {
		extent.flush();
		driver.quit();
		
	}

}
