package salescloud.pageobjects;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import salescloud.abstractcomponents.AbstactComponents;

public class OpportunityPageInternal extends AbstactComponents {
	
	WebDriver driver;
	public OpportunityPageInternal opportunityPageInternal;

	public OpportunityPageInternal(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//button[@title='Select a List View: Opportunities']")
	WebElement openAllOpportunitiesListView;
	@FindBy(xpath = "//ul[@aria-label='Opportunities | List Views']/li[not(contains(@class, 'slds-hide'))]//a/span[@class=' virtualAutocompleteOptionText']")
	List<WebElement> allAvailableListView;
	@FindBy(xpath = "//h1/span[text()='Opportunities']/following-sibling::span")
	WebElement myOpportuntiesListViewText;
	@FindBy(xpath = "(//table/tbody/tr/th//a)[2]")
	WebElement secondRecordLink;
	@FindBy(xpath = "//a[@data-label='Related']")
	WebElement relatedTabButton;
	@FindBy(xpath = "//h2/a[contains(@href,'related/Quotes/view')]")
	WebElement quoteTab;
	
	public String openMyOpportunityListView(HashMap<String, String> input) {

		waitForWebElementToApper(openAllOpportunitiesListView);
		openAllOpportunitiesListView.click();
		waitForWebElementsToApper(allAvailableListView);
		allAvailableListView.stream().filter(tabs -> tabs.getText().equalsIgnoreCase(input.get("listViewName")))
				.findFirst().ifPresent(WebElement::click);
		return myOpportuntiesListViewText.getText();
	}
	
	public void openOpportunityRecord() throws InterruptedException {
		wait5Seconds();
		waitForWebElementToApper(secondRecordLink);
		wait5Seconds();
		secondRecordLink.click();
	}
	
	public void goToRelatedTab() throws InterruptedException {
		wait5Seconds();
		relatedTabButton.click();
	}
	
	public QuotesPage goToQuotesTab() {
		scrollByWidth();
		quoteTab.click();
		return new QuotesPage(driver);
	}
	
}
