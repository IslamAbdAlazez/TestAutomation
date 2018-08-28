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

public class pepoleYouMayKnowPageFactory {
	
	JavascriptExecutor js ;
	
	@FindBy(className="onboarding-widget__cta onboarding-widget__cta--tertiary onboarding-combo-bar__skip button-tertiary-small-muted mr4 flex-shrink-zero ember-view")
	private WebElement skipBtn;
		
	public  pepoleYouMayKnowPageFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;		
	}
	
	public void setProfileCountry(String confirmationCode) {
				
		skipBtn.click();		
	}
}
