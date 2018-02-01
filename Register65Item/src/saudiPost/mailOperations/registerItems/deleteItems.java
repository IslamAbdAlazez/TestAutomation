package saudiPost.mailOperations.registerItems;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import saudiPost.mailOperations.registerItems.general;


public class deleteItems {

	static WebDriver browserDriver ;
	general genCls = new general();
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
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (browserDriver.findElements(By.className("dataTables_empty")).size() != 0) {
					break;
				}
			}
			
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		general genCls = new general();
		deleteItems delItems = new deleteItems();
		String[] browserName = new String[1];
		browserName[0]="firefox";
		browserDriver= genCls.main(browserName);
		genCls.invokeBrowser();
		genCls.login("mosimi","P@ssw0rd");
		genCls.openPage("ÇáÚãáíÇÊ ÇáÈÑíÏíÉ,ÊÓÌíá ÇáÈÚÇÆË","ÓÌíá ÈÚíËÉ");
		delItems.deleteItems(65);		
	}

}
