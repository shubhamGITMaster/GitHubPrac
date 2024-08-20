package salescloud.pageobjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import salescloud.abstractcomponents.AbstactComponents;

public class HomePage extends AbstactComponents {
	List<String> allproducts = new ArrayList<String>();
	WebDriver driver;
	public HomePage homePage;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//b[text()='Category']")
	private WebElement category;
	@FindBy(xpath = "//div[@class='slds-card__body']//lightning-input//span[contains(@class,'element')]")
	private List<WebElement> categoryLabels;
	@FindBy(xpath = "//a//div[@class='content']//p")
	private List<WebElement> productsNameOnUI;
	@FindBy(xpath = "//button[text()='Next']")
	private WebElement nextButtonXpath;

	public void goTo() {
		driver.get("https://hcg-sf-salesengagement-dev-ed.my.site.com/ElectronicProducts/s/");

	}

	public String getPageTitle() {
		return driver.getTitle();

	}

	public void selectCategoryToBeChecked(String categoryName) {
		categoryLabels.stream().filter(category -> !category.getText().equalsIgnoreCase(categoryName))
				.forEach(category -> {
					try {
						Thread.sleep(Duration.ofSeconds(2));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					category.click();
				});
	}

	public List<String> getAllProducts() throws InterruptedException {

		while (true) {
			Thread.sleep(Duration.ofSeconds(5));
			List<String> returnedProducts = productsNameOnUI.stream().map(WebElement::getText)
					.collect(Collectors.toList());
			allproducts.addAll(returnedProducts);
			scrollToBottom();
			if (nextButtonXpath.isEnabled()) {
				nextButtonXpath.click();
			} else {
				break;
			}
		}
		return allproducts;

	}

	public SubmitForm clickOnAnyProduct(String productName) {
		List<WebElement> filtredProduct;
		do {
			filtredProduct = productsNameOnUI.stream().filter(prod -> prod.getText().equalsIgnoreCase(productName))
					.collect(Collectors.toList());
			scrollToBottom();
			if (filtredProduct.size() < 1) {
				waitForElementToClickable(nextButtonXpath);
				nextButtonXpath.click();
				implicitWait();
			}
		} while (filtredProduct.size() < 1);
		implicitWait();
		waitForElementsToClickable(filtredProduct);
		filtredProduct.getFirst().click();
		SubmitForm submitForm = new SubmitForm(driver);
		return submitForm;

	}
	public String getSearchBoxText() {
		waitForWebElementToApper(category);
	return	category.getText();
		
	}
}
