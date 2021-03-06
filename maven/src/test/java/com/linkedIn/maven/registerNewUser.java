package com.linkedIn.maven;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class registerNewUser {
	
	String [][] userData;
	generalClass glc;
	static WebDriver browserDriver ;

	 @BeforeClass
	  public void beforeClass() throws IOException {
		  
		 	glc = new generalClass();
		 	userData = glc.readDataFromExcel(5);
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
			browserDriver= new ChromeDriver();
			browserDriver.manage().window().maximize();
		  	browserDriver.manage().deleteAllCookies();
			browserDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			browserDriver.get("https://www.linkedin.com/");
			
	  }
	
  @Test (priority = 20)
  public void successfulRegisteration() {
	  registerPageFactory rpf = new registerPageFactory(browserDriver);
	  rpf.successfulRegisteration(userData[0][0], userData[0][1], userData[0][2], userData[0][3], userData[0][4], userData[0][5], userData[0][6], userData[0][7], userData[0][8]);
  }
 
  @Test (priority = 10, enabled = false)
  public void notSuccessfulRegisteration() {
	  registerPageFactory rpf = new registerPageFactory(browserDriver);
	  rpf.notSuccessfulRegisteration(userData[0][0], userData[0][1], userData[0][2]);
	  Assert.assertEquals(rpf.passwordValidation.isDisplayed(), true);
  }

  @AfterClass (enabled = false)
  public void AfterClass() {
	  browserDriver.quit();
  }
}
