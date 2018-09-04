/**
 * 
 */
package LinkedIn;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import LinkedIn.gmail.gmailLogin;
import LinkedIn.gmail.gmailPageFactory;

public class registerPageFactory {
	
	gmailLogin gLogin;
	generalClass glc;
	
	JavascriptExecutor js ;
	Select countryDropdown;
	Select workIndustryDropDown;
	
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
	private WebElement workIndustryComboBox;
	
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

	@FindBy(xpath = "//*[contains(@class, 'onboarding-email-confirmation__code-input text-align-center   ember-text-field ember-view')]")
	private WebElement emailConfirmationTextBox;
	
	@FindBy(xpath = ".//button[contains(text(),'Agree & Confirm')]")
	private WebElement eMailAgreeAndContinueBtn;
	
	@FindBy(xpath = "//*[contains(@class, 'onboarding-widget__cta onboarding-widget__cta-skip button-tertiary-small-muted mt4 ember-view')]")
	private WebElement importContactsSkipBtn;
	
	@FindBy(xpath = "//*[contains(@class, 'onboarding-abi__hovercard-confirm button-secondary-medium mr1')]")
	private WebElement ImportContactsPopUpSkipBtn;	
	
	@FindBy(xpath = "//*[contains(@class, 'onboarding-widget__cta onboarding-widget__cta--tertiary onboarding-combo-bar__skip button-tertiary-small-muted mr4 flex-shrink-zero ember-view')]")
	private WebElement pepoleYouMayKnowSkipBtn;
	
	@FindBy(xpath = "//*[contains(@class, 'onboarding-widget__cta onboarding-photo__skip-button button-tertiary-small-muted mt4 ember-view1')]")
	private WebElement profilePhotoUploadSkipBtn;
	
	@FindBy(xpath = "//*[contains(@class, 'onboarding-widget__cta button-tertiary-small-muted mt4 ember-view')]")
	private WebElement getTheAppSkipBtn;
	
	public  registerPageFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
		browserDriver = driver;
		gLogin = new gmailLogin(browserDriver);
		glc = new generalClass();
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
		
		// If there is a CAPATCHA or security verification wait for 90 seconds to allow the user to submit it manually
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     // The following block of code is used if I Want to change the country
        countryDropdown = new Select(countryComboBox);
        countryDropdown.selectByVisibleText(countryName);
        postalCodeTextBox.sendKeys(postalCode);
        nextBtn.click();
        waitForPageLoad(30);
        
    	jobTitleTextBox.sendKeys(jobTitle);
    	companyTextBox.sendKeys(companyName+Keys.ENTER);
    	//companyTextBox.sendKeys(Keys.ENTER);
    	boolean workIndustryComboBoxExists = browserDriver.findElements( By.id("work-industry") ).size() != 0;
    	if (workIndustryComboBoxExists) {
    		workIndustryDropDown = new Select(workIndustryComboBox);
    		workIndustryDropDown.selectByVisibleText(workIndustry);
		}
    	/*try {
    		
		} catch (Exception e) {
			// TODO: handle exception
		}
    	finally {
    		
		}*/
    	continuetBtn.click();
		waitForPageLoad(30);
		
		// Get e-mail confirmation code from gmail and pass it to the page
		glc.openNewTab(browserDriver, "https://www.gmail.com");
		gLogin.logIn(5);
		glc.waitForPageLoad(30, browserDriver);
		gmailPageFactory gpf = new gmailPageFactory(browserDriver);
		String pin = gpf.getPinFromMailSubject();
		emailConfirmationTextBox.sendKeys(pin);
		eMailAgreeAndContinueBtn.click();
		waitForPageLoad(30);
		
		importContactsSkipBtn.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImportContactsPopUpSkipBtn.click();
		waitForPageLoad(30);
		
		pepoleYouMayKnowSkipBtn.click();
		waitForPageLoad(30);
		
		profilePhotoUploadSkipBtn.click();
		waitForPageLoad(30);
		
		getTheAppSkipBtn.click();
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
