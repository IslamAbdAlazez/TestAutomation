/**
 * 
 */
package LinkedIn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class resetPasswordPageFactory {
	
	generalClass glc = new generalClass();
	WebDriver browserDriver;
	
	@FindBy(xpath = "//*[@id=\"newPassword\"]")
	private WebElement newPasswordTextBox;
	
	@FindBy(xpath = "//*[@id=\"confirmPassword\"]")
	private WebElement confirmPasswordTextBox;
	
	@FindBy(xpath = "//*[@id=\"reset-password-submit-button\"]")
	private WebElement submitButton; 
		
	public resetPasswordPageFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);
		browserDriver = driver;		
	}
	
	public void completeResetPassword(String password) {
		glc.waitForPageLoad(30, browserDriver);
		newPasswordTextBox.sendKeys(password);
		confirmPasswordTextBox.sendKeys(password);
		submitButton.click();		
	}	
}