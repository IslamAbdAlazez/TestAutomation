package saudiPost.mailOperations.registerItems;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class general {
	
	static WebDriver browserDriver ;
	
	public void openPage(String fullPathCommaDele, String pageName) 
	{
		String currPointerText;
		String[] fullPath = fullPathCommaDele.split(",");
		for (int i = 0; i < fullPath.length; i++) {
			currPointerText = fullPath[i].toString();
			WebElement currPointer = browserDriver.findElement(By.linkText(currPointerText));
			currPointer.click();
			browserDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			currPointerText="";
		}		
		
		WebElement pageLnk = browserDriver.findElement(By.partialLinkText(pageName));
		pageLnk.click();
		browserDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	public void invokeBrowser() 
	{		
		browserDriver.manage().deleteAllCookies();
		browserDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		browserDriver.get("http://212.100.202.154:5002");
		/*try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//browserDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);		
	}
	public void login(String uName, String pass) 
	{
		WebElement usrNameTxt = browserDriver.findElement(By.id("UserNamevalid"));
		usrNameTxt.sendKeys(uName);
		WebElement pwdTxt = browserDriver.findElement(By.id("Passwordvalid"));
		pwdTxt.sendKeys(pass);
		pwdTxt.sendKeys(Keys.ENTER);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//browserDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}	

	public static WebDriver main(String[] args) {
		// for Chrome
		/*System.setProperty("webdriver.chrome.driver", "C:\\Users\\islam.ARCOM\\Downloads\\Compressed\\chromedriver_win32\\chromedriver.exe");
		browserDriver= new ChromeDriver();
		browserDriver.manage().window().maximize();*/
		
		// for Firefox
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\islam.ARCOM\\Downloads\\Compressed\\geckodriver-v0.19.1-win64\\geckodriver.exe");
		browserDriver= new FirefoxDriver();
		
		return browserDriver;
	}
	

}
