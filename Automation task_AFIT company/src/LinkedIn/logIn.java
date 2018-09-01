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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.BeforeClass;

public class logIn {
	
	WebDriver browserDriver ;
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
			lpf = new logInPageFactory(browserDriver);			
	  }	 
	 
	 // Added this constructor for cases that call this class without WebDriver
	 public logIn() {
		 // Setting email and password in the constructor for the case of another class calling this class
		 try {
				getValidUserCredentials();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	// Added this constructor for cases that call this class with WebDriver
	 public  logIn(WebDriver driver) {
		browserDriver = driver;
		lpf = new logInPageFactory(browserDriver);
		
		// Getting email and password in the constructor for the case of another class calling this class
		try {
			getValidUserCredentials();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	 
  // This method is used to get specific data from the excel row returned from LinkedIn.generalClass.readDataFromExcel(int) method	
	
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
  
	@BeforeTest ()
	@DataProvider(name="inValidUserData")
	public Object[][] getInvalidUserCredentials() throws IOException 
	{	
		String [][] excelRow = new String[][] { glc.readDataFromExcel(3)[3]};
		String [][] returnedArray = new String[1][2];
		returnedArray[0][0] = excelRow[0][2].toString();
		returnedArray[0][1] = excelRow[0][3].toString(); 	
		return returnedArray;  
	}
	
  @Test (priority = 20, dataProvider="validUserData")
  public void successfulUserLogIn(String eMail, String password) {	  
	  browserDriver.get("https://www.linkedin.com/");
	  try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  lpf.signInUser(eMail, password);
	  assertEquals(browserDriver.getTitle(), "LinkedIn","LinkedIn home page is not displayed");
  }

  @Test (priority = 10, dataProvider="inValidUserData")
  public void notSuccessfulUserLogIn(String eMail, String password) throws Exception {	  
	  lpf.signInUser(eMail, password);	  
	  assertTrue(browserDriver.findElement(By.id("session_key-login-error")).isDisplayed(),"System did not displayed the error validation");
	  Thread.sleep(5000);
  }
 
  @AfterClass
  public void afterTest () 
  {
	  browserDriver.quit();
  }
}
