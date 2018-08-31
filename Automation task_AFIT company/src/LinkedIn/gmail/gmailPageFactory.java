package LinkedIn.gmail;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import LinkedIn.generalClass;


public class gmailPageFactory {

	WebDriver browserDriver;
	
	@FindBy(xpath="//*[@class='zF']")
	private WebElement mailsTable;
	
	@FindBy(className="hP")
	private WebElement subjectElement;
	
	@FindBy(xpath="/html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div/div[2]/div/div[1]/div/div[2]/div/table/tr/td[1]/div[2]/div[2]/div/div[3]/div/div/div/div/div/div[1]/div[2]/div[3]/div[3]/div/div[1]")
	private WebElement bodyElement;
	
	@FindBy(linkText="here")
	private WebElement resetLink;
	
	generalClass glc = new generalClass();
	
	public gmailPageFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);
		browserDriver = driver;		
}
	public void readMail() {
		
		// Get unread mails
		
		List<WebElement> a = browserDriver.findElements(By.xpath("//*[@class='yW']/span"));
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getText().equals("LinkedIn")) //to click on a specific mail.
                {                                           
                a.get(i).click();
                break;
            }
        }
        
       try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        // Click on the reset link
        resetLink.click();
        }
}