package salescloud.pageobjects;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import salescloud.abstractcomponents.AbstactComponents;

public class SubmitForm extends AbstactComponents {
	WebDriver driver;

	public SubmitForm(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//b")
	private WebElement productNameOnSubmitForm;
	@FindBy(xpath = "//div//label[text()='First Name']//parent::label//following-sibling::div//input")
	private WebElement firstName;
	@FindBy(xpath = "//div//label[text()='Last Name']//parent::label//following-sibling::div//input")
	private WebElement lastName;
	@FindBy(xpath = "//div//label[text()='Company']//parent::label//following-sibling::div//input")
	private WebElement company;
	@FindBy(xpath = "//div//label[text()='Phone']//parent::label//following-sibling::div//input")
	private WebElement phone;
	@FindBy(xpath = "//div//label[text()='Company Email']//parent::label//following-sibling::div//input")
	private WebElement companyEmail;
	@FindBy(xpath = "//div//label[text()='Contact Email']//parent::label//following-sibling::div//input")
	private WebElement contactEmail;
	@FindBy(xpath = "//lightning-base-combobox[@exportparts='dropdown, option']")
	private WebElement regionDropDownButton;
	@FindBy(xpath = "//div//label[text()='Annual Revenue In USD']//parent::label//following-sibling::div//input")
	private WebElement annualRevenue;
	@FindBy(xpath = "//div//label[text()='Number Of Employees']//parent::label//following-sibling::div//input")
	private WebElement numberOfEmployee;
	@FindBy(xpath = "//div//label[text()='Website']//parent::label//following-sibling::div//input")
	private WebElement website;
	@FindBy(xpath = "//span[@class='slds-truncate']")
	private List<WebElement> regions;
	@FindBy(xpath = "//lightning-button[@variant='brand']")
	private WebElement formSubmitButton;
	@FindBy(css = ".toastMessage.forceActionsText")
	private WebElement SuccessToastmessage;
	@FindBy(xpath = "//lightning-button[@variant='destructive']")
	private WebElement cancelButton;
	@FindBy(xpath = "//label[text()='First Name']//ancestor::lightning-primitive-input-simple//div[text()='Complete this field.']")
	private WebElement firstNameError;
	@FindBy(xpath = "//label[text()='Last Name']//ancestor::lightning-primitive-input-simple//div[text()='Complete this field.']")
	private WebElement lastNameError;
	@FindBy(xpath = "//label[text()='Company']//ancestor::lightning-primitive-input-simple//div[text()='Complete this field.']")
	private WebElement companyError;
	@FindBy(xpath = "//label[text()='Phone']//ancestor::lightning-primitive-input-simple//div[text()='Complete this field.']")
	private WebElement phoneError;
	@FindBy(xpath = "//label[text()='Company Email']//ancestor::lightning-primitive-input-simple//div[text()='Complete this field.']")
	private WebElement companyEmailError;
	@FindBy(xpath = "//label[text()='Contact Email']//ancestor::lightning-primitive-input-simple//div[text()='Complete this field.']")
	private WebElement contactEmailError;
	@FindBy(xpath = "//label[text()='Region']//ancestor::lightning-combobox//div[text()='Complete this field.']")
	private WebElement regionError;
	@FindBy(xpath = "//label[text()='Number Of Employees']//ancestor::lightning-primitive-input-simple//div[text()='Complete this field.']")
	private WebElement numberOfEmployeesError;
	@FindBy(xpath = "//label[text()='Website']//ancestor::lightning-primitive-input-simple//div[text()='Complete this field.']")
	private WebElement websiteError;
	

	public String getProductNameOnSubmitForm() {
		implicitWait();
		waitForWebElementToApper(productNameOnSubmitForm);
		implicitWait();
		return productNameOnSubmitForm.getText();
	}

	public void fillSumbitFormFields(HashMap<String, String> input) {
		waitForWebElementToApper(firstName);
		firstName.sendKeys(input.get("FirstName"));
		waitForWebElementToApper(lastName);
		lastName.sendKeys(input.get("LastName"));
		waitForWebElementToApper(company);
		company.sendKeys(input.get("Company"));
		waitForWebElementToApper(phone);
		phone.sendKeys(input.get("Phone"));
		waitForWebElementToApper(companyEmail);
		companyEmail.sendKeys(input.get("CompanyEmail"));
		waitForWebElementToApper(contactEmail);
		contactEmail.sendKeys(input.get("ContactEmail"));
		waitForWebElementToApper(regionDropDownButton);
		regionDropDownButton.click();
		waitForWebElementsToApper(regions);
		regions.stream().filter(r -> r.getText().equalsIgnoreCase(input.get("Region"))).findFirst()
				.ifPresent(WebElement::click);
		waitForWebElementToApper(annualRevenue);
		annualRevenue.sendKeys(input.get("AnnualRevenue"));
		waitForWebElementToApper(numberOfEmployee);
		numberOfEmployee.sendKeys(input.get("NumberOfEmployees"));
		waitForWebElementToApper(website);
		website.sendKeys(input.get("Website"));
	}

	public void clickOnTheSubmitFormButton()  {
		implicitWait();
		waitForWebElementToApper(formSubmitButton);
		waitForElementToClickable(formSubmitButton);
		implicitWait();
	     formSubmitButton.click();

	}

	public String getToastmessage() {
		implicitWait();
		waitForWebElementToApper(SuccessToastmessage);
		implicitWait();
		return SuccessToastmessage.getText();
	}

	public void clickOnTheCancelButton() {
		implicitWait();
		waitForWebElementToApper(cancelButton);
		waitForElementToClickable(cancelButton);
		cancelButton.click();

	}

	public String getErrorMessageFN() {
		waitForWebElementToApper(firstNameError);
		return firstNameError.getText();
	}

	public String getErrorMessageLN() {
		waitForWebElementToApper(lastNameError);
		return lastNameError.getText();
	}

	public String getErrorMessageC() {
		waitForWebElementToApper(companyError);
		return companyError.getText();
	}

	public String getErrorMessageP() {
		waitForWebElementToApper(phoneError);
		return phoneError.getText();
	}

	public String getErrorMessageCE() {
		waitForWebElementToApper(companyEmailError);
		return companyEmailError.getText();
	}

	public String getErrorMessagePE() {
		waitForWebElementToApper(contactEmailError);
		return contactEmailError.getText();
	}

	public String getErrorMessageR() {
		waitForWebElementToApper(regionError);
		return regionError.getText();
	}

	public String getErrorMessageNE() {
		waitForWebElementToApper(numberOfEmployeesError);
		return numberOfEmployeesError.getText();
	}

	public String getErrorMessageW() {
		waitForWebElementToApper(websiteError);
		return websiteError.getText();
	}
}
