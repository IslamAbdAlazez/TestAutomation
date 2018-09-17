package com.linkedIn.maven;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeClass;

public class profileEdit {
	
	WebDriver browserDriver ;
	profileEditPageFactory pepf;
	feedPage fp;
	logIn signIn;
	generalClass glc = new generalClass();
	String [][] userData;
	
	 @BeforeClass	 
	  public void BeforeClass() {
		 System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
			browserDriver= new ChromeDriver();
			browserDriver.manage().window().maximize();
		  	browserDriver.manage().deleteAllCookies();
			browserDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			browserDriver.get("https://www.linkedin.com/");	
			pepf = new profileEditPageFactory(browserDriver);
			fp = new feedPage();
			signIn = new logIn(browserDriver);
	  }
	 
	 @BeforeTest ()  
     @DataProvider(name="validUserData")
	  public String[][] getValidUserCredentials() throws IOException 
	  {	
			userData = glc.readDataFromExcel(1);
			String [][] emailAndPassword = new String[1][2];
			emailAndPassword[0][0] = userData[0][2].toString();
			emailAndPassword[0][1] = userData[0][3].toString(); 	
			return emailAndPassword;
	  }
	 
	 // Added this constructor for cases that call this class without WebDriver
	 public profileEdit() {
		 
	}
	// Added this constructor for cases that call this class with WebDriver
	 public  profileEdit(WebDriver driver) {
		browserDriver = driver;
		pepf = new profileEditPageFactory(browserDriver);
	}
	
  @Test (priority = 10, dataProvider = "validUserData")
  public void addIntroProfileSection(String email, String password) {	  
	  signIn.successfulUserLogIn(email, password);
	  fp.openFeedPage(email, password, true, browserDriver); // true her means that the user is already logged in so there is no need to login again 
	  try {
		pepf.addIntroProfileSection(browserDriver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	  assertEquals(pepf.summaryDiv.getAttribute("innerText").contains("This is a demo summary text"), true);	 
  }
  
  @Test (priority = 20)
  public void addBackgroundProfileSection () {
	  pepf.addBackgroundProfileSection(browserDriver);
	  assertEquals(pepf.companyNameSpan.getAttribute("innerText").contains("This is a demo position company"), true);
  }
  
  @Test(priority = 30)
  public void addSkillsProfileSection() {
	  pepf.addSkillsProfileSection(browserDriver);
	  browserDriver.navigate().refresh();
	  glc.waitForPageLoad(30, browserDriver);
  }
  
  @AfterClass (enabled = false)
  public void afterTest () 
  {
	  browserDriver.quit();
  }
}
