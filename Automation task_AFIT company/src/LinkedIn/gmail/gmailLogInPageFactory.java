package LinkedIn.gmail;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import LinkedIn.generalClass;

public class gmailLogInPageFactory {

	WebDriver browserDriver;
		
	generalClass glc = new generalClass();
	
	@FindBy(id="identifierId")
	private WebElement emailTextBox;
	
	@FindBy(id="identifierNext")
	private WebElement userNameNextBtn;
	
	@FindBy(name="password")
	private WebElement passwordTextBox;
	
	@FindBy(id="passwordNext")
	private WebElement passwordNextBtn;
	
	public gmailLogInPageFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);
		browserDriver = driver;		
	}
	
	public void gmailLogin() throws IOException {
		emailTextBox.sendKeys(glc.readDataFromExcel(2)[2][2]);
		userNameNextBtn.click();
		passwordTextBox.sendKeys(glc.readDataFromExcel(2)[2][3]);
		passwordNextBtn.click();
	}
	
	}
