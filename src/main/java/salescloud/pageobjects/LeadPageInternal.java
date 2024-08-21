package salescloud.pageobjects;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.Keys;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import salescloud.abstractcomponents.AbstactComponents;

public class LeadPageInternal extends AbstactComponents {
	public String[] ALL_FIELDS_LABEL = { "Name", "Company", "Title", "Email", "Phone", "Lead Status", "Rating",
			"Lead Source" };
	WebDriver driver;
	public LeadPageInternal leadPageInternal;

	public LeadPageInternal(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@id='username']")
	WebElement userName;
	@FindBy(xpath = "//input[@id='password']")
	WebElement password;
	@FindBy(xpath = "//input[@id='Login']")
	WebElement loginButton;
	@FindBy(xpath = "//button[@title='App Launcher']")
	WebElement appLauncherButton;
	@FindBy(xpath = "//input[@placeholder='Search apps and items...']")
	WebElement searchForApps;
	@FindBy(xpath = "//a[@data-label='Sales']//lightning-formatted-rich-text/span")
	WebElement salesApp;
	@FindBy(xpath = "//one-app-nav-bar-item-root/a/span[text()='Leads']/parent::*/parent::*")
	WebElement leadObjectTab;
	@FindBy(xpath = "//button[@title='Select a List View: Leads']")
	WebElement openAllLeadListView;
	@FindBy(xpath = "//ul[@aria-label='Leads | List Views']/li[not(contains(@class, 'slds-hide'))]//a/span[@class=' virtualAutocompleteOptionText']")
	List<WebElement> allAvailableListView;
	@FindBy(xpath = "//h1/span[text()='Leads']/following-sibling::span")
	WebElement myLeadListViewText;

	@FindBy(xpath = "(//table/tbody/tr/th//a)[1]")
	WebElement firstRecordLink;
	@FindBy(xpath = "//record_flexipage-record-field//span[contains(@class,'test-id__field-label')]")
	List<WebElement> listOfAvailableFieldsLabel;
	@FindBy(xpath = "//button[@title='Edit Annual Revenue']")
	WebElement inLineEditName;
	@FindBy(xpath = "//input[@name='Company']")
	WebElement companyInput;
	@FindBy(xpath = "//div[@class='footer-full-width']//runtime_platform_actions-actions-ribbon/ul/li//button[@name='SaveEdit']")
	WebElement saveEditButton;
	@FindBy(xpath = "//span[text()='Company']/ancestor::record_flexipage-record-field//lightning-formatted-text")
	WebElement editedCompanyName;
	@FindBy(xpath = "//button[@name='Convert']")
	private WebElement leadConvertButton;
	@FindBy(xpath = "//h1[text()='Convert Lead ']")
	private WebElement convertPopUpHeading;
	@FindBy(xpath = "//span[text()='Account']")
	private WebElement convertLeadPopUpAccount;
	@FindBy(xpath = "//button[text()='Convert']/parent::span")
	private WebElement popUpLeadConvertButton;
	@FindBy(xpath = "//span[@title='Account']/ancestor::div[4]//button")
	private WebElement convertLeadPopUPAccountName;
	@FindBy(xpath = "//span[@title='Contact']/ancestor::div[4]//button")
	private WebElement convertLeadPopUPContactName;
	@FindBy(xpath = "//span[@title='Opportunity']/ancestor::div[4]//button")
	private WebElement convertLeadPopUPOpportunityName;
	@FindBy(xpath = "//h2[text()='Your lead has been converted']")
	private WebElement leadConvertMessage;
	@FindBy(xpath = "//h3[text()='Account']/parent::div/parent::div//a[contains(@class,'outputLookupLink ')]")
	private WebElement convertedLeadAccountName;
	@FindBy(xpath = "//h3[text()='Contact']/parent::div/parent::div//a[contains(@class,'outputLookupLink ')]")
	private WebElement convertedLeadContactName;
	@FindBy(xpath = "//h3[text()='Opportunity']/parent::div/parent::div//a[contains(@class,'outputLookupLink ')]")
	private WebElement convertedLeadOpportunityName;

