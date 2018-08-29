package LinkedIn;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;

public class generalClass {
	JavascriptExecutor js ;
	public static String filePath = System.getProperty("user.dir")+"\\LinkedInUserData.xlsx";
	static String SheetName= "Sheet1";
	
public String[][] readDataFromExcel() throws IOException {
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
      String Data[][]= new String[RowNum-1][ColNum]; 
      DataFormatter formatter = new DataFormatter(); 
      
      //Loop work for Rows
          for(int i=0; i<RowNum-1; i++) // 2 Here to get only 1 row. You can replace 2 with 3 to get 2 rows and so on.
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
          }
      workbook.close();
      return Data;
    }
	
	public void waitForPageLoad (int timeInSeconds) {
		
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
  }