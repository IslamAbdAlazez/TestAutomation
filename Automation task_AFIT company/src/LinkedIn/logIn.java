package LinkedIn;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class logIn {
	
	static WebDriver browserDriver ;
	String[] logInCredentials;
	
  // This method is used to get specific data from the excel row returned from dataprovider method	
  @Test (dataProviderClass = generalClass.class, dataProvider= "readDataFromExcel", priority = 1000)
  /*@DataProvider (name="filteredData")*/
  public String[] filterData (String firstName, String lastName, String eMail, String password, String country, String zipCode) 
  {
	  logInCredentials = new String[] {eMail, password};
	  return logInCredentials;	  
  }
  
  @Test (/*dataProvider = "filteredData",*/ priority = 2000)
  public void userLogIn(/*String eMail , String password*/) {
	  logInPageFactory lpf = new logInPageFactory(browserDriver);
	  lpf.signInUser(logInCredentials[0], logInCredentials[1]);
  }

  
  @BeforeClass
  public void beforeClass() {
	  
	  System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
		browserDriver= new ChromeDriver();
		browserDriver.manage().window().maximize();
	  	browserDriver.manage().deleteAllCookies();
		browserDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		browserDriver.get("https://www.linkedin.com/");
  }

}
