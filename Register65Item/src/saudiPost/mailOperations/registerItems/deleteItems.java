package saudiPost.mailOperations.registerItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import saudiPost.mailOperations.registerItems.general;

public class deleteItems {

	static WebDriver browserDriver ;
	static general genCls = new general();
	public void deleteItems(int itemsCount) {
		//WebElement mainCrp = browserDriver.findElement(By.xpath("//*[@id=\"RenderBody\"]/div[1]/div[2]/div/div[1]/span/span[1]/span")).findElement(By.id("select2-mainCorporateCustomers-container"));
		// Check if sender can be selected
		//String title = mainCrp.getAttribute("title");
		
		Select itemsLengthMenu = new Select(browserDriver.findElement(By.id("itemsLengthMenu")));
		itemsLengthMenu.selectByVisibleText(Integer.toString(65));
		// in case of delete some items		
		
			for (int i = 0; i < itemsCount; i++) {
				//delBtnXPath.add("/html/body/div[6]/div[2]/div[3]/div/div/div/div/div[2]/div/table/tbody/tr[1]/td[5]/button[2]");
				WebElement currDelBtn = browserDriver.findElement(By.xpath("/html/body/div[6]/div[2]/div[3]/div/div/div/div/div[2]/div/table/tbody/tr[1]/td[5]/button[2]"));
				currDelBtn.click();
				//browserDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				WebElement okBtn = browserDriver.findElement(By.cssSelector(".ok"));
				okBtn.click();
				//browserDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			/*	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				if (browserDriver.findElements(By.className("dataTables_empty")).size() != 0) {
					break;
				}
			}			
	}	
	
	public void deleteItem(String itemNo) {
		
		//WebElement ItemsDataTableBody = browserDriver.findElement(By.id("ItemsDataTable")).findElement(By.cssSelector("#ItemsDataTable > tbody:nth-child(3)"));
		/*WebDriverWait uiWait = new WebDriverWait(browserDriver, 15);
		uiWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.id("ItemsDataTable"), By.cssSelector("button[data-original-title='ÍÐÝ']")));*/
		WebElement currentRow ;
		currentRow = genCls.bringTableRow("ItemsDataTable", itemNo);
		if (currentRow !=null) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
			WebElement curDelBtn = currentRow.findElement(By.cssSelector("button[data-original-title='ÍÐÝ']")); 
			curDelBtn.click();
			WebElement okBtn = browserDriver.findElement(By.cssSelector(".ok"));
			okBtn.click();
		}		
	}
	
public void deleteSomeItems(String[] itemsNos) {
				WebElement currentRow ;
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
		for (int i = 0; i < itemsNos.length; i++) {
			currentRow = genCls.bringTableRow("ItemsDataTable", itemsNos[i].toString());
			if (currentRow !=null) {
				
				WebElement curDelBtn = currentRow.findElement(By.cssSelector("button[data-original-title='ÍÐÝ']")); 
				curDelBtn.click();
				WebElement okBtn = browserDriver.findElement(By.cssSelector(".ok"));
				okBtn.click();
				currentRow = null;
			}		
		}
	
	}
	
	public static void main(String[] args) {
				
		deleteItems delItems = new deleteItems();
		String[] browserName = new String[1];
		browserName[0]="firefox";
		browserDriver= genCls.main(browserName);
		genCls.invokeBrowser();
		genCls.login("mosimi","P@ssw0rd");
		genCls.openPage("ÇáÚãáíÇÊ ÇáÈÑíÏíÉ,ÊÓÌíá ÇáÈÚÇÆË","ÓÌíá ÈÚíËÉ");
		
		// For deleting all items or some items from the beginning
		//delItems.deleteItems(65);
		
		//for deleting 1 Specific item 
		//delItems.deleteItem("O1983010549SA");
		
		//for deleting some specific items
		String[] itemsNos = new String[2]; /*You can modify this number to be the count of specific items You want to delete but make sure it is = the addition in the next line*/
		itemsNos[0] = "O1983010547SA"; itemsNos[1] = "O1983010543SA";
		delItems.deleteSomeItems(itemsNos);
	}

}
