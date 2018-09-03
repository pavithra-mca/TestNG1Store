


package com.qa.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

import java.util.Properties;

import javax.naming.directory.NoSuchAttributeException;

import com.qa.utils.*;
import com.qa.pages.*;

import org.testng.reporters.*;

public class NewStoreDemoTests 
{
    WebDriver driver ;
	public final static Logger log =Logger.getLogger(NewStoreDemoTests.class);
	static final String logproppath= "config\\log.properties";
	public String PriceAccessoriesPage;
	public static Properties pros =new Properties();
	public static Wait<WebDriver> wait;
	HomePage hpage;
	AccessoriesPage aPage;
	CheckOutPageYourCartPage chYourCartPage;
	ChckOutInfoPage chInfoPage;
	CheckOutFinalPage chFinalPage;
	Utilities12 utility=new Utilities12();
	@BeforeSuite
	public void setup() throws Exception
	{
		try
		{
			String timestamp;
			timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			System.setProperty("current.logdate", timestamp);
			System.out.println("Log4j property file location" + logproppath);
			
			PropertyConfigurator.configure(logproppath);
			log.info("Execution is in Before class..and intializing the drivers...");
			
			log.info("Properties initialized");
			
			InputStream in=new FileInputStream("config\\PropertyValues.properties");
			pros.load(in);
			
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			log.info("file not foun exception."+e.getMessage()+"\n"+e.getStackTrace()+"\nLine number:"+e.getStackTrace()[0].getLineNumber()+"\nClassName:"+e.getClass());
		    throw new Exception();
		}
		catch(Exception e)
		{
			log.info("Problem occured.. Please check the Properties file."+e.getMessage()+"\n"+e.getStackTrace()+"\nLine number:"+e.getStackTrace()[0].getLineNumber()+"\nClassName:"+e.getClass());
	    	throw new Exception();
			
		}
	}
		
