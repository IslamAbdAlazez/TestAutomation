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

public class profileLocationPageFactory {
	
	JavascriptExecutor js ;
	
	Select dropdown;
	
	@FindBy(id="location-country")
	private WebElement countryComboBox;
	
	@FindBy(id="location-postal")
	private WebElement postalCodeTextBox;
	
	@FindBy(className="onboarding-widget__cta button-primary-x-large full-width ember-view")
	private WebElement nextBtn;
	
	public  profileLocationPageFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
		dropdown = new Select(countryComboBox);
	}
	
	public void setProfileCountry(String countryName, String postalCode) {
		
        // The following block of code is used if I Want to change the country
        dropdown.selectByVisibleText(countryName);
        postalCodeTextBox.sendKeys(postalCode);
        nextBtn.click();
	}
}
