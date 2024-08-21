package salescloud.pageobjects;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import salescloud.abstractcomponents.AbstactComponents;

public class LoginPageInternal extends AbstactComponents {
	WebDriver driver;
	public LoginPageInternal loginPageInternal;

	public LoginPageInternal(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='username']")
	WebElement userName;
	@FindBy(xpath = "//input[@id='password']")
	WebElement password;
	@FindBy(xpath = "//div[contains(@class,'remember')]/label")
	WebElement rememberMeCheckbox;
	@FindBy(xpath = "//input[@id='Login']")
	WebElement loginButton;
	@FindBy(xpath = "//button[@title='App Launcher']")
	WebElement appLauncherButton;
	@FindBy(xpath = "//input[@placeholder='Search apps and items...']")
	WebElement searchForApps;
	@FindBy(xpath = "//a[@data-label='Sales']//lightning-formatted-rich-text/span")
	WebElement salesApp;
	@FindBy(xpath = "//one-app-nav-bar-item-root/a/span[text()='Opportunities']/parent::*/parent::*")
	WebElement opportunityObjectTab;

	public void goTo() {
		driver.get("https://hcg-sf-salesengagement-dev-ed.my.salesforce.com/");

	}

	public LeadPageInternal loginWithCredentials(HashMap<String, String> input) {
		userName.sendKeys(input.get("userName"));
		password.sendKeys(input.get("password"));
		rememberMeCheckbox.click();
		loginButton.click();
		return new LeadPageInternal(driver);
	}

	public String getLoggedInPageTitle() throws InterruptedException {
		wait5Seconds();
		return driver.getTitle();
	}
	
	public LoginPageInternal internalAppLogin(HashMap<String, String> input) {
		userName.sendKeys(input.get("userName"));
		password.sendKeys(input.get("password"));
		rememberMeCheckbox.click();
		loginButton.click();
		return new LoginPageInternal(driver);
	}
	
	public void goToSalesConsoleApp() {
		waitForWebElementToApper(appLauncherButton);
		appLauncherButton.click();
		waitForWebElementToApper(searchForApps);
		searchForApps.sendKeys("Sales");
		waitForWebElementToApper(salesApp);
		salesApp.click();
	}
	
	public OpportunityPageInternal goToOpportunityTab() throws InterruptedException {

		waitForWebElementToApper(opportunityObjectTab);
		wait5Seconds();
		opportunityObjectTab.click();
		return new OpportunityPageInternal(driver);
	}
	
}
