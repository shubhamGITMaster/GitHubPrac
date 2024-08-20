package salescloud.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import salescloud.pageobjects.SubmitForm;
import salescloud.testcomponents.BaseTest;

public class SubmitFormValidation extends BaseTest {
	public SubmitForm submitForm;
	public static final String TOASTMESSAGE = "Thank you for submitting your request";
	public static final String CATEGORY = "Category";

	@Test(priority = 6, dataProvider = "getDataForSubmitFormFields")
	// Validate whether the user can submit the form for another product after
	// submitting the initial form.
	// Validate if user is interested in any of the product he should be able to
	// submit the form

	public void testProducts_SubmitformForAnyProduct(HashMap<String, String> input) throws InterruptedException {
		submitForm = homePage.clickOnAnyProduct(input.get("ProductName"));
		String actualProductNameOnSubmitForm = submitForm.getProductNameOnSubmitForm();
		softAssert.assertEquals(actualProductNameOnSubmitForm, input.get("ProductName"));
		submitForm.fillSumbitFormFields(input);
		submitForm.clickOnTheSubmitFormButton();
		String actualSuccessToastMeassage = submitForm.getToastmessage();
		softAssert.assertEquals(actualSuccessToastMeassage, TOASTMESSAGE);
		softAssert.assertAll();

	}

	@Test(priority = 8, dataProvider = "getDataForSubmitFormErrorMessage")
	// Validate if a validation message is shown to user on clicking submit without
	// entering mandatory details

	public void tetProducts_ValidateErrorMessagesOnSubmitForm(HashMap<String, String> input){
		submitForm = homePage.clickOnAnyProduct(input.get("ProductName"));
		submitForm.clickOnTheSubmitFormButton();
		String actualFN = submitForm.getErrorMessageFN();
		softAssert.assertEquals(actualFN, input.get("FirstNameEM"));
		String actualLN = submitForm.getErrorMessageLN();
		softAssert.assertEquals(actualLN, input.get("LastNameEM"));
		String actualC = submitForm.getErrorMessageC();
		softAssert.assertEquals(actualC, input.get("CompanyEM"));
		String actualP = submitForm.getErrorMessageP();
		softAssert.assertEquals(actualP, input.get("PhoneEM"));
		String actualCE = submitForm.getErrorMessageCE();
		softAssert.assertEquals(actualCE, input.get("CompanyEmailEM"));
		String actualPE = submitForm.getErrorMessagePE();
		softAssert.assertEquals(actualPE, input.get("PersonalEmailEM" ));
		String actualR = submitForm.getErrorMessageR();
		softAssert.assertEquals(actualR, input.get("RegionEM"));
		String actualAR = submitForm.getErrorMessageAR();
		softAssert.assertEquals(actualAR, input.get("AnnualRevenueEM"));
		String actualNE = submitForm.getErrorMessageNE();
		softAssert.assertEquals(actualNE, input.get("NumberOfEmployeesEM"));
		String actualW = submitForm.getErrorMessageW();
		softAssert.assertEquals(actualW, input.get("WebsiteEM"));
		softAssert.assertAll();

	}

	@Test(priority = 9, dataProvider = "getDataForSubmitFormErrorMessage")
	// Validate if on clicking cancel button, user will be directed to products list
	// page
	public void testProducts_ValidateCancelButton(HashMap<String, String> input) {
		submitForm = homePage.clickOnAnyProduct(input.get("ProductName"));
		submitForm.clickOnTheCancelButton();
		String actualText = homePage.getSearchBoxText();
		softAssert.assertEquals(actualText, CATEGORY);
		softAssert.assertAll();

	}

	@DataProvider
	public Object[][] getDataForSubmitFormFields() throws IOException {
		List<HashMap<String, String>> dataforSubmitFormFields = getJsonDataKeyValue(
				System.getProperty("user.dir") + "\\src\\test\\java\\salescloud\\data\\SumbitFormFieldsData.json");
		return new Object[][] { { dataforSubmitFormFields.get(0) }, { dataforSubmitFormFields.get(1) } };
	}

	@DataProvider
	public Object[][] getDataForSubmitFormErrorMessage() throws IOException {
		List<HashMap<String, String>> dataforSubmitFormFields = getJsonDataKeyValue(System.getProperty("user.dir")
				+ "\\src\\test\\java\\salescloud\\data\\SubmitFormErrorMessagesData.json");
		return new Object[][] { { dataforSubmitFormFields.get(0) } };
	}

}
