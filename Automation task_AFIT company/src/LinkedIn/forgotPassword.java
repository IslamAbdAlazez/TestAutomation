package LinkedIn;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class forgotPassword {
	
	WebDriver browserDriver ;
	forgotPasswordPageFactory fppf;
	logInPageFactory lpf;
	generalClass glc = new generalClass();
	
	 @BeforeClass
	  public void BeforeClass() {
		 System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
			browserDriver= new ChromeDriver();
			browserDriver.manage().window().maximize();
		  	browserDriver.manage().deleteAllCookies();
			browserDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			browserDriver.get("https://www.linkedin.com/");
			fppf = new forgotPasswordPageFactory(browserDriver);
			lpf = new logInPageFactory(browserDriver);
	  }
  // This method is used to get specific data from the excel row returned from LinkedIn.generalClass.readDataFromExcel() method	
	
	@BeforeTest ()  
	@DataProvider(name="validUserData")
  public String[][] getValidUserCredentials() throws IOException 
  {	
		return new String[][] { glc.readDataFromExcel()[0]};	  
  }
  
	@BeforeTest ()
	@DataProvider(name="inValidUserData")
	public Object[][] getInvalidUserCredentials() throws IOException 
	{	
	   return new String[][] { glc.readDataFromExcel()[1]};		  
	}
	
  @Test (priority = 20, dataProvider="validUserData")
  public void successfulResetPassword(String firstName, String lastName, String eMail, String password, String profileLocation, String postalCode) {	  
	  browserDriver.get("https://www.linkedin.com/");
	  lpf.forgotPassword();
	  fppf.resetPassword(eMail);
	  Assert.assertEquals(fppf.validateEMailAddress(), false);
  }
    
  @Test (priority = 10, dataProvider="inValidUserData")
  public void unSuccessfulResetPassword(String firstName, String lastName, String eMail, String password, String profileLocation, String postalCode) throws Exception {	  
	  lpf.forgotPassword();
	  fppf.resetPassword(eMail);
	  Assert.assertEquals(fppf.validateEMailAddress(), true);
	  Thread.sleep(5000);
  }
 
  @AfterClass
  public void afterTest () 
  {
	  browserDriver.quit();
  }
}
