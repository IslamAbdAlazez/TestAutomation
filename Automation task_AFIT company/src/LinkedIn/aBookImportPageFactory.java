/**
 * 
 */
package LinkedIn;

import org.openqa.selenium.JavascriptExecutor;
/**
 * @author Islam Abd Alazez Abd Alhamed
 *
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class aBookImportPageFactory {
	
	JavascriptExecutor js ;
	
	@FindBy(className="onboarding-widget__cta onboarding-widget__cta-skip button-tertiary-small-muted mt4 ember-view")
	private WebElement skipBtn;
	
	@FindBy(name="skip-2nd-click")
	private WebElement popUpSkipBtn;
	
	public  aBookImportPageFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;		
	}
	
	public void setProfileCountry(String confirmationCode) {
				
		skipBtn.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		popUpSkipBtn.click();
	}
}
