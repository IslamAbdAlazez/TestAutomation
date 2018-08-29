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

public class logInPageFactory {
	
	JavascriptExecutor js ;
	generalClass glc;	
	WebDriver browserDriver;
	
	@FindBy(id="login-email")
	private WebElement emailTextBox;
	
	@FindBy(id="login-password")
	private WebElement passwordTextBox;
	
	@FindBy(id="login-submit")
	private WebElement signInButton;
	
	@FindBy(className = "link-forgot-password")
	private WebElement forgotPasswordBtn; 
	
	public  logInPageFactory(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
		browserDriver = driver;
	}
	
	public void signInUser(String eMail, String password) {
		
		// Waiting for the page to submit
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		emailTextBox.sendKeys(eMail);
		passwordTextBox.sendKeys(password);
		signInButton.click();
	}
	
	private void forgotPassword() {
		forgotPasswordBtn.click();
		glc.waitForPageLoad(30);
	}
}
