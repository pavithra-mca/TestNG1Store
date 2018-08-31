package com.qa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.qa.utils.*;
import com.qa.tests.*;

public class CheckOutFinalPage {
	
	WebDriver driver;
	Logger log=Logger.getLogger(HomePage.class);

	Utilities12 utility= new Utilities12();
	WebElement CheckoutClearElement;
	int CheckoutCount;
	
	public CheckOutFinalPage(WebDriver driver)
	{ 
		this.driver=driver;
		
	}
	
	public boolean verifyTitle(String expectedtitle)
	{
		

		log.info("Expected Checkout YourCart Page Title.."+expectedtitle);
		log.info("Actual title found in Page..."+driver.getTitle());
		return(driver.getTitle().equals(expectedtitle));
				    
	}
	public boolean VerifyCheckoutCount(WebDriver driver,int cartItemCount) throws InterruptedException
	{
		CheckoutClearElement = driver.findElement(By.xpath(NewStoreDemoTests.pros.getProperty("checkoutClearTransactiionPAge").trim()));
		Thread.sleep(3000);
		int CheckoutCount = Integer.parseInt(CheckoutClearElement.getText().trim());
		log.info("Expected Checkout Count ..." +cartItemCount);
		log.info("Actual checkout Count...."+CheckoutCount);
		WebElement CheckoutClearElement = driver.findElement(By.xpath(NewStoreDemoTests.pros.getProperty("checkoutClearTransactiionPAge").trim()));
		CheckoutCount = Integer.parseInt(CheckoutClearElement.getText().trim());
		return(cartItemCount==CheckoutCount);
	}
}
