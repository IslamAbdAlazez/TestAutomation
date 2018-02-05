package saudiPost.mailOperations.registerItems;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class addItems {

	static WebDriver browserDriver ;
	static saudiPost.mailOperations.registerItems.general genCls = new general();

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
	public void setItemDetails (boolean itemDirection, String itemType, String itemWeight, String orgNo , int startItemNOCellNo , int itemsCount) throws IOException
	{		
		WebElement inputInternal = browserDriver.findElement(By.id("Internal"));
		WebElement inputExternal = browserDriver.findElement(By.id("External"));
		WebElement itemTypeCombo = browserDriver.findElement(By.id("select2-itemType-container"));		
		WebElement itemWeightTextBox = browserDriver.findElement(By.id("weight"));
		WebElement orgCode = browserDriver.findElement(By.id("OriginalCode"));
		WebElement repeatOriginalCodeChk = browserDriver.findElement(By.id("repeatOriginalCode"));
		WebElement itemNO = browserDriver.findElement(By.id("packageno"));
		List<String> curCellsVals = new ArrayList<String>();
			    
				int endItemNO = startItemNOCellNo+itemsCount; 
				curCellsVals = genCls.readExcelCollumn("E:\\Selenium\\ItemsData.xlsx", 0, startItemNOCellNo, 3,endItemNO); /*sheet.getRow(i).getCell(3).getStringCellValue();*/
				// Adding the item details
				for (int i = startItemNOCellNo; i <endItemNO; i++) {
		
		if (itemDirection) 
		{
			inputInternal.click();
		}
		else {
			inputExternal.click();
		}
		itemTypeCombo.click();
		WebElement itemTypeComboSearch = browserDriver.findElement(By.className("select2-search__field"));
		itemTypeComboSearch.sendKeys(itemType);
		itemTypeComboSearch.sendKeys(Keys.ENTER);
		itemWeightTextBox.sendKeys(itemWeight);
		if (i == startItemNOCellNo) {
			orgCode.sendKeys(orgNo);			
		}
		if (repeatOriginalCodeChk.isSelected()==false) {
			repeatOriginalCodeChk.click();
		}	
				
				itemNO.sendKeys(curCellsVals.get(i-startItemNOCellNo).toString());
				itemNO.sendKeys(Keys.ENTER);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		
	public static void main(String[] args) {		
		
		addItems regItemObj = new addItems();
		String[] browserName = new String[1];
		browserName[0]="firefox";
		browserDriver= genCls.main(browserName);
		genCls.invokeBrowser();
		genCls.login("mosimi","P@ssw0rd");
		genCls.openPage("«·⁄„·Ì«  «·»—ÌœÌ…, ”ÃÌ· «·»⁄«∆À","”ÃÌ· »⁄ÌÀ…");
		if(regItemObj.checkSenderStsatus()) {
			regItemObj.setSender("Ê“«—… «·⁄„·", "œÌÊ«‰ Ê“«—… «·⁄„·");
		}
		try {
			regItemObj.setItemDetails(true, "ÊÀ«∆ﬁ", "100", "Islam",540,10);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String statementNo = genCls.createStatement("456321",false,true);
		
	}

}
