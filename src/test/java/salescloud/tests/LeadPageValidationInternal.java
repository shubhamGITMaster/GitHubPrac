package salescloud.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fasterxml.jackson.databind.JsonNode;
import salescloud.pageobjects.HomePage;
import salescloud.pageobjects.LeadHistoryPage;
import salescloud.pageobjects.LeadPageInternal;
import salescloud.pageobjects.SubmitForm;
import salescloud.testcomponents.BaseTest;
import salescloud.testcomponents.BaseTestInternal;

public class LeadPageValidationInternal extends BaseTestInternal {
	public LeadPageInternal leadPage;
	public static final String CONVERTLEADHEADINGPOPUP = "Convert Lead";
	public static final String LEADCONVERTEDMESSAGE = "Your lead has been converted";

	@Test(priority = 10, dataProvider = "getLoginDetailsFromJson")

	public void testLeadRecordViewAndEdit(HashMap<String, String> input) throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		leadPage = loginPageInternal.loginWithCredentials(input);
		leadPage.goToSalesConsoleApp();
		leadPage.goToLeadTab();
		String selectedListViewName = leadPage.openMyLeadListView(input);
		softAssert.assertEquals(selectedListViewName, input.get("listViewName"));
		String EditedCompanyName = leadPage.goToRecordAndEdit(input);
		softAssert.assertEquals(EditedCompanyName, input.get("editedCompanyValue"));
		softAssert.assertAll();
	}

	@Test(priority = 11, dataProvider = "getLoginDetailsFromJson")
	public void testLeadConvert(HashMap<String, String> input) throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		leadPage = loginPageInternal.loginWithCredentials(input);
		leadPage.goToSalesConsoleApp();
		leadPage.goToLeadTab();
		String selectedListViewName = leadPage.goToAllOpenLeadsListView(input);
		softAssert.assertEquals(selectedListViewName, input.get("AllOpenLeadslistViewName"));
		leadPage.goToAllOpenLeadsListView(input);
		leadPage.clickOnlead();
		String actualHeading = leadPage.clickOnLeadConvertButton();
		softAssert.assertEquals(actualHeading, CONVERTLEADHEADINGPOPUP);
		String accountNameOnConvertLeadPopUp = leadPage.getAccountNameOnConvertLeadPopUp();
		String contactNameOnConvertLeadPopUp = leadPage.getContactNameOnConvertLeadPopUp();
		String opportunityNameOnConvertLeadPopUp = leadPage.getOpportunityNameOnConvertLeadPopUp();
		leadPage.clickOnLeadConvertButtonOnPopUP();
		String convertedLeadSucceMessage = leadPage.getConvertedLeadSuccessMessage();
		String convertedLeadAccountname = leadPage.getConvertedLeadAccountName();
		String convertedLeadContactname = leadPage.getConvertedLeadContactName();
		String convertedLeadOpportunityname = leadPage.getConvertedLeadOpportunityName();
		softAssert.assertEquals(convertedLeadSucceMessage, LEADCONVERTEDMESSAGE);
		softAssert.assertEquals(accountNameOnConvertLeadPopUp, convertedLeadAccountname);
		softAssert.assertEquals(contactNameOnConvertLeadPopUp, convertedLeadContactname);
		softAssert.assertEquals(opportunityNameOnConvertLeadPopUp, convertedLeadOpportunityname);
		softAssert.assertAll();

	}

	@DataProvider
	public Object[][] getLoginDetailsFromJson() throws IOException {
		List<HashMap<String, String>> loginData = getJsonDataKeyValue(
				System.getProperty("user.dir") + "\\src\\test\\java\\salescloud\\data\\LeadObjectLoginDetails.json");
		return new Object[][] { { loginData.get(0) } };
	}

	@Test(priority = 12, dataProvider = "getLoginDetailsFromJson")
	public void testLeadIndustryCategorydependency(HashMap<String, String> input)
			throws IOException, InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		LeadPageInternal leadPage = loginPageInternal.loginWithCredentials(input);
		leadPage.goToSalesConsoleApp();
		leadPage.goToLeadTab();
		String selectedListViewName = leadPage.openMyLeadListView(input);
		softAssert.assertEquals(selectedListViewName, input.get("listViewName"));
		leadPage.startInLineEditForLeadRecord();

		JsonNode rootNode = getIndustryCategoryComboFromJson();

		JsonNode electronisNode = rootNode.get("Electronics");
		List<String> electronicsListFromJSON = convertJsonNodeToList(electronisNode);
		List<String> electronicsCategoryValues = leadPage.getCategoryValuesBasedOnIndustry("Electronics");
		softAssert.assertEquals(electronicsCategoryValues, electronicsListFromJSON);

		JsonNode telecommunicationNode = rootNode.get("Telecommunications");
		List<String> telecommunicationListFromJSON = convertJsonNodeToList(telecommunicationNode);

		List<String> telecommunicationCategoryValues = leadPage.getCategoryValuesBasedOnIndustry("Telecommunications");
		softAssert.assertEquals(telecommunicationCategoryValues, telecommunicationListFromJSON);

		JsonNode utilitiesNode = rootNode.get("Utilities");
		List<String> utilitiesListFromJSON = convertJsonNodeToList(utilitiesNode);

		List<String> utilitiesCategoryValues = leadPage.getCategoryValuesBasedOnIndustry("Utilities");
		softAssert.assertEquals(utilitiesCategoryValues, utilitiesListFromJSON);

		JsonNode otherNode = rootNode.get("Other");
		List<String> otherListFromJSON = convertJsonNodeToList(otherNode);

		List<String> otherCategoryValues = leadPage.getCategoryValuesBasedOnIndustry("Other");
		softAssert.assertEquals(otherCategoryValues, otherListFromJSON);

		softAssert.assertAll();

		leadPage.saveInLineEditingRecord();

	}

	@Test(priority = 13, dataProvider = "getDataForSubmitFormFields", dataProviderClass = SubmitFormValidation.class)

	public void testLeadCreationToLeadConvert(HashMap<String, String> input) throws IOException, InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("userName", "salesgtmproject@hcg.com");
		data.put("password", "gtmproject123");
		data.put("AllOpenLeadslistViewName", "All Open Leads");

		BaseTest baseTest = new BaseTest();
		HomePage homePage = baseTest.launchApplication();
		SubmitForm submitForm = homePage.clickOnAnyProduct(input.get("ProductName"));
		String actualProductNameOnSubmitForm = submitForm.getProductNameOnSubmitForm();
		softAssert.assertEquals(actualProductNameOnSubmitForm, input.get("ProductName"));
		String LeadCompanyName = input.get("Company");
		submitForm.fillSumbitFormFields(input);
		submitForm.clickOnTheSubmitFormButton();
		String actualSuccessToastMeassage = submitForm.getToastmessage();
		softAssert.assertEquals(actualSuccessToastMeassage, SubmitFormValidation.TOASTMESSAGE);
		baseTest.tearDown();
		leadPage = loginPageInternal.loginWithCredentials(data);
		leadPage.goToSalesConsoleApp();
		leadPage.goToLeadTab();
		String selectedListViewName = leadPage.goToAllOpenLeadsListView(data);
		softAssert.assertEquals(selectedListViewName, data.get("AllOpenLeadslistViewName"));
		leadPage.searchLeadByCompanyName(LeadCompanyName);
		leadPage.clickOnlead();
		String actualHeading = leadPage.clickOnLeadConvertButton();
		softAssert.assertEquals(actualHeading, CONVERTLEADHEADINGPOPUP);
		String accountNameOnConvertLeadPopUp = leadPage.getAccountNameOnConvertLeadPopUp();
		String contactNameOnConvertLeadPopUp = leadPage.getContactNameOnConvertLeadPopUp();
		String opportunityNameOnConvertLeadPopUp = leadPage.getOpportunityNameOnConvertLeadPopUp();
		leadPage.clickOnLeadConvertButtonOnPopUP();
		String convertedLeadSucceMessage = leadPage.getConvertedLeadSuccessMessage();
		String convertedLeadAccountname = leadPage.getConvertedLeadAccountName();
		String convertedLeadContactname = leadPage.getConvertedLeadContactName();
		String convertedLeadOpportunityname = leadPage.getConvertedLeadOpportunityName();
		softAssert.assertEquals(convertedLeadSucceMessage, LEADCONVERTEDMESSAGE);
		softAssert.assertEquals(accountNameOnConvertLeadPopUp, convertedLeadAccountname);
		softAssert.assertEquals(contactNameOnConvertLeadPopUp, convertedLeadContactname);
		softAssert.assertEquals(opportunityNameOnConvertLeadPopUp, convertedLeadOpportunityname);
		softAssert.assertEquals(LeadCompanyName, convertedLeadAccountname);
		softAssert.assertAll();

	}

	@Test(priority = 14, dataProvider = "getLoginDetailsFromJson")
	public void testLeadSourceAndStatusValuesAndHistoryTracking(HashMap<String, String> input)
			throws IOException, InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		LeadPageInternal leadPage = loginPageInternal.loginWithCredentials(input);
		leadPage.goToSalesConsoleApp();
		leadPage.goToLeadTab();
		String selectedListViewName = leadPage.openMyLeadListView(input);
		softAssert.assertEquals(selectedListViewName, input.get("listViewName"));
		leadPage.startInLineEditForLeadRecord();
		JsonNode rootNode = getLeadStatusAndSourceFromJson();

		String originalLeadStatusValue = leadPage.getLeadStatusSelectedValue(true);
		JsonNode leadStatusNodeFromJson = rootNode.get("Lead_Status");
		List<String> convertedLeadStatusListFromJSON = convertJsonNodeToList(leadStatusNodeFromJson);
		List<String> leadStatusValuesFromUI = leadPage.getLeadStatusValuesFromUI();
		softAssert.assertEquals(leadStatusValuesFromUI, convertedLeadStatusListFromJSON);

		String newLeadStatusValue = leadPage.getLeadStatusSelectedValue(false);
		JsonNode leadSourceNodeFromJson = rootNode.get("Lead_Source");
		List<String> convertedLeadSourceListFromJSON = convertJsonNodeToList(leadSourceNodeFromJson);
		List<String> leadSourceValuesFromUI = leadPage.getLeadSourceValuesFromUI();
		softAssert.assertEquals(leadSourceValuesFromUI, convertedLeadSourceListFromJSON);

		leadPage.saveInLineEditingRecord();

		leadPage.goToRelatedTab();
		LeadHistoryPage leadHistoryPage = leadPage.goToLeadHistoryTab();
		String pageHeader = leadHistoryPage.getPageHeaderText();
		softAssert.assertEquals(pageHeader, "Lead History");

		String originalLeadHistoryValue = leadHistoryPage.getOriginalValue();
		softAssert.assertEquals(originalLeadHistoryValue, originalLeadStatusValue);

		String newLeadHistoryValue = leadHistoryPage.getNewValue();
		softAssert.assertEquals(newLeadHistoryValue, newLeadStatusValue);
		softAssert.assertAll();
	}

	@Test(priority = 15, dataProvider = "getLoginDetailsFromJson")
	// Verify that leads are categorized based on their age as New (0-7 days), Aging
	// (8-30 days), and Stale (31+ days).
	public void testLeadAge(HashMap<String, String> input) throws InterruptedException, IOException {
		SoftAssert softAssert = new SoftAssert();
		LeadPageInternal leadPage = loginPageInternal.loginWithCredentials(input);
		leadPage.goToSalesConsoleApp();
		leadPage.goToLeadTab();
		String selectedListViewName = leadPage.goToAllOpenLeadsListView(input);
		softAssert.assertEquals(selectedListViewName, input.get("AllOpenLeadslistViewName"));
		leadPage.clickOnlead();
		JsonNode rootNode = getLeadAgeCategoryDataFromJson();
		JsonNode ageCategories = rootNode.get("age_categories");
		 if (ageCategories == null || !ageCategories.isArray()) {
	            softAssert.fail("Age categories are not available or in the wrong format.");
	            return;
	        }
		 String leadAgingCategory = leadPage.getCurrentLeadAgingCategory();
		 int leadAgeDays = leadPage.getLeadAgeFieldValue();
		 String expectedCategory = getExpectedCategoryForAge(ageCategories, leadAgeDays);
		 softAssert.assertEquals(expectedCategory, leadAgingCategory);
		 softAssert.assertAll();
	}
	
}
