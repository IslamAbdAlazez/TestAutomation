package saudiPost.mailOperations.registerItems;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
//import java.util.function.Predicate;
import java.util.function.Predicate;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class general {
	
	static WebDriver browserDriver ;	

	public void openPage(String fullPathCommaDele, String pageName)  
	{
		String currPointerText;
		String[] fullPath = fullPathCommaDele.split(",");
		for (int i = 0; i < fullPath.length; i++) {
			currPointerText = fullPath[i].toString();
			WebElement currPointer = browserDriver.findElement(By.linkText(currPointerText));
			currPointer.click();
			browserDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			currPointerText="";
		}
		try {
			WebElement pageLnk = browserDriver.findElement(By.partialLinkText(pageName));
			pageLnk.click();
		} catch (Exception e) {
			
			Actions dragger = new Actions(browserDriver);
			WebElement draggablePartOfScrollbar = browserDriver.findElement(By.className("scrollable-bar-handle"));
			int numberOfPixelsToDragTheScrollbarDown = 325;
			dragger.moveToElement(draggablePartOfScrollbar).clickAndHold().moveByOffset(0,numberOfPixelsToDragTheScrollbarDown).release().perform();
			
			/*WebElement element = browserDriver.findElement(By.partialLinkText(pageName));
			((JavascriptExecutor) browserDriver).executeScript("arguments[0].scrollIntoView(true);", element);*/
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			//element.click();
		}
		
		//browserDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		/*try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		new WebDriverWait(browserDriver, 30).until(
		          webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
	}
	
	public void invokeBrowser() 
	{		
		browserDriver.manage().deleteAllCookies();
		browserDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		browserDriver.get("https://mops.sp.com.sa/");
		/*try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//browserDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);	
		browserDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	public void login(String uName, String pass) 
	{
		WebElement usrNameTxt = browserDriver.findElement(By.id("UserNamevalid"));
		usrNameTxt.sendKeys(uName);
		WebElement pwdTxt = browserDriver.findElement(By.id("Passwordvalid"));
		pwdTxt.sendKeys(pass);
		pwdTxt.sendKeys(Keys.ENTER);
		/*try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		new WebDriverWait(browserDriver, 30).until(
		          webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
	}	
	
	public WebElement bringTableRow(String tableName, String itemId) {
		
		browserDriver.findElement(By.cssSelector("span[aria-labelledby='select2-itemsLengthMenu-container']")).click();
		browserDriver.findElement(By.className("select2-search__field")).sendKeys(Integer.toString(65));
		browserDriver.findElement(By.className("select2-search__field")).sendKeys(Keys.ENTER);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JavascriptExecutor jse = (JavascriptExecutor)browserDriver;
		jse.executeScript("window.scrollTo(0,document.body.scrollHeight)", "");
		WebElement DataTable = browserDriver.findElement(By.id(tableName));
		List<WebElement> TotalRowCount = DataTable.findElements(By.xpath("//*[@id='ItemsDataTable']/tbody/tr"));		
		if (itemId !="") {
			int RowIndex=1;		
			for (WebElement rowElement:TotalRowCount) {
				List<WebElement> TotalColumnCount=rowElement.findElements(By.xpath("td"));
				 int ColumnIndex=1;
				 for (WebElement colElement:TotalColumnCount) {
					if (colElement.getText().toLowerCase().equalsIgnoreCase(itemId.toLowerCase().trim()) ) {
						return rowElement;
					}
					 ColumnIndex=ColumnIndex+1;
				}
				 RowIndex=RowIndex+1;
			}
			return null;
		}
		else {
			List<WebElement> tableRows = DataTable.findElements(By.tagName("tr"));
			return tableRows.get(1);
		}		
	}
	
	public List<XSSFRow> readExcelRows(String fileFullPath, int sheetIndex,int startRowIndex, int endRowIndex) throws IOException {
		
		File srcFile = new File(fileFullPath);
		FileInputStream fis = new FileInputStream(srcFile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		List<XSSFRow> returnedRows = new ArrayList<XSSFRow>();
		for (int i = startRowIndex; i < endRowIndex; i++) {
			returnedRows.add(wb.getSheetAt(sheetIndex).getRow(i));
		}		
		wb.close();
		fis.close();
		return returnedRows;
	}
	
	public XSSFRow findExcelRow(String fileFullPath, int sheetIndex,String itemNo) throws IOException {
		File srcFile = new File(fileFullPath);
		FileInputStream fis = new FileInputStream(srcFile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet currSheet = wb.getSheetAt(sheetIndex);
		String itemNoCellVal;
		for (int i = 1; i < currSheet.getPhysicalNumberOfRows()-1; i++) {
			itemNoCellVal =currSheet.getRow(i).getCell(3).getStringCellValue().toLowerCase().trim(); 
			if (itemNoCellVal.equals(itemNo.toLowerCase().trim()))/* NOW THE ROW IS FOUND*/ {
				return currSheet.getRow(i);				
			}
			itemNoCellVal="";			
		}	
		wb.close();
		return null;
	}
	
	public ArrayList<String> readExcelRow(String fileFullPath, int sheetIndex,String itemNo) throws IOException {
		XSSFRow currentRow = this.findExcelRow(fileFullPath, sheetIndex, itemNo);
		if (currentRow!=null) {
			ArrayList<String> returnRow = new ArrayList<String>();		
				for (int j = 3; j < currentRow.getLastCellNum(); j++) {
					if (j ==7 || j==13 || j==15 || j==16||j==17 || j==18 || j==19 || j==20 || j==21 || j==22 || j==23) /* For excel columns that has formula*/ {
						XSSFCell itemCell = currentRow.getCell(j);
						if (itemCell !=null) {
							itemCell.setCellType(itemCell.CELL_TYPE_STRING);							
						}						
					}
					if (currentRow.getCell(j)!=null) {
						returnRow.add(currentRow.getCell(j).getStringCellValue());
					}
					else {
						returnRow.add("");
					}
				}					
			return returnRow;
		}
		return null;
	}
	
	// This method has been placed here in the general class because it will be called twice from register items page and from the create statement page	
	public String createStatement(String mndoobId,boolean printOrNot, boolean pageSource /*true means this method will be invoked through register item page & false means this method will be invoked with in the create statement page */) {
		if (pageSource) {
			WebElement createStatementBtn = browserDriver.findElement(By.id("AddStatementBtn"));		
			createStatementBtn.click();
			browserDriver.manage().timeouts().pageLoadTimeout(7,TimeUnit.SECONDS);
		}		
		if (mndoobId !="") {
			WebElement mndoobIdTxtBox = browserDriver.findElement(By.id("representativeNationalId"));
			mndoobIdTxtBox.sendKeys(mndoobId);
		}		
		WebElement exportStatement = browserDriver.findElement(By.id("saveStatement"));
		exportStatement.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//browserDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		WebElement statementNoLbl = browserDriver.findElement(By.cssSelector("text[text-anchor='middle']"));
		String statementNo = statementNoLbl.getText(); 
		if (printOrNot) {
			WebElement printBtn = browserDriver.findElement(By.id("PrintReport"));
			((JavascriptExecutor)browserDriver).executeScript("arguments[0].click();", printBtn);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Robot r = new Robot();
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);				
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WebElement cancelBtn = browserDriver.findElement(By.cssSelector("button[class='btn btn-default none-printable closepopup']"));
			((JavascriptExecutor)browserDriver).executeScript("arguments[0].click();", cancelBtn);
		}
		else {
			WebElement cancelBtn = browserDriver.findElement(By.cssSelector("button[class='btn btn-default none-printable closepopup']"));
			((JavascriptExecutor)browserDriver).executeScript("arguments[0].click();", cancelBtn);
			//cancelBtn.click();
		}		
		return statementNo;
	}
	
	public static WebDriver main(String[] args) {
		String browserName = args[0].toString().toLowerCase();		
		switch (browserName) {
		// for Chrome
		case "chrome":			
			System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\chromedriver.exe");
			browserDriver= new ChromeDriver();
			browserDriver.manage().window().maximize();
			break;
		case "firefox":
			// for Firefox
			System.setProperty("webdriver.gecko.driver", "E:\\Selenium\\geckodriver.exe");
			browserDriver= new FirefoxDriver();
			break;
		default:
			break;
		}
		return browserDriver;
	}
}