	@FindBy(xpath = "//button[@aria-label='Industry']")
	WebElement industryFieldButton;
	@FindBy(xpath = "//div[@aria-label='Industry']/lightning-base-combobox-item/span[@class='slds-media__body']/span")
	List<WebElement> industryFieldDropdownValues;
	@FindBy(xpath = "//button[@aria-label='Category']")
	WebElement categoryFieldButton;
	@FindBy(xpath = "//div[@aria-label='Category']/lightning-base-combobox-item/span[@class='slds-media__body']/span")
	List<WebElement> categoryFieldDropdownValues;

	@FindBy(xpath = "//input[@name='Lead-search-input']")
	private WebElement leadSearchBox;
	@FindBy(xpath = "//span[@class='slds-assistive-text' and text()='Loading']")
	private WebElement spinner;

	@FindBy(xpath = "//button[@aria-label='Lead Status']/span")
	WebElement leadStatusValue;
	@FindBy(xpath = "//button[@aria-label='Lead Status']")
	WebElement leadStatusButton;
	@FindBy(xpath = "//div[@aria-label='Lead Status']/lightning-base-combobox-item/span[@class='slds-media__body']/span")
	List<WebElement> leadStatusDropdownValues;
	@FindBy(xpath = "//button[@aria-label='Lead Source']")
	WebElement leadSourceButton;
	@FindBy(xpath = "//div[@aria-label='Lead Source']/lightning-base-combobox-item/span[@class='slds-media__body']/span")
	List<WebElement> leadSourceDropdownValues;
	@FindBy(xpath = "//a[@data-label='Related']")
	WebElement relatedTabButton;
	@FindBy(xpath = "//h2/a[contains(@href,'related/Histories/view')]")
	WebElement leadHistoryTab;
	@FindBy(xpath = "//span[text()='Lead Age']/ancestor::flexipage-field//slot//lightning-formatted-number")
	WebElement leadAge;
	@FindBy(xpath = "//span[text()='Lead Aging Category']/ancestor::flexipage-field//slot//lightning-formatted-text")
	WebElement leadAgingCategory;

	public void goTo() {
		driver.get("https://hcg-sf-salesengagement-dev-ed.my.salesforce.com/");

	}

	public void goToSalesConsoleApp() {
		waitForWebElementToApper(appLauncherButton);
		appLauncherButton.click();
		waitForWebElementToApper(searchForApps);
		searchForApps.sendKeys("Sales");
		waitForWebElementToApper(salesApp);
		salesApp.click();

	}

	public void goToLeadTab() throws InterruptedException {
		waitForWebElementToApper(leadObjectTab);
		wait5Seconds();
		leadObjectTab.click();
	}

	public String openMyLeadListView(HashMap<String, String> input) {

		waitForWebElementToApper(openAllLeadListView);
		openAllLeadListView.click();
		waitForWebElementsToApper(allAvailableListView);
		allAvailableListView.stream().filter(tabs -> tabs.getText().equalsIgnoreCase(input.get("listViewName")))
				.findFirst().ifPresent(WebElement::click);
		return myLeadListViewText.getText();
	}

	public String goToRecordAndEdit(HashMap<String, String> input) throws InterruptedException {
		wait5Seconds();
		waitForWebElementToApper(firstRecordLink);
		wait5Seconds();
		firstRecordLink.click();
		zoomOut();
		List<String> convertedList = Arrays.asList(ALL_FIELDS_LABEL);
		boolean matchedListOfFields = listOfAvailableFieldsLabel.stream().map(WebElement::getText)
				.anyMatch(convertedList::contains);
		if(matchedListOfFields) {
			zoomIn();
			scrollByWidth();
			inLineEditName.click();
			String editCompanyName = input.get("editedCompanyValue");
			companyInput.clear();
			companyInput.sendKeys(editCompanyName);
			saveEditButton.click();
			wait5Seconds();
			return editedCompanyName.getText();
		} else {
			return "Not Found";
		}
		
	}

