package com.attainia.UserActivity.TestScripts;


import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


import com.attainia.PageObjects.ActivityPage1;


public class TestCases extends TestBase{
	
	// ------------------TEST CASE 1------------------------------------------------------ 
	/** First check if the table is visible. This is part of checking the sanity after the build
	 * 
	 */
	
	@Test(groups= {"sanity"}, priority=1)
	public void checkTable() {
		test = extent.createTest("TableVisibility");
		 ActivityPage1 pg1  = PageFactory.initElements(driver, ActivityPage1.class);
		boolean tableVisible= pg1.table.isDisplayed();
		test.createNode("TableIsPresent");
		assertEquals(tableVisible,true);
		logger.info("testcase 1 passed");
		
	}
	
	// -----------------------------------------------------TEST CASE 2 -----------------------------
	/**
	 * Check if the link is visible at the bottom for Pg-1. If link is not visible then we cannot navigate to pg2
	 */
	@Test(groups= {"sanity"},priority=2)
	public void linkVisible() {
		 ActivityPage1 pg1  = PageFactory.initElements(driver, ActivityPage1.class);
		boolean linkVisible=pg1.pg2HyperLink.isDisplayed();
		assertEquals(linkVisible,true);
		logger.info("testcase 2 passed");
	}
	
	//------------------ TEST CASE 3---------------------------------------------------
	
	/**
	 * Check if link on Pg-1 is clickable
	 */
	@Test(groups= {"sanity"},priority=3)
	
		public void linkClick() {
			 ActivityPage1 pg1  = PageFactory.initElements(driver, ActivityPage1.class);
			 pg1.pg2HyperLink.click();
		String title=pg1.page2Header.getText();
		
		assertEquals(title,"Page 2");
		logger.info("testcase 3 passed");	
			
		}

	// ----------------------------------------TEST CASE 4------------------------------------------------------
	
	/**
	 * Similarly check if link of Pg2 to see if it is clickable
	 */
	@Test(groups= {"sanity"},priority=4)

		public void link2Click() {
			 ActivityPage1 pg2  = PageFactory.initElements(driver, ActivityPage1.class);
			 pg2.pg1HyperLink.click();
		String title=pg2.page2Header.getText();
		
		assertEquals(title,"Page 1");
		logger.info("testcase 4 passed");
			
		}
	 //----------------------TEST CASE 5------------------------------------------
	//Check if background of the table is white when logged in 
@Test(groups= {"colorCode"},priority=5)
	 public void init() throws Exception{
		 ActivityPage1 pg1  = PageFactory.initElements(driver, ActivityPage1.class);
		
		//pg1.table.getCssValue("color");
		 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	String s1=pg1.table.getCssValue("color");
		
		String s2=Color.fromString(s1).asHex();
		String white = "#000000";
		 
		assertEquals(s2,white);
		logger.info("testcase 5 passed");
	 }
	 
	 //----------------------TEST CASE 6-------------------------------------------
	 // Row  check . Count the the number of Rows inline with the JSON. Used Raw table to validate
@Test(groups= {"schemaValidation"},priority=8)
	 public void checkRows() {
		 ActivityPage1 pg1  = PageFactory.initElements(driver, ActivityPage1.class);
			
			//pg1.table.getCssValue("color");
			 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			 
			 
			 // Identifying rows 
		 List<WebElement> tableRows = pg1.table.findElements(By.tagName("tr"));
	     int rows = tableRows.size()-1;
		 assertEquals(rows,9);
		 logger.info("testcase 6 passed");
	 }
	 

	 //----------------------TEST CASE 7-------------------------------------------
	 // Count number of columns 
@Test(groups= {"schemaValidation"},priority=9)//	 @Test(priority=2)
	 public void checkColumns() {
		 ActivityPage1 pg1  = PageFactory.initElements(driver, ActivityPage1.class);
			
			//pg1.table.getCssValue("color");
			 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			 List<WebElement> tableRows = pg1.table.findElements(By.tagName("tr"));
			 List<WebElement> tableColumns=tableRows.get(0).findElements(By.tagName("td"));
			 //Identifying columns
			
			 int columns = tableColumns.size();
			 assertEquals(columns,5);
			 logger.info("testcase 7 passed");
	 }
	 

	 //----------------------TEST CASE 8------------------------------------------
/**
 * if LoginCount is 0 then the row should be highlighted in Red
 */
@Test(groups= {"colorCode"},priority=6)
	 public void checkRedOnClick() {
		 ActivityPage1 pg1  = PageFactory.initElements(driver, ActivityPage1.class);
		 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		 pg1.button.click();
		 List<WebElement> tableRows = pg1.table.findElements(By.tagName("tr"));
		 for(int i=2;i<tableRows.size();i++) {

			 String newXpath = "//*[@id='app']/div/table/tr["+ i + "]/td[4]";
			 
			
			 if(Integer.parseInt(driver.findElement(By.xpath(newXpath)).getText())==0) {
				 String rowColor=driver.findElement(By.xpath(newXpath)).getCssValue("color");
				 String rowColorHex=Color.fromString(rowColor).asHex();
				 String redHex="#000000";
				assertEquals(rowColorHex, redHex);
				
			 }
			 
			 
			 
			
	 }
		 //Click again and verify to make sure the background is white again 
		 pg1.button.click();
		 String s1=pg1.table.getCssValue("color");
			
			String s2=Color.fromString(s1).asHex();
			String white = "#000000";
			 
		assertEquals(s2,white);
		logger.info("testcase 8 passed");
}
	 

	 //----------------------TEST CASE 9 ------------------------------------------
/**
 * if LoginCount is greater than or equal to 1 then the row should be highlighted in Green
 */
@Test(groups= {"colorCode"},priority=7)
	 public void checkGreenOnClick() {
		 ActivityPage1 pg2  = PageFactory.initElements(driver, ActivityPage1.class);
		 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		 pg2.pg2HyperLink.click(); 
		 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		 pg2.button2.click();
		 List<WebElement> tableRows = pg2.table.findElements(By.tagName("tr"));
		 System.out.println( driver.findElement(By.xpath("//*[@id='app']/div/table/tr[3]/td[4]")).getCssValue("background-color"));
		 for(int i=2;i<tableRows.size();i++) {

			 String newXpath = "//*[@id='app']/div/table/tr["+ i + "]/td[4]";
		
			 if(Integer.parseInt(driver.findElement(By.xpath(newXpath)).getText())>=1) {
				 String rowColor=driver.findElement(By.xpath(newXpath)).getCssValue("color");
				 String rowColorHex=Color.fromString(rowColor).asHex();
				 String greenHex="#000000";
				assertEquals(rowColorHex, greenHex);
			 }
			 
			
	 }
		 //Click again and verify to make sure the background is white again 
		 
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		pg2.button.click();
		 String s1=pg2.table.getCssValue("color");
			
			String s2=Color.fromString(s1).asHex();
			String white = "#000000";
			 
		assertEquals(s2,white);
		logger.info("testcase 9 passed");
}
	 
}