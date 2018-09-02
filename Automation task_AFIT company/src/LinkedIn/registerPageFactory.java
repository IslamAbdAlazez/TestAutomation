/**
 * 
 */
package LinkedIn;

/**
 * @author Islam Abd Alazez Abd Alhamed
 *
 */
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class registerPageFactory {
	
	JavascriptExecutor js ;
	Select countryDropdown;
	Select workIndustryComboBox;
	
	WebDriver browserDriver;
	
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
	
	@FindBy(xpath = "//*[@id=\"regForm\"]/div/div/p/span")
	public WebElement passwordValidation;
		
	@FindBy(id = "call")
	private WebElement callWindow;
	
	@FindBy(id = "nocaptcha")
	private WebElement captcha;
	
	@FindBy(id="location-country")
	private WebElement countryComboBox;
	
	@FindBy(id="work-industry")
	private WebElement workIndustry;
	
	@FindBy(id="location-postal")
	private WebElement postalCodeTextBox;
	
	@FindBy(id="typeahead-input-for-title")
	private WebElement jobTitleTextBox;
	
	@FindBy(id="typeahead-input-for-company")
	private WebElement companyTextBox;
	
	@FindBy(xpath = "//*[contains(@class, 'onboarding-widget__cta button-primary-x-large full-width ember-view')]")
	private WebElement continuetBtn;
	
	@FindBy(xpath = "//*[contains(@class, 'onboarding-widget__cta button-primary-x-large full-width ember-view')]")
	private WebElement nextBtn;

	public  registerPageFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
		browserDriver = driver;
		countryDropdown = new Select(countryComboBox);
		workIndustryComboBox = new Select(workIndustry);
	}
	
	public void successfulRegisteration(String firstName, String lastName, String eMail, String password, String countryName, String postalCode, String jobTitle, String companyName, String workIndustry) {
		
		firstNameTextBox.clear();
		firstNameTextBox.sendKeys(firstName);
		lastNameTextBox.clear();
		lastNameTextBox.sendKeys(lastName);
		emailTextBox.clear();
		emailTextBox.sendKeys(eMail);
		passwordTextBox.clear();
		passwordTextBox.sendKeys(password);
		joinButton.click();
		toString();
		
		waitForPageLoad(30);
		
		// Check if there is a CAPATCHA or security verification wait for 90 seconds to allow the user to submit it manually
        if((captcha !=null && captcha.isDisplayed()) || (callWindow != null && callWindow.isDisplayed())) 
        {
        	try {
				Thread.sleep(90000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
     // The following block of code is used if I Want to change the country
        countryDropdown.selectByVisibleText(countryName);
        postalCodeTextBox.sendKeys(postalCode);
        nextBtn.click();
        waitForPageLoad(30);
        
    	jobTitleTextBox.sendKeys(jobTitle);
		companyTextBox.sendKeys(companyName);
		workIndustryComboBox.sendKeys(workIndustry);
		continuetBtn.click();
		waitForPageLoad(30);
	}
	
	public void waitForPageLoad(int seconds) {
		// Waiting for the page to submit
		
				// This loop will rotate for 30 times to check If page Is ready after every 1 second.
		        // You can replace your value with 30 If you wants to Increase or decrease wait time.
		        for (int i = 0; i < seconds; i++) {
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
	
	// This method try to register without password 
public void notSuccessfulRegisteration(String firstName, String lastName, String eMail) {
		
		firstNameTextBox.sendKeys(firstName);
		lastNameTextBox.sendKeys(lastName);
		emailTextBox.sendKeys(eMail);
		joinButton.click();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
	}
}