	public String goToAllOpenLeadsListView(HashMap<String, String> input) {
		waitForWebElementToApper(openAllLeadListView);
		waitForElementToClickable(openAllLeadListView);
		openAllLeadListView.click();
		waitForWebElementsToApper(allAvailableListView);
		implicitWait();
		allAvailableListView.stream()
				.filter(tabs -> tabs.getText().equalsIgnoreCase(input.get("AllOpenLeadslistViewName"))).findFirst()
				.ifPresent(WebElement -> {
					try {
						WebElement.click();
					} catch (TimeoutException e) {
						System.err.println("Failed to click the element: " + e.getMessage());
					}
				});
		return myLeadListViewText.getText();

	}

	public void clickOnlead() {
		waitForElementToDisappear(spinner);
		waitForPageToLoad();
		implicitWait();
		waitForWebElementToApper(firstRecordLink);
		waitForElementToClickable(firstRecordLink);
		try {
			firstRecordLink.click();
		} catch (StaleElementReferenceException e) {
			driver.navigate().refresh();
			waitForWebElementToApper(firstRecordLink);
			waitForElementToClickable(firstRecordLink);
			firstRecordLink.click();

		}

	}

	public String clickOnLeadConvertButton() {
		waitForPageToLoad();
		implicitWait();
		waitForWebElementToApper(leadConvertButton);
		leadConvertButton.click();
		implicitWait();
		waitForWebElementToApper(convertPopUpHeading);
		implicitWait();
		return convertPopUpHeading.getText();

	}

	public String getAccountNameOnConvertLeadPopUp() {
		waitForPageToLoad();
		implicitWait();
		waitForWebElementToApper(convertLeadPopUpAccount);
		waitForElementToClickable(convertLeadPopUpAccount);
		convertLeadPopUpAccount.click();
		implicitWait();
		waitForWebElementToApper(convertLeadPopUPAccountName);
		waitForElementToClickable(convertLeadPopUPAccountName);
		return convertLeadPopUPAccountName.getText();

	}

	public String getContactNameOnConvertLeadPopUp() {
		implicitWait();
		waitForWebElementToApper(convertLeadPopUPContactName);
		return convertLeadPopUPContactName.getText();

	}

	public String getOpportunityNameOnConvertLeadPopUp() {
		implicitWait();
		waitForWebElementToApper(convertLeadPopUPOpportunityName);
		return convertLeadPopUPOpportunityName.getText();

	}

	public void clickOnLeadConvertButtonOnPopUP() {
		waitForPageToLoad();
		implicitWait();
		waitForWebElementToApper(popUpLeadConvertButton);
		waitForElementToClickable(popUpLeadConvertButton);
		popUpLeadConvertButton.click();

	}

	public String getConvertedLeadSuccessMessage() {
		waitForPageToLoad();
		implicitWait();
		waitForWebElementToApper(leadConvertMessage);
		return leadConvertMessage.getText();

	}

	public String getConvertedLeadAccountName() {
		implicitWait();
		waitForWebElementToApper(convertedLeadAccountName);
		return convertedLeadAccountName.getText();

	}

	public String getConvertedLeadContactName() {
		implicitWait();
		waitForWebElementToApper(convertedLeadContactName);
		return convertedLeadContactName.getText();

	}

	public String getConvertedLeadOpportunityName() {
		implicitWait();
		waitForWebElementToApper(convertedLeadOpportunityName);
		return convertedLeadOpportunityName.getText();

	}

	public void startInLineEditForLeadRecord() throws InterruptedException {
		implicitWait();
		waitForWebElementToApper(firstRecordLink);
		wait5Seconds();
		firstRecordLink.click();
		wait5Seconds();
		scrollByWidth();
		inLineEditName.click();
		scrollByWidth();
		scrollByWidth();
	}

