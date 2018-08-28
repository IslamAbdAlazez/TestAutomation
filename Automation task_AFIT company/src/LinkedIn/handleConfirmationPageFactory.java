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

public class handleConfirmationPageFactory {
	
	JavascriptExecutor js ;
	
	@FindBy(className="onboarding-email-confirmation__code-input text-align-center   ember-text-field ember-view")
	private WebElement confirmationTextBox;
	
	@FindBy(className="onboarding-widget__cta onboarding-email-confirmation__verify onboarding-widget__cta--primary\r\n" + 
			"        button-primary-large\r\n" + 
			"        Sans-19px-white-100%-open\r\n" + 
			"        full-width\r\n" + 
			"        mv5 ember-view")
	private WebElement agreeBtn;
	
	@FindBy(id="typeahead-input-for-company")
	private WebDriver companyTextBox;
	
	
	
	public  handleConfirmationPageFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;		
	}
	
	public void setProfileCountry(String confirmationCode) {
		
		confirmationTextBox.sendKeys(confirmationCode);
		agreeBtn.click();
	}
}
