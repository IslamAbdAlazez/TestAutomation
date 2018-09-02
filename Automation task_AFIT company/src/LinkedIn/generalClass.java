package LinkedIn;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

public class generalClass {
	JavascriptExecutor js ;
	public static String filePath = System.getProperty("user.dir")+"\\LinkedInUserData.xlsx";
	static String SheetName= "Sheet1";

	@DataProvider (name = "readDataFromExcel")
public String[][] readDataFromExcel(int desiredRowZeroBasedNum) throws IOException {
	  FileInputStream fileInputStream= new FileInputStream(filePath); 
      
		// Get the workbook		
		Workbook workbook = new XSSFWorkbook (fileInputStream);
		
		// get my sheet from workbook
      Sheet worksheet=workbook.getSheet(SheetName);
      
      //get my Row which start from 0
      Row Row=worksheet.getRow(0);    
      
      // get last ColNum by referencing the first row 
      int ColNum= Row.getLastCellNum(); 
      
      // pass the count data in array 
      String Data[][]= new String[1][ColNum]; 
      DataFormatter formatter = new DataFormatter(); 
            
              XSSFRow row= (XSSFRow) worksheet.getRow(desiredRowZeroBasedNum);
              //Loop work for colNum
              for (int j=0; j<ColNum; j++) 
              {
                  if(row==null)
                      Data[0][j]= "";
                   else
                  {
                      XSSFCell cell= row.getCell(j);
                      if(cell==null)
                      	//if it get Null value it pass no data 
                          Data[0][j]= ""; 
                      else
                      {
                          String value= formatter.formatCellValue(cell);                          
                          Data[0][j]=value;                             
                      }
                  }
              }             
          
      workbook.close();
      return Data;
    }
	
	public void waitForPageLoad (int timeInSeconds, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// This loop will rotate for the number passed in the timeInSeconds input parameter to check If page Is ready after every 1 second.
          for (int i = 0; i < timeInSeconds; i++) {
          try {
              Thread.sleep(1000);
          } catch (InterruptedException e) {
          }
          // To check page ready state.
          if (js.executeScript("return document.readyState").toString().equals("complete")) {
              break;
          }
      }
	}
	public void openNewTab(WebDriver driver, String URL) {
		
		((JavascriptExecutor)driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get(URL);
	}
  }