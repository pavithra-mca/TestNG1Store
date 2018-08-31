package com.qa.pages;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import com.qa.utils.*;
import com.qa.tests.*;



public class AccessoriesPage {
	
	WebDriver driver;
	Logger log=Logger.getLogger(HomePage.class);
	
	Utilities12 utiliy= new Utilities12();
	WebElement PriceSpan;
	WebElement CheckoutElementAccessories;
	public static int CheckoutCountAccessories;
	
	
	public String AccessoriesPageTitile;
	
	WebElement AddtoCartButton;
	
	
	public static String PriceAccessoriesPage;
	
	public AccessoriesPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public boolean verifyTitle(String expectedtitle)
	{
		log.info("Expected Accessories Page Title.."+expectedtitle);
		log.info("Actual title found in Page..."+driver.getTitle());
		AddtoCartButton = NewStoreDemoTests.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(NewStoreDemoTests.pros.getProperty("AddtoCartAceessoriesPAge").trim())));
		return(driver.getTitle().equals(expectedtitle));
				    
	}
	public void AddToCartClick(WebDriver driver) throws  Exception
	{
		try
		{
			
		
		
		PriceSpan =driver.findElement(By.xpath(NewStoreDemoTests.pros.getProperty("PriceSpanAccessoriesPAge").trim()));
		PriceAccessoriesPage=PriceSpan.getText();
		log.info("Price garabbed.."+PriceAccessoriesPage);
		
		AddtoCartButton = driver.findElement(By.xpath(NewStoreDemoTests.pros.getProperty("AddtoCartAceessoriesPAge").trim()));
		log.info("Add to Cart Button Identified");
		
		utiliy.ClickButton(driver, AddtoCartButton);
		//AddtoCartButton.click();
		log.info("AddToCart Button Clicked");
		
		utiliy.TakeScreenShortOF(driver);
		log.info("Screen short done...");
		}
		catch(NoSuchElementException e){
			e.printStackTrace();
			log.info("Element is not available in the page"+e.getMessage()+"\n Line Number."+e.getStackTrace()[0].getLineNumber());
			throw new Exception();
		}
		catch(Exception e)
		{
			log.info("Error occured...."+e.getMessage()+"\n"+e.getStackTrace()+"\nLine number:"+e.getStackTrace()[0].getLineNumber());
	    	throw new Exception();
	    }
		
		
	}
	public boolean VerifyCheckoutCount(WebDriver driver,int cartItemCount) throws InterruptedException,Exception
	{
		try
		{
			Thread.sleep(3000);
			CheckoutElementAccessories = driver.findElement(By.xpath(NewStoreDemoTests.pros.getProperty("CheckoutElementAccessoriesPAge").trim()));
	    	CheckoutCountAccessories = Integer.parseInt(CheckoutElementAccessories.getText().trim());
			log.info("Expected Checkout Count ..." +cartItemCount);
			log.info("Actual checkout Count...."+CheckoutCountAccessories);
			return(cartItemCount==CheckoutCountAccessories);
		
		}
		catch(NoSuchElementException e){
			e.printStackTrace();
			log.info("Element is not available in the page"+e.getMessage()+"\n Line Number."+e.getStackTrace()[0].getLineNumber());
			throw new Exception();
		}
		catch(Exception e)
		{
			log.info("Error occured...."+e.getMessage()+"\n"+e.getStackTrace()+"\nLine number:"+e.getStackTrace()[0].getLineNumber());
	    	throw new Exception();
	    }
		
	}
	
	public CheckOutPageYourCartPage CheckoutClick(WebDriver driver) throws Exception
	{
		utiliy.ClickLink(driver, CheckoutElementAccessories);
		log.info("Check out count link clicked");
		return new CheckOutPageYourCartPage(driver);
		 
	}

}
