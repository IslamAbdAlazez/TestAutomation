/**
 * 
 */
package LinkedIn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class forgotPasswordPageFactory {
	
	generalClass glc = new generalClass();
	WebDriver browserDriver;
	
	@FindBy(id="username")
	private WebElement emailTextBox;
	
	@FindBy(id="reset-password-submit-button")
	private WebElement findAccountButton;
	
	@FindBy(className = "form__cancel")
	private WebElement cancelBtn; 
	
	@FindBy(xpath = "//*[@id=\"app__container\"]/div[1]/div/div[1]/span")
	private WebElement notFoundEMailValidation;
	
	public  forgotPasswordPageFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);
		browserDriver = driver;		
	}
	
	public void resetPassword(String eMail) {
		glc.waitForPageLoad(30, browserDriver);
		emailTextBox.sendKeys(eMail);
		findAccountButton.click();		
	}

	public boolean validateEMailAddress() {
		if (notFoundEMailValidation.isDisplayed()) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
}
