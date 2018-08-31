package com.qa.pages;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.qa.utils.*;
import com.qa.tests.*;


import org.apache.log4j.Logger;


public class HomePage {
	
	WebDriver driver;
	Logger log=Logger.getLogger(HomePage.class);
	
	Utilities12 utiliy= new Utilities12();
	public String ExpectedHomePageTitile;
	public String HomePageTitile;
	
	public HomePage(WebDriver driver)
	{
	this.driver = driver;
	//this.wait =wait;
	//this.log = log;
	}
	public AccessoriesPage GoToAccessories() throws Exception
	{
		try
		{
			log.info("In Home page...");
			
			//Assert.assertEquals(HomePageTitile, driver.getTitle());
			
			log.info("Assertion of home page done..");
			
			ExpectedHomePageTitile = "ONLINE STORE | Toolsqa Dummy Test site";
			HomePageTitile=driver.getTitle();
			
			//Home Page
			//Thread.sleep(1000);
			log.info("finding product category link..");
			WebElement ProdCategoryLink= NewStoreDemoTests.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='menu-item-33']/a")));
			//WebElement Accessories	=driver.findElement(By.linkText("Accessories"));
			log.info("finding Accessories link..");
			WebElement AccessoriesLink= driver.findElement(By.xpath(".//a[text()='Accessories']"));
			log.info("Accessories Link Found");
			Actions action= new Actions(driver);
			action.moveToElement(ProdCategoryLink).build().perform();;
			log.info("ProductDetails..clicked");
			action.moveToElement(AccessoriesLink).build().perform();;
			log.info("Accessories clicked");
			utiliy.TakeScreenShortOF(driver);
			Thread.sleep(1000);
			action.click().perform();
			log.info("action performed...Accessories clicked");
			utiliy.TakeScreenShortOF(driver);
			log.info("Screen shot captured..");
			return new AccessoriesPage(driver);
		}
		catch(org.openqa.selenium.NoSuchElementException e)
		{
			e.printStackTrace();
			log.info("Element is not available in the page"+e.getMessage()+"\n Line Number."+e.getStackTrace()[0].getLineNumber());
			throw new Exception();
		}
		catch(Exception e)
		{
			log.info("Problem occured.. Throwing object not found error."+e.getMessage()+"\n"+e.getStackTrace()+"\nLine number:"+e.getStackTrace()[0].getLineNumber()+"\nClassName:"+e.getClass());
	    	throw new Exception();
			
		}
	}

}