	public List<String> getCategoryValuesBasedOnIndustry(String industryName) throws InterruptedException {
		wait5Seconds();
		// Firstly select the None Option to start the dependency from start
		industryFieldButton.click();
		industryFieldDropdownValues.stream().filter(industry -> industry.getText().equalsIgnoreCase("--None--"))
				.limit(1).forEach(industry -> industry.click());

		industryFieldButton.click();
		industryFieldDropdownValues.stream().filter(industry -> industry.getText().equalsIgnoreCase(industryName))
				.limit(1).forEach(industry -> industry.click());
		wait5Seconds();
		categoryFieldButton.click();

		List<String> getCategoryNames = categoryFieldDropdownValues.stream().map(WebElement::getText)
				.collect(Collectors.toList());

		categoryFieldDropdownValues.stream().filter(category -> !category.getText().equalsIgnoreCase("--None--"))
				.limit(1).forEach(category -> category.click());

		return getCategoryNames;
	}

	public String getLeadStatusSelectedValue(boolean scrollUp) {
		if (scrollUp) {
			scrollUpByWidth();
		}
		return leadStatusValue.getText();
	}

	public List<String> getLeadStatusValuesFromUI() throws InterruptedException {
		wait5Seconds();
		String leadStatusOriginalValue = getLeadStatusSelectedValue(false);
		leadStatusButton.click();
		leadStatusDropdownValues.stream().filter(status -> status.getText().equalsIgnoreCase("--None--")).limit(1)
				.forEach(industry -> industry.click());

		leadStatusButton.click();
		List<String> getLeadStatusDropDownValuesFromUI = leadStatusDropdownValues.stream().map(WebElement::getText)
				.collect(Collectors.toList());
		leadStatusDropdownValues.stream()
				.filter(category -> !(category.getText().equalsIgnoreCase("--None--")
						|| category.getText().equalsIgnoreCase(leadStatusOriginalValue)))
				.limit(1).forEach(category -> category.click());
		return getLeadStatusDropDownValuesFromUI;
	}

	public List<String> getLeadSourceValuesFromUI() throws InterruptedException {
		wait5Seconds();
		scrollByWidth();
		scrollByWidth();
		leadSourceButton.click();
		List<String> getLeadSoureDropDownValuesFromUI = leadSourceDropdownValues.stream().map(WebElement::getText)
				.collect(Collectors.toList());
		leadSourceDropdownValues.stream().filter(category -> !category.getText().equalsIgnoreCase("--None--")).limit(1)
				.forEach(category -> category.click());
		return getLeadSoureDropDownValuesFromUI;
	}

	public void saveInLineEditingRecord() {
		saveEditButton.click();
	}

	public void goToRelatedTab() throws InterruptedException {
		scrollToTop();
		wait5Seconds();
		relatedTabButton.click();
	}

	public LeadHistoryPage goToLeadHistoryTab() {
		scrollByWidth();
		leadHistoryTab.click();

		return new LeadHistoryPage(driver);
	}

	public void searchLeadByCompanyName(String CName) {
		waitForElementToDisappear(spinner);
		waitForPageToLoad();
		implicitWait();
		waitForWebElementToApper(leadSearchBox);
		waitForElementToClickable(leadSearchBox);
		leadSearchBox.click();
		implicitWait();
		leadSearchBox.sendKeys(CName);
		implicitWait();
		leadSearchBox.sendKeys(Keys.ENTER);
		implicitWait();
		waitForElementToDisappear(spinner);

	}

	public int getLeadAgeFieldValue() {
		waitForWebElementToApper(leadAge);
		return Integer.parseInt(leadAge.getText());

	}

	public String getLeadAgingCategoryFiledValue() {
		waitForWebElementToApper(leadAgingCategory);
		return leadAgingCategory.getText();

	}

	public String getCurrentLeadAgingCategory() {
		int age = getLeadAgeFieldValue();
		if (age >= 0 && age <= 7) {
			return "New";
		} else if (age >= 8 && age <= 30) {
			return "Aging";
		} else {
			return "Stale";
		}
	}

}
