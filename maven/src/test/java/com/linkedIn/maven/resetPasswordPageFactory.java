/**
 * 
 */
package com.linkedIn.maven;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class resetPasswordPageFactory {

	generalClass glc = new generalClass();
	WebDriver browserDriver;

	@FindBy(xpath = "//*[@id=\"newPassword\"]")
	private WebElement newPasswordTextBox;

	@FindBy(xpath = "//*[@id=\"confirmPassword\"]")
	private WebElement confirmPasswordTextBox;

	@FindBy(xpath = "//*[@id=\"reset-password-submit-button\"]")
	private WebElement submitButton;

	public resetPasswordPageFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);
		browserDriver = driver;
	}

	public void completeResetPassword(String password) {
		ArrayList<String> tabs = new ArrayList<String>(browserDriver.getWindowHandles());
		browserDriver.switchTo().window(tabs.get(2));
		glc.waitForPageLoad(30, browserDriver);
		newPasswordTextBox.sendKeys(password);
		confirmPasswordTextBox.sendKeys(password);
		submitButton.click();
	}

	public boolean confirmPasswordChanged() {
		if (browserDriver.getTitle().contains("You've successfully reset your password. | LinkedIn")) {
			return true;
		} else {
			return false;
		}
	}
}