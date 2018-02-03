package saudiPost.mailOperations.registerItems;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class general {
	
	static WebDriver browserDriver ;
	XSSFWorkbook wb;

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
		
		WebElement pageLnk = browserDriver.findElement(By.partialLinkText(pageName));
		pageLnk.click();
		browserDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	public void invokeBrowser() 
	{		
		browserDriver.manage().deleteAllCookies();
		browserDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		browserDriver.get("http://212.100.202.154:5002");
		/*try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//browserDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);		
	}
	public void login(String uName, String pass) 
	{
		WebElement usrNameTxt = browserDriver.findElement(By.id("UserNamevalid"));
		usrNameTxt.sendKeys(uName);
		WebElement pwdTxt = browserDriver.findElement(By.id("Passwordvalid"));
		pwdTxt.sendKeys(pass);
		pwdTxt.sendKeys(Keys.ENTER);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//browserDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}	
	
	public XSSFWorkbook initializeExcel(String fileFullPath) throws IOException {
		//Prepairing to use Excel
		File srcFile = new File(fileFullPath);
		FileInputStream fis = new FileInputStream(srcFile);
		this.wb = new XSSFWorkbook(fis);		
		return wb;
	}
	
	public List<String> readExcelCollumn(String fileFullPath, int sheetIndex,int rowIndex,int cellIndex, int cellsCount) throws IOException {
		//XSSFSheet sheet;
		//sheet = wb.getSheetAt(sheetIndex);
		wb = this.initializeExcel(fileFullPath);
		List<String> cellsValues = new ArrayList<String>();
		for (int i = rowIndex; i < cellsCount; i++) {
			cellsValues.add(wb.getSheetAt(sheetIndex).getRow(rowIndex).getCell(cellIndex).getStringCellValue());
		}
		
		wb.close();
		return cellsValues;
	}
	
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
		browserDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		WebElement statementNoLbl = browserDriver.findElement(By.cssSelector("text[text-anchor='middle']"));
		String statementNo = statementNoLbl.getText(); 
		if (printOrNot) {
			WebElement printBtn = browserDriver.findElement(By.id("PrintReport"));
			((JavascriptExecutor)browserDriver).executeScript("arguments[0].click();", printBtn);
			//printBtn.click();
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
			System.setProperty("webdriver.gecko.driver", "E:\\Selenium\\geckodriver-v0.19.1-win64\\geckodriver.exe");
			browserDriver= new FirefoxDriver();
			break;
		default:
			break;
		}
		return browserDriver;
	}
	

}
