package com.qa.utils;

import java.io.File;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.sun.istack.internal.logging.Logger;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;



public class Utilities12  {
	
	//WebDriver driver;
	Logger log=Logger.getLogger(Utilities12.class);
public void TakeScreenShortOF(WebDriver driver) throws InterruptedException, IOException
{

	
	log.info("taking screen shot1..");
	Reporter.log(driver.getTitle());
	TakesScreenshot tk=(TakesScreenshot)driver;

	log.info("screen shot object created");
	Thread.sleep(500);
	String ImageFileName = new SimpleDateFormat("MMddyy-HHmmss").format(new Date());
	File f=tk.getScreenshotAs(OutputType.FILE);
	System.out.println(f.getAbsoluteFile());
	log.info("screen shot captured");
	String  dest=System.getProperty("user.dir")+"/ScreenShots/"+ImageFileName+".png";
	File destlocation=new File(dest);//("C:\\Users\\maanya sri\\Downloads\\seleniumtraining\\selenium\\screenshorts");/*\\screenshort.png");
	
	//destlocation=new File("C:\\Users\\maanya sri\\Downloads\\seleniumtraining\\selenium\\screenshorts\\screenshort"+ImageFileName+".png");
	FileUtils.copyFile(f,destlocation);
	log.info("copied into destination location");
	Reporter.log("<br><img src='"+destlocation+"' height='400' width='400'/></br>");
	
}
public void EnterText(WebDriver driver,WebElement we,String ValueToEnter)
{
	try
	{
		we.sendKeys(ValueToEnter);

	}
	catch(NoSuchElementException e)
	{
		e.printStackTrace();
		log.info("WebElement is not present");
	}
}
public void ClickButton(WebDriver driver,WebElement we) throws Exception
{
	try
	{
		we.click();

	}
	catch(NoSuchElementException e)
	{
		e.printStackTrace();
		log.info("WebElement is not present");
		throw new Exception();
	}
	catch(Exception e)
	{
		log.info("exception occured while clicking the button"+e.getMessage()+"\n"+e.getStackTrace()+"\nLine number:"+e.getStackTrace()[0].getLineNumber());
    	throw new Exception();
    }
	
}
public void ClickLink(WebDriver driver,WebElement we) throws Exception
{
	try
	{
		we.click();

	}
	catch(NoSuchElementException e)
	{
		e.printStackTrace();
		log.info("WebElement is not present");
		throw new Exception();
	}
	catch(Exception e)
	{
		log.info("exception occured while clicking the button"+e.getMessage()+"\n"+e.getStackTrace()+"\nLine number:"+e.getStackTrace()[0].getLineNumber());
    	throw new Exception();
    }
	
}
public void SelectCheckBox(WebDriver driver,WebElement we)
{
	try
	{
		Boolean CheckStatus = we.isSelected();
		if(!CheckStatus)
		{
			we.click();
		}

	}
	catch(NoSuchElementException e)
	{
		e.printStackTrace();
		log.info("WebElement is not present");
	}
	catch(Exception e)
	{
		log.info("Exception while clicking the checkbox");
		e.printStackTrace();
	}
	
}
public void SelectDropDown(WebDriver driver,WebElement we,String value) throws Exception
{
	try
	{
		Select SelectDDL = new Select(we);
		SelectDDL.selectByVisibleText(value);

	}
	catch(NoSuchElementException e)
	{
		e.printStackTrace();
		log.info("WebElement is not present");
		throw new Exception();
	}
	catch(Exception e)
	{
		log.info("exception occured while clicking the button"+e.getMessage()+"\n"+e.getStackTrace()+"\nLine number:"+e.getStackTrace()[0].getLineNumber());
    	throw new Exception();
    }
	
}
public void VerifyTable(WebDriver driver,String jsonString,WebElement resultfieldTable) throws Exception
{
	try
	{
		Integer resultIndex = -1;
	JSONParser parser = new JSONParser();
	String JSON_DATA = jsonString;
	Object obj = null;
	try {
		obj = parser.parse(JSON_DATA);
	} catch (ParseException e) {
		
		e.printStackTrace();
	}
	JSONObject jsonObject = (JSONObject) obj;
	log.info("Total number or elements in Json is " + jsonObject.size());
	
	
	
	Properties propArray = new Properties();
	Set keys = jsonObject.keySet();
    Iterator a = keys.iterator();
	int i = 0;
    while(a.hasNext()) {
    	String key = (String)a.next();
        // loop to get the dynamic key
        String value = (String)jsonObject.get(key);
       
        propArray.setProperty(key, value);
        System.out.println(propArray.toString());
        i++;
    }
    
    
	new Actions(driver).moveToElement(resultfieldTable).perform();
	
	List<Integer> headerIndex = new ArrayList<Integer>();
	List<String> headerValue = new ArrayList<String>();
	List<String> dataValue = new ArrayList<String>();
	
	Set headerInfo = propArray.keySet();
	Iterator itr = headerInfo.iterator();
	String header = null;
	String expectedtablevalue = null;
	Integer headercount=-1;
	List<WebElement> columnheaders=null;
	List<WebElement> datarows=null;
	Integer foundresult=0; // Increment to the number of json values and verify
	Integer headerprocessingcount=0;
	
	while(itr.hasNext()){ // Process each json at a time
		headercount = -1;
		header = (String) itr.next();
		
		expectedtablevalue = propArray.getProperty(header);
		log.info("Header I am looking for: "+header+" - Value I am looking for:"+expectedtablevalue);
		
		columnheaders = resultfieldTable.findElements(By.xpath("./tbody/tr/th"));
		log.info("Number of columns in the table"+ columnheaders.size());
		
		datarows = resultfieldTable.findElements(By.xpath("./tbody/tr"));
		log.info("Number of data rows in the table"+ datarows.size());
		
		for(WebElement processinghedaderele: columnheaders){
			headercount++;
			log.info("Header I am processing- number" + headercount + "- value"+ processinghedaderele.getText());
			if(processinghedaderele.getText().trim().length()>1){
				if(processinghedaderele.getText().trim().contains(header.trim())){
					try{
						log.info("Match found for header in"+headercount);
						headerIndex.add(headerprocessingcount, headercount);
						headerValue.add(headerprocessingcount,header.trim());
						dataValue.add(headerprocessingcount,expectedtablevalue.trim());
						headerprocessingcount++;
						log.info("Completed processing one json search. Moving to next");
					}
					catch(Exception e){
						log.info("Exception in updating Arraylist"+e.getLocalizedMessage()+e.getMessage()+e.toString());
					}
					break;
				}
			}
			
		}
		log.info("The columns I have to process"+headerValue.toString());
		log.info("The values I have to process"+dataValue.toString());
		log.info("The Index I have to process"+headerIndex.toString());
		
	}
	
	
	log.info("******************************");
	log.info("Table Header indexes are found");
	log.info("******************************");
	
	Integer rowcount=1;
	for(int it=1;it<datarows.size();it++)//the first row is header and so procesing from the next row
		
		
	{
		WebElement processingdatarow=datarows.get(it);
		foundresult =0;
		log.info("Processing data row " + rowcount+ "in the table");
		
		//getting the values from header indexes and comparing the values
		for(int indexkeyref=0; indexkeyref < headerIndex.size();indexkeyref++){
			
			Integer headerIndexprocess = headerIndex.get(indexkeyref);
			String processingtablevalue;
			if(headerIndexprocess+2==3)
			
			{
			processingtablevalue=processingdatarow.findElement(By.xpath("./td["+(headerIndexprocess+2)+"]/form/input[1]")).getAttribute("value").toString();
			}
			else
			{
				processingtablevalue= processingdatarow.findElement(By.xpath("./td["+(headerIndexprocess+2)+"]")).getText().trim();
			 
			}
			log.info("Current value in the table"+processingtablevalue);
			
			expectedtablevalue = dataValue.get(indexkeyref).toString();
			log.info("Value I am looking for"+expectedtablevalue);
			
			
			if(processingtablevalue.contains("...")){
				log.info("Table has chopped content.");
				
				WebElement tdelement = processingdatarow.findElement(By.xpath("./td["+(headerIndexprocess+1)+"]"));
				tdelement.click();
				Thread.sleep(1000);
				processingtablevalue = tdelement.findElement(By.xpath(".//span/div")).getAttribute("innerText").trim();
				//logger.info("Inner text"+processingdatarow.findElement(By.xpath("./td["+(headerIndexprocess+1)+"]/div/span/div")).getAttribute("innerText"));
				log.info("Table inside content"+processingtablevalue);
			}
			
			
			
			log.info("Value of the column"+headerIndexprocess+2);
			log.info("Value of corresponding table column:"+processingtablevalue);
		
			if(processingtablevalue.length()>0){
				if(processingtablevalue.trim().contains(expectedtablevalue.trim())){
					log.info("Found match for the table content in row"+rowcount);
					foundresult++;
					//break;
				}
				else{
					log.info("Mis match:"+processingtablevalue.trim()+" did not match expected value:"+expectedtablevalue);
				}
			}
			else{
				log.info("Row:"+rowcount+" Column:"+ headerIndexprocess+" does not have any value.");
			}
		}
	
		if(propArray.size() == foundresult){
			log.info("Found data for all the info in table in row"+rowcount);
			resultIndex = rowcount;
			break;
		}
		rowcount++;
	}
	log.info("Row index identified"+resultIndex);
	
	log.info("***************************************************************");
	log.info("Table Verification done");
	log.info("*****************************************************************");
	
		
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
}

/*
public  WebDriver ScreenShort(WebDriver driver) throws InterruptedException,IOException
	{
		log.info("taking screen shot1..");
		Reporter.log(driver.getTitle());
		TakesScreenshot tk=(TakesScreenshot)driver;

		log.info("screen shot object created");
		Thread.sleep(500);
		String ImageFileName = new SimpleDateFormat("MMddyy-HHmmss").format(new Date());
		File f=tk.getScreenshotAs(OutputType.FILE);
		System.out.println(f.getAbsoluteFile());
		log.info("screen shot captured");
		String  dest=System.getProperty("user.dir")+"/ScreenShots/"+ImageFileName+".png";
		File destlocation=new File(dest);//("C:\\Users\\maanya sri\\Downloads\\seleniumtraining\\selenium\\screenshorts");/*\\screenshort.png");
		
		//destlocation=new File("C:\\Users\\maanya sri\\Downloads\\seleniumtraining\\selenium\\screenshorts\\screenshort"+ImageFileName+".png");
		FileUtils.copyFile(f,destlocation);
		log.info("copied into destination location");
		Reporter.log("<br><img src='"+destlocation+"' height='400' width='400'/></br>");
		return driver;
	}
*/
