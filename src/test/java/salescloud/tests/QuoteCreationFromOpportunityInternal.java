package salescloud.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import salescloud.pageobjects.LoginPageInternal;
import salescloud.pageobjects.OpportunityPageInternal;
import salescloud.pageobjects.QuotesPage;
import salescloud.testcomponents.BaseTestInternal;

public class QuoteCreationFromOpportunityInternal extends BaseTestInternal {

	@Test(priority = 16, dataProvider = "getLoginDetailsFromJson")

	public void testQuoteCreationFromExistingOpportunity(HashMap<String, String> input) throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		LoginPageInternal loginPage = loginPageInternal.internalAppLogin(input);
		loginPage.goToSalesConsoleApp();
		OpportunityPageInternal opportunityPage=loginPage.goToOpportunityTab();
		String selectedListViewName = opportunityPage.openMyOpportunityListView(input);
		softAssert.assertEquals(selectedListViewName, input.get("listViewName"));
		opportunityPage.openOpportunityRecord();
		opportunityPage.goToRelatedTab();
		QuotesPage quotesPage = opportunityPage.goToQuotesTab();
		quotesPage.getPageHeaderText();
		String pageHeader = quotesPage.getPageHeaderText();
		softAssert.assertEquals(pageHeader, "Quotes");
		quotesPage.clickOnNewQuoteButton();
		String actualQuoteName = quotesPage.fillQuoteFormAndReturnQuoteName();
		String quoteNameFromUI = quotesPage.getQuoteNameFromUI();
		softAssert.assertEquals(quoteNameFromUI, actualQuoteName);
		softAssert.assertAll();
		
	}
	
	@DataProvider
	public Object[][] getLoginDetailsFromJson() throws IOException {
		List<HashMap<String, String>> loginData = getJsonDataKeyValue(
				System.getProperty("user.dir") + "\\src\\test\\java\\salescloud\\data\\LoginDetailsAndOppLVData.json");
		return new Object[][] { { loginData.get(0) } };
	}

	
}
