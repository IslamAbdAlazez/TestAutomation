package LinkedIn.gmail;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import com.linkedIn.maven.generalClass;

public class gmailLogin {
	
	WebDriver browserDriver ;
	gmailLogInPageFactory glpf;
	generalClass glc = new generalClass();
	  
	public gmailLogin(WebDriver driver) {
		browserDriver = driver;
		glpf = new gmailLogInPageFactory(browserDriver);
	}
	
  public void logIn(int excelRowIndex) {
	  try {
		glpf.gmailLogin(excelRowIndex);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	  
  }
}
