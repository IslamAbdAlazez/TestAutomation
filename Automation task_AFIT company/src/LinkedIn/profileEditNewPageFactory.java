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

public class profileEditNewPageFactory {
	
	JavascriptExecutor js ;
	
	@FindBy(id="typeahead-input-for-title")
	private WebElement jobTitleTextBox;
	
	@FindBy(id="typeahead-input-for-company")
	private WebElement companyTextBox;
	
	@FindBy(className="onboarding-widget__cta button-primary-x-large full-width ember-view")
	private WebElement continuetBtn;
	
	public  profileEditNewPageFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;		
	}
	
	public void setProfileCountry(String jobTitle, String companyName) {
		
		jobTitleTextBox.sendKeys(jobTitle);
		companyTextBox.sendKeys(companyName);
		continuetBtn.click();
	}
}
