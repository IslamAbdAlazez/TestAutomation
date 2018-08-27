package LinkedIn;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class registerNewUser {
	
	static WebDriver browserDriver ;
		
  @Test
  (dataProviderClass = generalClass.class, dataProvider= "readDataFromExcel")
  public void register(String firstName, String lastName, String eMail, String password, String country, String zipCode) {
	  registerPageFactory rpf = new registerPageFactory(browserDriver);
	  rpf.registerUser(firstName, lastName, eMail, password, country, zipCode);
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

  @BeforeTest
  public void beforeTest() {
  }

}
