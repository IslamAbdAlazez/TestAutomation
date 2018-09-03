package LinkedIn;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import LinkedIn.gmail.gmailLogin;
import LinkedIn.gmail.gmailPageFactory;

public class forgotPassword {
	
	WebDriver browserDriver ;
	forgotPasswordPageFactory fppf;
	logInPageFactory lpf;
	gmailPageFactory gpf ;
	resetPasswordPageFactory rppf;
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
			gpf = new gmailPageFactory(browserDriver);
			rppf = new resetPasswordPageFactory(browserDriver);
	  }
  // This method is used to get specific data from the excel row returned from LinkedIn.generalClass.readDataFromExcel() method	
	
	@BeforeClass ()  
	@DataProvider(name="validUserData")
  public String[][] getValidUserCredentials() throws IOException 
  {	
		return new String[][] { glc.readDataFromExcel(2)[2]};	  
  }
  
	@BeforeClass ()
	@DataProvider(name="inValidUserData")
	public Object[][] getInvalidUserCredentials() throws IOException 
	{	
	   return new String[][] { glc.readDataFromExcel(3)[3]};		  
	}
	
  @Test (priority = 20, dataProvider="validUserData")
  public void successfulResetPassword(String firstName, String lastName, String eMail, String password, String profileLocation, String postalCode) {	  
	  browserDriver.get("https://www.linkedin.com/");
	  lpf.forgotPassword();
	  fppf.resetPassword(eMail);
	  if (fppf.validateEMailAddress()==false) {
		glc.openNewTab(browserDriver, "https://www.gmail.com");
		gmailLogin gLogin = new gmailLogin(browserDriver);
		gLogin.logIn(4);
		glc.waitForPageLoad(35, browserDriver);
		gpf.readMail();
		rppf.completeResetPassword("P@ssw0rd64");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(rppf.confirmPasswordChanged(), true);
	}	  
  }
    
  @Test (priority = 10, dataProvider="inValidUserData", enabled = false)
  public void notSuccessfulResetPassword(String firstName, String lastName, String eMail, String password, String profileLocation, String postalCode) throws Exception {	  
	  lpf.forgotPassword();
	  fppf.resetPassword(eMail);
	  Assert.assertEquals(fppf.validateEMailAddress(), true);
	  Thread.sleep(5000);
  }
 
  @AfterClass()
  public void afterTest () 
  {
	  browserDriver.quit();
  }
}
