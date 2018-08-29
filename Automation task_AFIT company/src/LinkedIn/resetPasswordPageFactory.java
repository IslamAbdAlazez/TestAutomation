/**
 * 
 */
package LinkedIn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class resetPasswordPageFactory {
	
	generalClass glc;
	WebDriver browserDriver;
	
	@FindBy(id="username")
	private WebElement emailTextBox;
	
	@FindBy(id="reset-password-submit-button")
	private WebElement findAccountButton;
	
	@FindBy(className = "form__cancel")
	private WebElement cancelBtn; 
	
	public  resetPasswordPageFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);
		browserDriver = driver;
	}
	
	public void resetPassword(String eMail) {
		emailTextBox.sendKeys(eMail);
		findAccountButton.click();
	}
	
}
