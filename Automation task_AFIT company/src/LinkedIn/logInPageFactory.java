/**
 * 
 */
package LinkedIn;

import org.openqa.selenium.By;
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

public class logInPageFactory {
	
	JavascriptExecutor js ;
		
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
		
		emailTextBox.sendKeys(eMail);
		passwordTextBox.sendKeys(password);
		signInButton.click();
		
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
	}
}