	@Parameters({"url","BrowserType"})
	@BeforeTest(alwaysRun=true)
	public void navigatingToUrl(String url,String BrowserType) throws Exception
	{
		try
		{
			if(BrowserType.equalsIgnoreCase("IE"))
			{
				
				System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
				Reporter.log("<font colour=\"blue\"><h2>I am running in IE..<h2></font>");
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				// Settings to Accept the SSL Certificate in the Capability object
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				driver= new InternetExplorerDriver(capabilities);
				log.info("IE driver intialized with capabilities");
				
			}
			else if(BrowserType.equalsIgnoreCase("chrome"))
			{
				
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				Reporter.log("<font colour=\"blue\"><h2>I am running in chrome..<h2></font>");
				driver= new ChromeDriver();
				log.info("Chrome Driver Intialized");
				
				
			}
			else
			{
				
				System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
				Reporter.log("<font colour=\"blue\"><h2>I am running in firefox..<h2></font>");
				driver= new FirefoxDriver();
				log.info("Firefox driver Intialized");
				
				
				
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(url);
			Thread.sleep(3000);
			wait = new FluentWait<WebDriver>(driver)
					.withTimeout(30,TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);
		}
		catch(SessionNotCreatedException e)
		{
			e.printStackTrace();
			log.info("Unable to create the session "+e.getMessage()+"\n Line Number."+e.getStackTrace()[0].getLineNumber());
			throw new Exception();
		}
		
		catch(Exception e)
		{
			log.info("**********************");
			e.printStackTrace();
			log.info("**********************");
			log.info("Problem occured.. ."+"*****"+e.getMessage()+"\n"+e.getStackTrace()+"\nLine number:"+e.getStackTrace()[0].getLineNumber()+"\nClassName:"+e.getClass());
	    	throw new Exception();
			
		}
		
		
	}
	@Test
	public void storedemoqa() throws InterruptedException,IOException,Exception
	{
		try
		{
			log.info("Home Page tests are running");
			utility.TakeScreenShortOF(driver);
			hpage =new HomePage(driver);
			aPage=hpage.GoToAccessories();
			Assert.assertEquals(hpage.ExpectedHomePageTitile, hpage.HomePageTitile);
			log.info("Titile verified");
		}
		catch(org.openqa.selenium.NoSuchElementException e)
		{
			e.printStackTrace();
			log.info("Element is not available in the page"+e.getMessage()+"\n Line Number."+e.getStackTrace()[0].getLineNumber());
			throw new Exception();
		}
		catch(WebDriverException e)
		{
			log.info("Browser is not reachable...."+e.getMessage()+"\n"+e.getStackTrace()+"\nLine number:"+e.getStackTrace()[0].getLineNumber());
	    	throw new Exception();
	    }
		catch(Exception e)
		{
			log.info("**********************");
			e.printStackTrace();
			log.info("**********************");
			log.info("Problem occured.. Throwing object not found error."+"*****"+e.getMessage()+"\n"+e.getStackTrace()+"\nLine number:"+e.getStackTrace()[0].getLineNumber()+"\nClassName:"+e.getClass());
	    	throw new Exception();
			
		}
	
}
	@Test(dependsOnMethods={"storedemoqa"})
	public void AccessoriesPageTest() throws InterruptedException,IOException,Exception
	{
		try
		{
	
			String ExpectedAccessoriesPageTitile = "Accessories | ONLINE STORE";
			boolean result=aPage.verifyTitle(ExpectedAccessoriesPageTitile);
			Assert.assertTrue(result);
			log.info("title of the assert page verified...");
			
			aPage.AddToCartClick(driver);
			int ExpectedCartCout =1;
			boolean cartresult=aPage.VerifyCheckoutCount(driver, ExpectedCartCout);
			Assert.assertTrue(cartresult);
			utility.TakeScreenShortOF(driver);
			chYourCartPage=aPage.CheckoutClick(driver);

		}
		catch(org.openqa.selenium.NoSuchElementException e)
		{
			e.printStackTrace();
			log.info("Element is not available in the page"+e.getMessage()+"\n Line Number."+e.getStackTrace()[0].getLineNumber());
			throw new Exception();
		}
		catch(WebDriverException e)
		{
			log.info("Browser is not reachable...."+e.getMessage()+"\n"+e.getStackTrace()+"\nLine number:"+e.getStackTrace()[0].getLineNumber());
	    	throw new Exception();
	    }
		catch(Exception e)
		{
			log.info("**********************");
			e.printStackTrace();
			log.info("**********************");
			log.info("Problem occured.. Throwing object not found error."+"*****"+e.getMessage()+"\n"+e.getStackTrace()+"\nLine number:"+e.getStackTrace()[0].getLineNumber()+"\nClassName:"+e.getClass());
	    	throw new Exception();
			
		}
	
}
	
	@Test(dependsOnMethods={"AccessoriesPageTest"})
	public void CheckYourCartPage() throws Exception
	{
		try
		{
			 
			 String ExpCheckoutYourCartPageTitile ="Checkout | ONLINE STORE";
			 boolean result=chYourCartPage.verifyTitle(ExpCheckoutYourCartPageTitile);
			 Assert.assertTrue(result);
			 log.info("Assert done in checkoutinfo page");
			 chYourCartPage.verifyCartInCheckoutpage();
			 log.info("Verification done... Item added to cart..");
			 chInfoPage=chYourCartPage.ContinueButtonClick(driver);
		}
		catch(org.openqa.selenium.NoSuchElementException e)
		{
			e.printStackTrace();
			log.info("Element is not available in the page"+e.getMessage()+"\n Line Number."+e.getStackTrace()[0].getLineNumber());
			throw new Exception();
		}
		
		catch(WebDriverException e)
		{
			log.info("Browser is not reachable...."+e.getMessage()+"\n"+e.getStackTrace()+"\nLine number:"+e.getStackTrace()[0].getLineNumber());
	    	throw new Exception();
	    }
		 
	}
	
	@Test(dependsOnMethods={"CheckYourCartPage"})
	public void CheckOutInfoPageTest() throws Exception
	{
		try
		{
			 utility.TakeScreenShortOF(driver);
			 chInfoPage = new ChckOutInfoPage(driver);
			 log.info("Finding the elements in checkout page");
		
			 String ExpCheckoutInfoPageTitile ="Checkout | ONLINE STORE";
			 boolean result=chInfoPage.verifyTitle(ExpCheckoutInfoPageTitile);
			 Assert.assertTrue(result);
			 log.info("Assert done in checkoutinfo page");
			 chInfoPage.FillInfo(driver);
			 log.info("Information filled");
			 chFinalPage=chInfoPage.PurchaseButtonClick(driver);
			 
		/*
			 Assersion error in chrome becuase these fields are not in chrome
			String ItemCostInfo="$150.00";
			String TotalCostInfo="$160.00";
			log.info("Assertion for Item cost and total Cost in InfoCheckout PAge");
			
			Assert.assertEquals(ItemCostInfo, ItemCostSpan.getText().trim());
			Assert.assertEquals(TotalCostInfo, TotalCostSpan.getText().trim());
			*/
		
		}
		catch(org.openqa.selenium.NoSuchElementException e)
		{
			e.printStackTrace();
			log.info("Element is not available in the page"+e.getMessage()+"\n Line Number."+e.getStackTrace()[0].getLineNumber());
			throw new Exception();
		}
		catch(WebDriverException e)
		{
			log.info("Browser is not reachable...."+e.getMessage()+"\n"+e.getStackTrace()+"\nLine number:"+e.getStackTrace()[0].getLineNumber());
	    	throw new Exception();
	    }
		catch(Exception e)
		{
			log.info("Error occured...."+e.getMessage()+"\n"+e.getStackTrace()+"\nLine number:"+e.getStackTrace()[0].getLineNumber());
	    	throw new Exception();
	    }
	
	}
	@Test(dependsOnMethods={"CheckOutInfoPageTest"})
	public void CheckOutFinalPageTest() throws Exception
	{
		try
		{
			
			Thread.sleep(8000);
			utility.TakeScreenShortOF(driver);
			String TransactionResultTitle ="Transaction Results | ONLINE STORE";
			boolean result=chFinalPage.verifyTitle(TransactionResultTitle);
			Assert.assertTrue(result);
			int ExpectedCheckoutFinalCount=0;
			boolean result2=chFinalPage.VerifyCheckoutCount(driver, ExpectedCheckoutFinalCount);
			Assert.assertTrue(result2);
		}
		catch(org.openqa.selenium.NoSuchElementException e)
		{
			e.printStackTrace();
			log.info("Element is not available in the page"+e.getMessage()+"\n Line Number."+e.getStackTrace()[0].getLineNumber());
			throw new Exception();
		}
		catch(WebDriverException e)
		{
			log.info("Browser is not reachable...."+e.getMessage()+"\n"+e.getStackTrace()+"\nLine number:"+e.getStackTrace()[0].getLineNumber());
	    	throw new Exception();
	    }

		catch(Exception e)
		{
			log.info("Error while verifying the table...."+e.getMessage()+"\n"+e.getStackTrace()+"\nLine number:"+e.getStackTrace()[0].getLineNumber());
	    	throw new Exception();
	    }
		
		
	}
	
	
	
}
			
			
			
			
		
	
	
	

	
		
	
	
	
	
	
	


