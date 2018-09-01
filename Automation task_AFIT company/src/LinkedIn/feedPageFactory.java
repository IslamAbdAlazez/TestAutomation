/**
 * 
 */
package LinkedIn;

/**
 * @author Islam Abd Alazez Abd Alhamed
 *
 */
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class feedPageFactory {
	
	JavascriptExecutor js ;
	generalClass glc = new generalClass();	
	WebDriver browserDriver;
	
	@FindBy(id="nav-settings__dropdown-trigger")
	private WebElement settingButton;
	
	@FindBy(xpath="/html/body/nav/div/ul[1]/li[6]/div/ul/li[1]/a/div[2]/span")
	private WebElement viewProfileLink;
	
	
	public feedPageFactory(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
		browserDriver = driver;
	}
	
	public void openProfile() {
		
		settingButton.click();
		viewProfileLink.click();
		glc.waitForPageLoad(10, browserDriver);
	}
}
