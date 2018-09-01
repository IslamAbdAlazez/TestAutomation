/**
 * 
 */
package LinkedIn;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class profileEditPageFactory {
	            
    @FindBy(xpath = "/html/body/div[5]/div[6]/div[3]/div/div/div/div/div[2]/div[1]/section/div/div/div[1]")
    private WebElement profileStrength;
	
	@FindBy(xpath = "/html/body/div[5]/div[6]/div[3]/div/div/div/div/div[2]/div[1]/div[2]/section/div[3]/div[1]/div[2]/section/button")
	private WebElement addProfileSectionBtn;
	
	@FindBy(id = "intro-drawer")
	private WebElement introHeader;
	
	@FindBy(id = "background-drawer")
	private WebElement backGroundHeader;
	
	@FindBy(id = "skills-drawer")
	private WebElement skillsHeader;
	
	@FindBy(xpath = "/html/body/div[5]/div[6]/div[3]/div/div/div/div/div[2]/div[1]/div[2]/section/div[3]/div[1]/div[2]/section/ul/section[1]/ul/li[1]/div[1]/span[2]")
	private WebElement profilePhotoAddButton;
	
	@FindBy(xpath = "/html/body/div[5]/div[6]/div[3]/div/div/div/div/div[2]/div[1]/div[2]/section/div[3]/div[1]/div[2]/section/ul/section[1]/ul/li[2]/a/div/span[2]")
	private WebElement summaryAddButton;

	@FindBy(xpath = "/html/body/div[5]/div[6]/div[3]/div/div/div/div/div[2]/div[1]/div[2]/section/div[3]/div[1]/div[2]/section/ul/section[3]/ul/li/a/div/span[2]")
	private WebElement skillsAddButton;
	
	@FindBy(id = "topcard-summary")
	private WebElement summaryTextBox;
	
	@FindBy(xpath = "/html/body/div[5]/div[6]/div/div[1]/div/div/div[1]/div[2]/div[1]/div/form/div/footer/div/button")
	private WebElement saveSummaryButton;
	
	@FindBy(xpath = "/html/body/div[5]/div[6]/div[3]/div/div/div/div/div[2]/div[1]/div[2]/section/div[4]/p/span[1]")
	public WebElement summaryDiv;
	
	@FindBy(xpath = "/html/body/div[5]/div[6]/div[3]/div/div/div/div/div[2]/div[1]/div[2]/section/div[3]/div[1]/div[2]/section/ul/section[2]/ul/li[1]/a/div/span[2]")
	private WebElement workExperienceAddButton;
	
	@FindBy(xpath = "/html/body/div[5]/div[6]/div[3]/div/div/div/div/div[2]/div[1]/div[2]/section/div[3]/div[1]/div[2]/section/ul/section[2]/ul/li[2]/a/div/span[2]")
	private WebElement educationAddButton;
	
	@FindBy(xpath = "/html/body/div[5]/div[6]/div[3]/div/div/div/div/div[2]/div[1]/div[2]/section/div[3]/div[1]/div[2]/section/ul/section[2]/ul/li[3]/a/div/span[2]")
	private WebElement volunteerExperienceAddButton;
	
	@FindBy(id = "position-title-typeahead")
	private WebElement positionTitleTextBox;
	
	@FindBy(id = "position-company-typeahead")
	private WebElement positionCompanyTextBox;
	
	@FindBy(id= "position-location-typeahead")
	private WebElement positionLocationTextBox;
	
	@FindBy(id = "position-start-month")
	private WebElement positionStartMonthSelect;
	
	@FindBy(id = "position-start-year")
	private WebElement positionStartYearSelect;
	
	@FindBy(id = "position-headline")
	private WebElement positionHeadLineTextBox;
	
	@FindBy(id = "position-description")
	private WebElement positionDescriptionTextBox;
	
	@FindBy(xpath = "/html/body/div[5]/div[6]/div/div[1]/div/div/div[1]/div[2]/div[1]/div/form/div/footer/div[2]/button")
	private WebElement saveExperienceButton;
	
	@FindBy(id = "edu-school-typeahead")
	private WebElement schoolTextBox;
	
	@FindBy(id = "edu-degree-typeahead")
	private WebElement degreeTextBox;
	
	@FindBy(id = "edu-field-of-study-typeahead")
	private WebElement studyFieldTextBox;
	
	@FindBy(id = "edu-activities-societies")
	private WebElement studyActivitiesTextBox;
	
	@FindBy(id = "pe-education-form__start-year")
	private WebElement studyStartYearSelect;
	
	@FindBy(id = "pe-education-form__end-year")
	private WebElement studyEndYearSelect;
	
	@FindBy(id = "edu-description")
	private WebElement studyDescription;
	
	@FindBy(xpath = "/html/body/div[5]/div[6]/div/div[1]/div/div/div[1]/div[2]/div[1]/div/form/div/footer/div[2]/button")
	private WebElement saveStudyButton;
	
	@FindBy(name = "companyName")
	private WebElement organizationTextBox;
	
	@FindBy(name = "role")
	private WebElement roleTextBox;
	
	@FindBy(id = "volunteer-experience-cause")
	private WebElement causeSelect;
	
	@FindBy(id = "volunteer-experience-start-month")
	private WebElement volunteerStartMonthSelect;
	
	@FindBy(id = "volunteer-experience-start-year")
	private WebElement volunteerStartYearSelect;
	
	@FindBy(id = "volunteer-experience-end-month")
	private WebElement volunteerEndMonthSelect;
	
	@FindBy(id = "volunteer-experience-end-year")
	private WebElement volunteerEndYearSelect;
		
	@FindBy(id = "volunteer-experience-currently-volunteers-here")
	private WebElement currentlyVolunteerCheckBox;
	
	@FindBy(id = "volunteer-experience-description")
	private WebElement volunteerDescriptionTextBox;
	
	@FindBy(xpath = "/html/body/div[5]/div[6]/div/div[1]/div/div/form/div/footer/div[2]/button")
	private WebElement saveVolunteerButton;
	
	@FindBy(xpath = "/html/body/div[1]/artdeco-modal-overlay/artdeco-modal/artdeco-modal-content/div/ul")
	private WebElement skillsArea;
	//
	@FindAll({@FindBy(xpath = "//*[contains(@class, '" + "button-primary-medium ml2 fr ember-view" + "')]")})
	private List<WebElement> saveSkillsButton;
	
	
	@FindBy (xpath = "/html/body/div[5]/div[6]/div[3]/div/div/div/div/div[2]/div[1]/div[5]/div[2]/span/section/div[1]/section/ul/li[2]/div/li/a/div/div[2]/h3/span[2]")
	public WebElement companyNameSpan;
	
	public  profileEditPageFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);			
	}
	
	public void addIntroProfileSection(WebDriver driver) throws IOException {
		
		Actions actions = new Actions(driver);
		actions.moveToElement(addProfileSectionBtn);
		actions.perform();
		addProfileSectionBtn.click();
		introHeader.click();
		profilePhotoAddButton.click();
		
		// Run AutoIt code
        // The EXE For upload file is included in the soloution but must be copied to "C:\" Partition on the test PC
        // The Image used for the uploading is included in the solution "Islam.jpg" But must copied to "C:\" partition in the test PC

        Process process = Runtime.getRuntime().exec("C:\\FileUpload.exe");        
        try {
			process.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if (summaryDiv !=null && !summaryDiv.getAttribute("innerText").contains("This is a demo summary text") ) {
        	summaryAddButton.click();
        	summaryTextBox.sendKeys("This is a demo summary text");
            saveSummaryButton.click();
		}   
	}
	
	public void addBackgroundProfileSection(WebDriver driver) {
		
		Actions actions = new Actions(driver);
		actions.moveToElement(profileStrength);
		actions.perform();
		//addProfileSectionBtn.click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement background_header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("background-drawer")));
		background_header.click();
		workExperienceAddButton.click();
		positionTitleTextBox.sendKeys("This is a demo position title");
		positionCompanyTextBox.sendKeys("This is a demo position company");
		positionLocationTextBox.sendKeys("Egypt");
		Select startMonthSelect = new Select(positionStartMonthSelect);
		startMonthSelect.selectByVisibleText("January");
		Select startYearSelect = new Select(positionStartYearSelect);
		startYearSelect.selectByVisibleText("2014");
		positionHeadLineTextBox.sendKeys("This is a demo headline");
		positionDescriptionTextBox.sendKeys("This is a demo position description");
		saveExperienceButton.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions actions2 = new Actions(driver);
		actions2.moveToElement(addProfileSectionBtn);
		actions2.perform();
		addProfileSectionBtn.click();
		educationAddButton.click();
		schoolTextBox.sendKeys("This is a demo school");
		degreeTextBox.sendKeys("Bachelor's degree");
		studyFieldTextBox.sendKeys("Commerece");
		studyActivitiesTextBox.sendKeys("This is a demo study activities");
		Select studyStartYear = new Select(studyStartYearSelect);
		studyStartYear.selectByVisibleText("2000");
		Select studyEndtYearSelect = new Select(studyEndYearSelect);
		studyEndtYearSelect.selectByVisibleText("2004");
		studyDescription.sendKeys("This is a demo study description");
		saveStudyButton.click();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions actions3 = new Actions(driver);
		actions3.moveToElement(addProfileSectionBtn);
		actions3.perform();
		addProfileSectionBtn.click();
		volunteerExperienceAddButton.click();
		organizationTextBox.sendKeys("This is a demo organization");
		roleTextBox.sendKeys("This is a demo role");
		Select volunteerCause = new Select(causeSelect);
		volunteerCause.selectByVisibleText("Children");
		Select volunteerStartMonth = new Select(volunteerStartMonthSelect);
		volunteerStartMonth.selectByVisibleText("January");
		Select volunteerStartYear = new Select(volunteerStartYearSelect);
		volunteerStartYear.selectByVisibleText("2000");
		Select volunteerEndMonth = new Select(volunteerEndMonthSelect);
		volunteerEndMonth.selectByVisibleText("January");
		Select volunteerEndYear = new Select(volunteerEndYearSelect);
		volunteerEndYear.selectByVisibleText("2003");
		volunteerDescriptionTextBox.sendKeys("This is a demo volunteer description");
		saveVolunteerButton.click();		
	}	
public void addSkillsProfileSection(WebDriver driver) {
	
	Actions actions = new Actions(driver);
	actions.moveToElement(addProfileSectionBtn);
	actions.perform();
		addProfileSectionBtn.click();
		skillsHeader.click();		
		skillsAddButton.click();
		List<WebElement> options = skillsArea.findElements(By.tagName("li"));
		for (WebElement option : options)
		{
		    if (option.getText().toLowerCase().contains("a")) // For selecting the first item that contains a letter
		    {
		        option.click(); // click the desired option		        
		        break;
		    }
		}

		for(WebElement element : saveSkillsButton){
	        if(element.getAttribute("innerHTML").contains("Add")){
	            element.click();
	            break;
	        }
	    }
	try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	public void scrollPage(WebDriver driver, int numberOfPixelsToDragTheScrollbarDown) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + (String) Integer.toString( numberOfPixelsToDragTheScrollbarDown) + ")");		
	}
}
