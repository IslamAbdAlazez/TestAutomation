package LinkedIn;

import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class generalClass {
	
	public static String filePath = System.getProperty("user.dir")+"\\LinkedInUserData.xlsx";
	static String SheetName= "Sheet1";

  @DataProvider (name="readDataFromExcel")
  public static Object[][] readDataFromExcel() throws IOException {
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
          for(int i=0; i<2-1; i++) // 2 Here to get only 1 row. You can replace 2 with 3 to get 2 rows and so on.
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
    };
  }

