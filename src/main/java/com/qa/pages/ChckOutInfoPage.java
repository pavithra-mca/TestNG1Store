package com.qa.pages;

import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.qa.utils.*;
import com.qa.tests.*;

public class ChckOutInfoPage {
	
	WebDriver driver;
	Logger log=Logger.getLogger(HomePage.class);
	
	Utilities12 utility= new Utilities12();
	WebElement EmailAddressFLD ,FirstNameFLD,LastNameFLD,AddressFLD,CityFLD,StateFLD,PostalCodeFLD,PhoneNumberFLD,CountryDropdown;
	WebElement ShippAddrCheckbox,PurchaseBTN;
	Select CountrySelect;
	
	
	WebElement ItemCostSpan ,TotalCostSpan;
	
	
	
	
	
	public ChckOutInfoPage(WebDriver driver)
	{ 
		this.driver=driver;
		
	}
	
	public boolean verifyTitle(String expectedtitle)
	{
		PurchaseBTN = NewStoreDemoTests.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(NewStoreDemoTests.pros.getProperty("PurchaseButtonInfoPAge").trim())));

		log.info("Expected Checkout YourCart Page Title.."+expectedtitle);
		log.info("Actual title found in Page..."+driver.getTitle());
		
		return(driver.getTitle().equals(expectedtitle));
				    
	}
	public void FillInfo(WebDriver driver) throws Exception
	{
		try
		{
			EmailAddressFLD = driver.findElement(By.cssSelector(NewStoreDemoTests.pros.getProperty("EmailIDInfoPage").trim()));
			FirstNameFLD = driver.findElement(By.cssSelector(NewStoreDemoTests.pros.getProperty("FirstNameInfoPage").trim()));
			LastNameFLD = driver.findElement(By.cssSelector(NewStoreDemoTests.pros.getProperty("LastNAmeInfoPAge").trim()));
			AddressFLD = driver.findElement(By.cssSelector(NewStoreDemoTests.pros.getProperty("AddressInfoPAge").trim()));
			CityFLD = driver.findElement(By.id(NewStoreDemoTests.pros.getProperty("CityInfoPage").trim()));
			StateFLD = driver.findElement(By.id(NewStoreDemoTests.pros.getProperty("StateFldInfoPAge").trim()));
			PostalCodeFLD = driver.findElement(By.id(NewStoreDemoTests.pros.getProperty("PostalCodePAge").trim()));
			PhoneNumberFLD = driver.findElement(By.id(NewStoreDemoTests.pros.getProperty("PhoneNumberPage").trim()));
			CountryDropdown = driver.findElement(By.id(NewStoreDemoTests.pros.getProperty("CountryDropdown").trim()));
			//CountrySelect = new Select(CountryDropdown);
			ShippAddrCheckbox = driver.findElement(By.id(NewStoreDemoTests.pros.getProperty("ShipAddresscheckboxInfopage").trim()));
			PurchaseBTN = driver.findElement(By.xpath(NewStoreDemoTests.pros.getProperty("PurchaseButtonInfoPAge").trim()));
			
			
			ItemCostSpan =driver.findElement(By.xpath(NewStoreDemoTests.pros.getProperty("ItemCostInfoPage").trim()));
			TotalCostSpan=driver.findElement(By.xpath(NewStoreDemoTests.pros.getProperty("TotalCostInfoPAge").trim()));
			
			log.info("Filling the information in checkout page");
			/*
			EmailAddressFLD.sendKeys("abc@gmail.com");
			FirstNameFLD.sendKeys("testfirst");
			LastNameFLD.sendKeys("testlast");
			AddressFLD.sendKeys("TestAddress");
			CityFLD.sendKeys("testcity");
			StateFLD.sendKeys("teststate");
			PostalCodeFLD.sendKeys("t1t2t3");
			//CountrySelect.selectByVisibleText("Canada");
			PhoneNumberFLD.sendKeys("12345fkaj567");
			*/
			utility.EnterText(driver, EmailAddressFLD, "abc@gmail.com");
			utility.EnterText(driver, FirstNameFLD, "testfirst");
			utility.EnterText(driver, LastNameFLD, "testlast");
			utility.EnterText(driver, AddressFLD, "TestAddress");
			utility.EnterText(driver, CityFLD, "testcity");
			utility.EnterText(driver, StateFLD, "teststate");
			utility.EnterText(driver, PostalCodeFLD, "t1t2t3");
			utility.SelectDropDown(driver, CountryDropdown, "Canada");
			utility.EnterText(driver, PhoneNumberFLD, "12345fkaj567");
			utility.SelectCheckBox(driver, ShippAddrCheckbox);
			
			/*
			Boolean ShipAddCheckStatus = ShippAddrCheckbox.isSelected();
			if(!ShipAddCheckStatus)
			{
				ShippAddrCheckbox.click();
			}
			*/
		}
		
		catch(org.openqa.selenium.NoSuchElementException e)
		{
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
	
	public CheckOutFinalPage PurchaseButtonClick(WebDriver driver)
	{
		PurchaseBTN.click();
		return new CheckOutFinalPage(driver);
	}
	

}
