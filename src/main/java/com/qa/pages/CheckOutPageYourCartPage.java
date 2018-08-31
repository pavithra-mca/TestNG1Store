package com.qa.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.AssertJUnit;


import com.qa.utils.*;
import com.qa.tests.*;
import sun.security.util.Length;

public class CheckOutPageYourCartPage {
	WebDriver driver;
	Logger log=Logger.getLogger(HomePage.class);
	
	Utilities12 utility= new Utilities12();
	WebElement ContinueLink;
	WebElement resultfieldTable;
	String jsonString;
	public static String totalCost;
	public CheckOutPageYourCartPage(WebDriver driver)
	{ 
		this.driver=driver;
		
	}
	
	public boolean verifyTitle(String expectedtitle)
	{
		ContinueLink = NewStoreDemoTests.wait.until(ExpectedConditions.elementToBeClickable(By.linkText(NewStoreDemoTests.pros.getProperty("ContinueCheckoutYourCartPage").trim())));

		log.info("Expected Checkout YourCart Page Title.."+expectedtitle);
		log.info("Actual title found in Page..."+driver.getTitle());
		
		return(driver.getTitle().equals(expectedtitle));
				    
	}
	public void verifyCartInCheckoutpage() throws Exception
	{
		try
		{
			utility.TakeScreenShortOF(driver);
		    log.info("Passing the verification string in json format");
		    totalCost="$"+(Double.parseDouble(AccessoriesPage.PriceAccessoriesPage.substring(1).trim())*AccessoriesPage.CheckoutCountAccessories)+".00";
		    log.info("Total cost caliculated...:"+totalCost);
		    jsonString= "{\"Product\":\"Magic Mouse\",\"Quantity\":\"1\",\"Price\":\""+AccessoriesPage.PriceAccessoriesPage+"\",\"Total\":\""+totalCost+"\"}";
		    //jsonString=NewStoreDemoTests.pros.getProperty("VerifyTable").trim().toString();
			
		    resultfieldTable = driver.findElement(By.xpath(NewStoreDemoTests.pros.getProperty("TableCheckoutCartPage")));
		    utility.VerifyTable(driver, jsonString, resultfieldTable);
		}
		catch(org.openqa.selenium.NoSuchElementException e)
		{
			e.printStackTrace();
			log.info("Element is not available in the page"+e.getMessage()+"\n Line Number."+e.getStackTrace()[0].getLineNumber());
			throw new Exception();
		}
		catch(ArithmeticException e)
		{
			log.info("Error while caliculating  the totalcost...."+e.getMessage()+"\n"+e.getStackTrace()+"\nLine number:"+e.getStackTrace()[0].getLineNumber());
	    	throw new Exception();
	    }
		catch(NumberFormatException e)
		{
			log.info("Error while converting price to double...."+e.getMessage()+"\n"+e.getStackTrace()+"\nLine number:"+e.getStackTrace()[0].getLineNumber());
	    	throw new Exception();
	    }

		catch(Exception e)
		{
			log.info("Error while verifying the table...."+e.getMessage()+"\n"+e.getStackTrace()+"\nLine number:"+e.getStackTrace()[0].getLineNumber());
	    	throw new Exception();
	    }
	}
	
	public ChckOutInfoPage ContinueButtonClick(WebDriver driver) throws Exception
	{
		ContinueLink = driver.findElement(By.linkText(NewStoreDemoTests.pros.getProperty("ContinueCheckoutYourCartPage")));
		//ContinueLink.click();
		utility.ClickButton(driver, ContinueLink);
		utility.TakeScreenShortOF(driver);	
		return new ChckOutInfoPage(driver);
	}
	
	
	

}
