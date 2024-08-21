package salescloud.pageobjects;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import salescloud.abstractcomponents.AbstactComponents;

public class QuotesPage extends AbstactComponents  {

	WebDriver driver;
	public QuotesPage quotePage;
	public String quoteName;

	public QuotesPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//lst-breadcrumbs/following-sibling::div/h1")
	WebElement quotesHeaderText;
	@FindBy(xpath = "//a[@title='New Quote']")
	WebElement newQuoteButton;
	@FindBy(xpath = "//input[@name='Name']")
	WebElement quoteNameField;
	@FindBy(xpath = "//input[@name='ExpirationDate']")
	WebElement expirationDateField;
	@FindBy(xpath = "//button[@name='SaveEdit']")
	WebElement quoteSaveButton;
	@FindBy(xpath = "//table[@aria-label='Quotes']/tbody/tr/td[@data-label='Quote Name']//a")
	List<WebElement> quoteNameList;
	@FindBy(xpath = "//records-record-layout-item[@field-label=\"Quote Name\"]//lightning-formatted-text")
	WebElement getQuoteName;
	
	public String getPageHeaderText() throws InterruptedException {
		wait5Seconds();
		return quotesHeaderText.getText();
	}
	
	public void clickOnNewQuoteButton() throws InterruptedException {
		newQuoteButton.click();
		wait5Seconds();
	}
	
	public String fillQuoteFormAndReturnQuoteName() throws InterruptedException {
		wait5Seconds();
		quoteName = getRandomQuoteName();
		quoteNameField.sendKeys(quoteName);
		expirationDateField.sendKeys("12/23/2028");
		quoteSaveButton.click();
		wait5Seconds();
		return quoteName;
	}
	
	public String getRandomQuoteName() {
		Random random = new Random();
        int randomNumber = random.nextInt(1000) + 1;
        return "Quote : " + randomNumber;
	}

	public String getQuoteNameFromUI() throws InterruptedException {
		wait5Seconds();
		List<WebElement> qNameList = quoteNameList.stream().filter(qName -> qName.getAttribute("title").contains(quoteName)).collect(Collectors.toList());
		qNameList.get(0).click();
		return getQuoteName.getText();
	}
	
}
