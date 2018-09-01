package LinkedIn;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class feedPage {
	
	WebDriver browserDriver ;	
	generalClass glc = new generalClass();
	feedPageFactory fpf;
	
	 @BeforeClass
	  public void BeforeClass() {
		 System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
			browserDriver= new ChromeDriver();
			browserDriver.manage().window().maximize();
		  	browserDriver.manage().deleteAllCookies();
			browserDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			browserDriver.get("https://www.linkedin.com/");
			
	  }
  // This method is used to get specific data from the excel row returned from LinkedIn.generalClass.readDataFromExcel() method	
	
	 @BeforeTest ()  
		@DataProvider(name="validUserData")
	  public String[][] getValidUserCredentials() throws IOException 
	  {	
			String [][] excelRow = new String[][] { glc.readDataFromExcel(1)[1]};
			String [][] returnedArray = new String[1][2];
			returnedArray[0][0] = excelRow[0][2].toString();
			returnedArray[0][1] = excelRow[0][3].toString(); 	
			return returnedArray;
	  }
	
  @Test (priority = 20, dataProvider="validUserData")
  public void openFeedPage(String email, String password, boolean isLoggedIn, WebDriver driver) {	  
	  if (isLoggedIn==false)  /* LogIn first*/ {
		  logIn signIn = new logIn(browserDriver);	  
		  signIn.successfulUserLogIn(email, password);
	}	  
	    browserDriver = driver;
		glc.waitForPageLoad(35, browserDriver);
		fpf = new feedPageFactory(driver);
		fpf.openProfile();			  
  }    
 
  @AfterClass(enabled = false)
  public void afterTest () 
  {
	  browserDriver.quit();
  }
}
