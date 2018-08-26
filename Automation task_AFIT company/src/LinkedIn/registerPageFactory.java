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

public class registerPageFactory {
	
	JavascriptExecutor js ;
	
	Select dropdown;
	
	@FindBy(id="reg-firstname")
	private WebElement firstNameTextBox;
	
	@FindBy(id="reg-lastname")
	private WebElement lastNameTextBox;
	
	@FindBy(id="reg-email")
	private WebElement emailTextBox;
	
	@FindBy(id="reg-password")
	private WebElement passwordTextBox;
	
	@FindBy(id="registration-submit")
	private WebElement joinButton;
	
	@FindBy(id="location-country")
	private WebElement countryComboBox;
	
	public  registerPageFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;

	}
	
	public void registerUser(String firstName, String lastName, String eMail, String password, String country, String zipCode) {
		
		firstNameTextBox.sendKeys(firstName);
		lastNameTextBox.sendKeys(lastName);
		emailTextBox.sendKeys(eMail);
		passwordTextBox.sendKeys(password);
		joinButton.click();
		
		// Waiting for the page to submit
		
		// This loop will rotate for 30 times to check If page Is ready after every 1 second.
        // You can replace your value with 30 If you wants to Increase or decrease wait time.
        for (int i = 0; i < 30; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            // To check page ready state.
            if (js.executeScript("return document.readyState").toString().equals("complete")) {
                break;
            }
        }
        
        // The following block of code is used if I Want to change the country
        dropdown.selectByVisibleText("Egypt");
	}
}
