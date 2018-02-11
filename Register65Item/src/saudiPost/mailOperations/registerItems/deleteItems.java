package saudiPost.mailOperations.registerItems;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import saudiPost.mailOperations.registerItems.general;

public class deleteItems {

	static WebDriver browserDriver ;
	static general genCls = new general();
	
	public void deleteItems(int itemsCount, boolean pageSource, boolean delPermOrFromStat) {
		
		/*Select itemsLengthMenu = new Select(browserDriver.findElement(By.id("itemsLengthMenu")));
		itemsLengthMenu.selectByVisibleText(Integer.toString(65));*/
		
			for (int i = 0; i < itemsCount; i++) {
				WebElement currDelBtn= genCls.bringTableRow("ItemsDataTable", "").findElement(By.cssSelector("button[data-original-title='ÍÐÝ']"));
				currDelBtn.click();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (pageSource) {					
					//browserDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					WebElement okBtn = browserDriver.findElement(By.cssSelector(".ok"));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					okBtn.click();
				}
				else {
					
					if (delPermOrFromStat) {
						browserDriver.findElement(By.id("deleted")).click();						
					}
					else {
						browserDriver.findElement(By.id("deleteFromStatement")).click();						
					}					
				}
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
				if (browserDriver.findElements(By.className("dataTables_empty")).size() != 0) {
					break;
				}
			}			
	}
	
public void deleteSomeItems(String[] itemsNos , boolean [] delete, boolean pageSource) {
				WebElement currentRow ;
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {					
					e.printStackTrace();
				}
		for (int i = 0; i < itemsNos.length; i++) {
			currentRow = genCls.bringTableRow("ItemsDataTable", itemsNos[i].toString());
			if (currentRow !=null) {				
				currentRow.findElement(By.cssSelector("button[data-original-title='ÍÐÝ']")).click();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (pageSource) {
					WebElement okBtn = browserDriver.findElement(By.cssSelector(".ok"));
					okBtn.click();
					currentRow = null;
				}
				else {
					///// 	HERE I WILL BUT THE CODE THAT WILL CLICK ON DELETE PERMENTALLY OR DELETE FROM STATEMENT BUTTON
					if (delete[i]) {
						browserDriver.findElement(By.id("deleted")).click();
						currentRow = null;
					}
					else {
						browserDriver.findElement(By.id("deleteFromStatement")).click();
						currentRow = null;
					}					
				}
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		genCls.openPage("ÇáÚãáíÇÊ ÇáÈÑíÏíÉ,ÊÓÌíá ÇáÈÚÇÆË","Êßæíä ÈíÇä");
		
		// For deleting all items or some items from the beginning
		delItems.deleteItems(65,false,false);			
		//for deleting some specific items
		//String[] itemsNos = new String[2]; /*You can modify this number to be the count of specific items You want to delete but make sure it is = the addition in the next line*/
		//itemsNos[0] = "O1985010007SA"; itemsNos[1] = "O1985010003SA";
		//boolean [] delete = new boolean[3]; // This array is used to identify that each of the items will be deleted perementaly or only deleted from the statement
		//delete[0] = true; delete[1] = false; delete[2] = true;
		//delItems.deleteSomeItems(itemsNos,delete,true /*True mean this page is invoked from register item page while false means this page is invoked from the create statement page*/);
	}
}
