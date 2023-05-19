package com.testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.lang.model.element.Element;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.base.BaseClass;
import com.pageObjects.OrderNow;
import com.utilities.Reporting;
import com.utilities.Screen_Recordings;
@Listeners(com.utilities.Reporting.class)
public class FWA_022 extends BaseClass{

	public Reporting report = new Reporting();	

	//____________________to validate ordernow btn validation without login ___________________________________
	
	@BeforeClass
	public void googleloin() throws Exception {
		Screen_Recordings.startRecording("order now page");
		FWA_018 login=new FWA_018();
		login.googlePage();
		OrderNow on=new OrderNow(driver);
		on.orderNow();
		on.confirm();
	}
	
	
	@Test(enabled=false,description="Ordernow btn validation without login")
	public void login() throws Exception {
		
		OrderNow on=new OrderNow(driver);
		
		on.settextpost("N2K2Y7");
		on.setfindstore();
		Thread.sleep(2000);
		on.orderNow();
		
		
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		SoftAssert soft=new SoftAssert();
		String Expected_url=driver.getCurrentUrl();
		String Actual_url="https://web-uat.freshii.com/en/stores/d11700f7-d168-4419-a3ef-3e727faedf78";
		if(Expected_url.equalsIgnoreCase(Actual_url)) {
			soft.assertTrue(true);
			logger.info(" Test passed " + Expected_url);
		}else {
			soft.assertTrue(false);
			logger.info("Test failed ");
		}
		soft.assertAll();
		
		
		
		}
	
	
	//__________________________________ordernow btn validation with Login___________________________________	
	@Test(enabled=true,description="Order now btn validation with login",priority=1)
	public void loginHome() throws Exception {
		
		OrderNow on=new OrderNow(driver);
		
		//on.setordernowbtn();
		Thread.sleep(2000);
		//on.confirm();
		SoftAssert soft=new SoftAssert();
		String Expected_url=driver.getCurrentUrl();
		String Actual_url="https://web-uat.freshii.com/en/stores/d11700f7-d168-4419-a3ef-3e727faedf78";
		if(Expected_url.equalsIgnoreCase(Actual_url)) {
			soft.assertTrue(true);
			logger.info(" Test passed " + Expected_url);
		}else {
			soft.assertTrue(false);
			logger.info("Test failed ");
		}
		soft.assertAll();
		
	}
	
	@Test(description="active link validation",priority=2)
	public void link() throws Exception {
		
		 OrderNow on=new OrderNow(driver);
		 //on.orderNow();
		// on.confirm();
		 
		//no. of item present in menu
		 
		 int num=on.Headerlinks().size();
		 
		 logger.info(num  + "Menu items available");
		 
		 //Print menu items
		 List<WebElement>MenuItem=on.Headerlinks();
		 for (WebElement element : MenuItem) {
			    logger.info(element.getText());
			   //element.click();
			}
		
		if(on.Headerlinks.isEnabled()) {
			on.Headerlinks.click();
			logger.info("links are clickable");		
		}else {
			logger.info("Links are nonclickable ");
		}
		 
		
	}
	
	@Test(enabled=true,description="Dietry preferences validation",priority=4)
	public void dietrybtn() throws Exception {
		 OrderNow on=new OrderNow(driver);
		 SoftAssert softAssert=new SoftAssert();
		 //on.orderNow();
		// on.confirm();
		 
		 on.dietpreferences();
		Thread.sleep(2000);
		String exptext=on.diettext().getText();
		String acttext="Dietary Restrictions";
		 if( exptext.equalsIgnoreCase(acttext)) {
			 logger.info("Test Passed  "+ exptext);
		 }else {
			 logger.info(" test failed  " + acttext);
		 }
		 
		 String parentWindowHandle = driver.getWindowHandle();
		 for (String windowHandle : driver.getWindowHandles()) {
		     if (!windowHandle.equals(parentWindowHandle)) {
		         driver.switchTo().window(windowHandle);
		         break;
		     }
		 }
		 int num=on.dietcheckboxes().size();
		 logger.info(num  + " checkbox available");
		//List <WebElement> diet= on.dietcheckboxes();
		// for (WebElement checkbox : diet) {
			// logger.info(checkbox.getText());
			   // checkbox.click();
		//	}*/
		 
		  //List<WebElement> chBoxes = new ArrayList<WebElement>();
          //chBoxes.add(diet.get(4));
         
			 
		//on.milk().click();
		 JavascriptExecutor executor = (JavascriptExecutor) driver;
		    executor.executeScript("arguments[0].click();",on.milk());
		 on.savebtn().click();
		 driver.switchTo().window(parentWindowHandle);
		 
		 {
				softAssert.assertTrue(false);
				try {
					captureScreen(driver, "reset button");
				} catch (IOException e) {
					e.printStackTrace();
				}
		
		 }
	
	

	}
	
	@Test(description="pocketbtn validation",priority=3)
	public void menu() throws Exception {
		
		 OrderNow on=new OrderNow(driver);
		 //on.orderNow();
		 //on.confirm();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		 on.pocket();
		 boolean result = driver.getPageSource().contains("Freshii");
		 logger.info(result);
		 
		 
		 //List<WebElement>items=on.pocketbtn();
		 int num=on.pocketbtn().size();
		 logger.info(num  + " Menu item available");
		 
		 List<WebElement>item=on.pocketbtn();
		 for (WebElement element : item) {
			String items1=element.getText();
			    logger.info(items1);
			   
			}
		
		//if(on.Headerlinks.isEnabled()) {
			//on.Headerlinks.click();
			//logger.info("links are clickable");		
		//}else {
		//	logger.info("Links are nonclickable ");
		//}
		 
	}
	@AfterClass
		public void closerecording() throws Exception{
		Screen_Recordings.stopRecording();
			
		}
	
	}

	
	
	



