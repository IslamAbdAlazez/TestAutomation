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
		browserDriver.get("https://www.linkedin.com");
		// Waiting for the page to submit
		waitForPageLoad(30);
		emailTextBox.sendKeys(eMail);
		passwordTextBox.sendKeys(password);
		signInButton.click();
	}
	
	private void waitForPageLoad (int timeInSeconds) {
		
		// This loop will rotate for the number passed in the timeInSeconds input parameter to check If page Is ready after every 1 second.
            for (int i = 0; i < timeInSeconds; i++) {
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
