package LinkedIn.gmail;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.linkedIn.maven.generalClass;

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
	
	public void gmailLogin(int excelRowIndex) throws IOException {
		String [][] userData;
		userData = glc.readDataFromExcel(excelRowIndex);
		emailTextBox.sendKeys(userData[0][2]);
		userNameNextBtn.click();
		passwordTextBox.sendKeys(userData[0][3]);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		passwordNextBtn.click();
	}	
}
