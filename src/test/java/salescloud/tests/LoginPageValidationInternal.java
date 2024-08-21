package salescloud.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import salescloud.testcomponents.BaseTestInternal;

public class LoginPageValidationInternal extends BaseTestInternal {

	SoftAssert softAssert = new SoftAssert();
	
	@Test(priority = 9, dataProvider = "getLoginDetailsFromJson") 
	public void testInternalLoginPage(HashMap<String, String> input) throws InterruptedException {
		loginPageInternal.loginWithCredentials(input);
		String pageTitle = loginPageInternal.getLoggedInPageTitle();
		softAssert.assertEquals(pageTitle, "Home | Salesforce");
		softAssert.assertAll();
		
	}
	
	@DataProvider
	public Object[][] getLoginDetailsFromJson() throws IOException {
		List<HashMap<String,String>> loginData = getJsonDataKeyValue(
				System.getProperty("user.dir") + "\\src\\test\\java\\salescloud\\data\\LeadObjectLoginDetails.json");
		return new Object[][] { {loginData.get(0)} };
	}
}
