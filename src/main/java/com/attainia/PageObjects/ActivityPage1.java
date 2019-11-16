package com.attainia.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ActivityPage1 {
	public WebDriver driver;
	public ActivityPage1(WebDriver driver) {
		this.driver =driver;
		
	}
	// identifying web elements 
	
	@FindBy(xpath="//*[@id='app']/div/button")
	public WebElement button;
	
	// Table locator
	@FindBy(xpath="//*[@id='app']/div/table")
	public WebElement table ;
	
	
	// find the Login count column 
	
	@FindBy (xpath="//*[@id='app']/div/table/tr/td[4]")
	public WebElement loginCountColumn;
	
	//find the hyperLink for Page2
	@FindBy(linkText="Go to Page 2")
	public WebElement pg2HyperLink;
	
	@FindBy(xpath="//*[@id='app']/div/button")
	public WebElement button2;
	
	@FindBy(xpath="//*[@id='app']/h1")
	public WebElement page2Header;
	
	//find the hyperLink for Page2
		@FindBy(linkText="Go to Page 1")
		public WebElement pg1HyperLink;
}
