package salescloud.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import salescloud.abstractcomponents.AbstactComponents;

public class LeadHistoryPage extends AbstactComponents  {

	WebDriver driver;
	public LeadHistoryPage leadHistoryPage;

	public LeadHistoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//lst-breadcrumbs/following-sibling::div/h1")
	WebElement leadHistoryHeaderText;
	@FindBy(xpath = "(//table[@aria-label='Lead History']/tbody/tr/td[@data-label='Original Value']//lightning-formatted-text)[1]")
	WebElement originalLeadStatusValue;
	@FindBy(xpath = "(//table[@aria-label='Lead History']/tbody/tr/td[@data-label='New Value']//lightning-formatted-text)[1]")
	WebElement newLeadStatusValue;
	
	public String getPageHeaderText() throws InterruptedException {
		wait5Seconds();
		return leadHistoryHeaderText.getText();
	}
	
	public String getOriginalValue() {
		return originalLeadStatusValue.getText();
	}
	
	public String getNewValue() {
		return newLeadStatusValue.getText();
	}
	
	
}
