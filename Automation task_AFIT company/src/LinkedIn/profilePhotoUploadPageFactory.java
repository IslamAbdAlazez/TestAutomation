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

public class profilePhotoUploadPageFactory {
	
	JavascriptExecutor js ;
	
	@FindBy(className="onboarding-widget__cta onboarding-photo__skip-button button-tertiary-small-muted mt4 ember-view")
	private WebElement skipBtn;
		
	public  profilePhotoUploadPageFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;		
	}
	
	public void setProfileCountry(String confirmationCode) {
				
		skipBtn.click();		
	}
}
