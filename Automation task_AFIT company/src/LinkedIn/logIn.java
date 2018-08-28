package LinkedIn;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class logIn {
	
	static WebDriver browserDriver ;
	String[][] validLogInCredentials;
	String[][] inValidLogInCredentials;
	generalClass glc = new generalClass();
	
  // This method is used to get specific data from the excel row returned from LinkedIn.generalClass.readDataFromExcel() method	
	
	@BeforeTest ()  
	@DataProvider(name="validUserData")
  public String[][] getValidUserCredentials() throws IOException 
  {	
		return validLogInCredentials = new String[][] { glc.readDataFromExcel()[0]};	  
  }
  
	@BeforeTest ()
	@DataProvider(name="inValidUserData")
	public Object[][] getInvalidUserCredentials() throws IOException 
	{	
	   return inValidLogInCredentials = new String[][] {glc.readDataFromExcel()[1]};		  
	}
	
  @Test (priority = 20, dataProvider="validUserData")
  public void successfulUserLogIn(String firstName, String lastName, String eMail, String password, String profileLocation, String postalCode) {
	  logInPageFactory lpf = new logInPageFactory(browserDriver);
	  lpf.signInUser(eMail, password);
	  assertEquals(browserDriver.getTitle(), "LinkedIn","LinkedIn home page is not displayed");
  }

  @Test (priority = 10, dataProvider="inValidUserData")
  public void unSuccessfulUserLogIn(String firstName, String lastName, String eMail, String password, String profileLocation, String postalCode) throws Exception {
	  logInPageFactory lpf = new logInPageFactory(browserDriver);
	  lpf.signInUser(eMail, password);	  
	  assertTrue(browserDriver.findElement(By.id("session_key-login-error")).isDisplayed(),"System did not displayed the error validation");
	  Thread.sleep(5000);
  }

  
  @BeforeTest
  public void beforeTest() {
	  
	  System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
		browserDriver= new ChromeDriver();
		browserDriver.manage().window().maximize();
	  	browserDriver.manage().deleteAllCookies();
		browserDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		browserDriver.get("https://www.linkedin.com/");
  }
  @AfterTest
  public void afterTest () 
  {
	  browserDriver.quit();
  }
}
