package saudiPost.mailOperations.registerItems;

import java.util.ArrayList;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class addItems {

	static WebDriver browserDriver ;
	static saudiPost.mailOperations.registerItems.general genCls = new general();
	static DataFormatter formatter = new DataFormatter();
	
	public void setSender(String mainCorpName,String subCorpName) 
	{
		WebElement mainCrp = browserDriver.findElement(By.id("select2-mainCorporateCustomers-container"));
		//Select dropdown = new Select(mainCrp);
		//dropdown.selectByVisibleText("Ê“«—… «·⁄„·");
		mainCrp.click();
		//browserDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		WebElement gehaMainText = browserDriver.findElement(By.className("select2-search__field"));
		gehaMainText.sendKeys(mainCorpName);
		gehaMainText.sendKeys(Keys.ENTER);
		WebElement gehaSub = browserDriver.findElement(By.id("select2-subCorporateCustomers-container"));
		gehaSub.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement gehaSubText = browserDriver.findElement(By.className("select2-search__field"));
		gehaSubText.sendKeys(subCorpName);
		gehaSubText.sendKeys(Keys.ENTER);
		//WebElement gehaMain = browserDriver.findElement(By.xpath("//li[contains(.,'««·ﬂ·Ì… «· ﬁ‰ÌÂ »⁄‰Ì“…')]"));/*.findElement(By.id("select2-mainCorporateCustomers-container"));*/ 
		//gehaMain.click();
		/*for (WebElement option : options) {
			if (option.getText().equalsIgnoreCase("Ê“«—… «·⁄„·")) {
				option.click();
			}
		}*/		
	}
	public void setItemDetails (/*boolean itemDirection, String itemType, String itemWeight, String orgNo ,*/ int startItemNOCellNo , int itemsCount) throws IOException
	{		
		WebElement inputInternal = browserDriver.findElement(By.id("Internal"));
		WebElement inputExternal = browserDriver.findElement(By.id("External"));
		WebElement itemTypeCombo = browserDriver.findElement(By.id("select2-itemType-container"));		
		WebElement itemWeightTextBox = browserDriver.findElement(By.id("weight"));
		WebElement orgCode = browserDriver.findElement(By.id("OriginalCode"));
		WebElement repeatOriginalCodeChk = browserDriver.findElement(By.id("repeatOriginalCode"));
		WebElement itemNO = browserDriver.findElement(By.id("packageno"));
		WebElement itemDescBtn = browserDriver.findElement(By.id("addItemDescriptionBtn"));
		ArrayList<XSSFRow> excelRows = new ArrayList<XSSFRow>();
		XSSFRow currentRow ;			    
		int endItemNO = startItemNOCellNo+itemsCount; 
		//curCellsVals = genCls.readExcelCollumn("E:\\Selenium\\ItemsData.xltm", 0, 3,startItemNOCellNo, endItemNO); /*sheet.getRow(i).getCell(3).getStringCellValue();*/
		excelRows.addAll(genCls.readExcelRows("E:\\Selenium\\ItemsData.xltm", 0,startItemNOCellNo, endItemNO));
		// Adding the item details
		for (int i = startItemNOCellNo; i <endItemNO; i++) {					
			currentRow=(XSSFRow)excelRows.get(i-1);
		if (currentRow.getCell(4)!=null && currentRow.getCell(4).getStringCellValue().trim()!="") {
			itemDescBtn.click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WebElement itemDescTextBox = browserDriver.findElement(By.id("ItemDesc"));
			itemDescTextBox.sendKeys(currentRow.getCell(4).getStringCellValue().trim());
			itemDescTextBox = null;
		}			
		if (currentRow.getCell(5).getStringCellValue().trim().equals("œ«Œ·Ì")) 
		{
			inputInternal.click();
		}
		else {
			inputExternal.click();
		}
		itemTypeCombo.click();
		WebElement itemTypeComboSearch = browserDriver.findElement(By.className("select2-search__field"));
		itemTypeComboSearch.sendKeys(currentRow.getCell(6).getStringCellValue().trim());
		itemTypeComboSearch.sendKeys(Keys.ENTER);
		//String itemWeight = formatter.formatCellValue(currentRow.getCell(6));
		XSSFCell itemWeightCell = currentRow.getCell(7);
		itemWeightCell.setCellType(itemWeightCell.CELL_TYPE_STRING);
		itemWeightTextBox.sendKeys(itemWeightCell.toString());
		if (i == startItemNOCellNo) {
			orgCode.sendKeys(currentRow.getCell(8).getStringCellValue().trim());			
		}
		if (repeatOriginalCodeChk.isSelected()==false) {
			repeatOriginalCodeChk.click();
		}					
				itemNO.sendKeys(currentRow.getCell(3).getStringCellValue());
				itemNO.sendKeys(Keys.ENTER);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				currentRow=null;				
		}		
	}	
	public boolean checkSenderStsatus() {
		WebElement mainCrp = browserDriver.findElement(By.xpath("//*[@id=\"RenderBody\"]/div[1]/div[2]/div/div[1]/span/span[1]/span")).findElement(By.id("select2-mainCorporateCustomers-container"));
		// Check if sender can be selected
		String title = mainCrp.getAttribute("title");
		if (title.contains("«Œ — «·ÃÂ… «·—∆Ì”ÌÂ")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void editItems(String[]itemsNos) throws InterruptedException  {
		
		ArrayList currentExcelRow;
		for (int i = 0; i < itemsNos.length; i++) {
			try {
				currentExcelRow = genCls.readExcelRow("E:\\Selenium\\ItemsData.xltm", 0, itemsNos[i]);
				genCls.bringTableRow("ItemsDataTable", currentExcelRow.get(0).toString()).findElement(By.cssSelector("button[data-original-title=' ⁄œÌ·']")).click();
				Thread.sleep(1000);
				WebElement itemTypeCombo = browserDriver.findElement(By.cssSelector("span[aria-labelledby='select2-editItemTypes-container']"));
				itemTypeCombo.click();
				WebElement itemTypeComboText =  itemTypeCombo.findElement(By.xpath("/html/body/span/span/span[1]/input"));
				Thread.sleep(2000);
				itemTypeComboText.sendKeys(currentExcelRow.get(3).toString());
				itemTypeComboText.sendKeys(Keys.ENTER);
				browserDriver.findElement(By.id("editItemWeight")).clear();
				browserDriver.findElement(By.id("editItemWeight")).sendKeys(currentExcelRow.get(4).toString());
				if (currentExcelRow.get(1)!=null && currentExcelRow.get(1).toString() !="") {
					browserDriver.findElement(By.id("editItemDescription")).clear();
					browserDriver.findElement(By.id("editItemDescription")).sendKeys(currentExcelRow.get(1).toString());
				}				
				browserDriver.findElement(By.id("submitEditItemBtn")).click();
				itemTypeCombo = null;
				Thread.sleep(5000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
	public static void main(String[] args) {		
		
		addItems regItemObj = new addItems();
		String[] browserName = new String[1];
		browserName[0]="firefox";
		browserDriver= genCls.main(browserName);
		genCls.invokeBrowser();
		genCls.login("mosimi","P@ssw0rd");
		genCls.openPage("«·⁄„·Ì«  «·»—ÌœÌ…, ”ÃÌ· «·»⁄«∆À"," ﬂÊÌ‰ »Ì«‰");
		/*if(regItemObj.checkSenderStsatus()) {
			regItemObj.setSender("Ê“«—… «·⁄„·", "œÌÊ«‰ Ê“«—… «·⁄„·");
		}
		try {
			regItemObj.setItemDetails(1,10);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//String statementNo = genCls.createStatement("456321",false,true);
		String [] itemsToEdit = new String[3];
		itemsToEdit[0]="RO198510002SA"; itemsToEdit[1] = "RO198510004SA"; itemsToEdit[2] = "RO198510006SA";
		try {
			regItemObj.editItems(itemsToEdit);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
