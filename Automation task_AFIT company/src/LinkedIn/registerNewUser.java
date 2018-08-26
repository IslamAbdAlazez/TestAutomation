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
	public static String filePath = System.getProperty("user.dir")+"\\LinkedInUserData.xlsx";
	static String SheetName= "Sheet1";
	
	@DataProvider
	public static Object[][] readRegisterationData() throws IOException
	{
		FileInputStream fileInputStream= new FileInputStream(filePath); 
        
		// Get the workbook		
		Workbook workbook = new XSSFWorkbook (fileInputStream);
		
		// get my sheet from workbook
        Sheet worksheet=workbook.getSheet(SheetName);
        
        //get my Row which start from 0
        Row Row=worksheet.getRow(0);    
        
        // count the number of Rows
        int RowNum = worksheet.getPhysicalNumberOfRows();
        
        // get last ColNum by referencing the first row 
        int ColNum= Row.getLastCellNum(); 
        
        // pass the count data in array 
        Object Data[][]= new Object[RowNum-1][ColNum]; 
        DataFormatter formatter = new DataFormatter(); 
        
        //Loop work for Rows
            for(int i=0; i<RowNum-1; i++) 
            {  
                XSSFRow row= (XSSFRow) worksheet.getRow(i+1);
                //Loop work for colNum
                for (int j=0; j<ColNum; j++) 
                {
                    if(row==null)
                        Data[i][j]= "";
                     else
                    {
                        XSSFCell cell= row.getCell(j);
                        if(cell==null)
                        	//if it get Null value it pass no data 
                            Data[i][j]= ""; 
                        else
                        {
                            String value= formatter.formatCellValue(cell);                          
                            Data[i][j]=value; 
                            
                        }
                    }
                }
             // If you want all the data that exist in the excel sheet comment the below line
                break;
            }
        workbook.close();
        return Data;
    }	
	
  @Test
  (dataProvider="readRegisterationData")
  public void register(String firstName, String lastName, String eMail, String password, String country, String zipCode ) {
	  registerPageFactory rpf = new registerPageFactory(browserDriver);
	  rpf.registerUser(firstName, lastName, eMail, password, country, zipCode);
  }
  @BeforeClass
  public void beforeClass() {
	  
	  	System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver.exe");
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
