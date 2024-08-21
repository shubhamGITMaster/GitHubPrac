package salescloud.tests;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import salescloud.testcomponents.BaseTest;

public class HomePageValidation extends BaseTest {

	
	String homepageTitle = "Home";
	public static final String TELECOMMUNICATION = "Telecommunications";
	public static final String UTILITIES = "Utilities";
	public static final String OTHER = "Other";
	public static final String ELECTRONICS = "Electronics";

	@Test(priority = 1)
	public void testHomePageTitle() {
		SoftAssert softAssert = new SoftAssert();
		String expected = homePage.getPageTitle();
		softAssert.assertEquals(expected, homepageTitle);
		softAssert.assertAll();
	}

	@Test(priority = 2, dataProvider = "getTelecommunicationsProductData")
	public void testProducts_telecommunication(List<String> telinput) throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		homePage.implicitWait();
		homePage.selectCategoryToBeChecked(TELECOMMUNICATION);
		List<String> productsFromUI = homePage.getAllProducts();
		boolean areEqual = productsFromUI.equals(telinput);
		softAssert.assertTrue(areEqual);
		softAssert.assertAll();
	}

	@Test(priority = 3, dataProvider = "getUtilitiesProductData")
	public void testProducts_Utilties(List<String> input) throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		homePage.implicitWait();
		homePage.selectCategoryToBeChecked(UTILITIES);
		List<String> productsFromUI = homePage.getAllProducts();
		boolean areEqual = productsFromUI.equals(input);
		softAssert.assertTrue(areEqual);
		softAssert.assertAll();
	}


	@Test(priority = 4, dataProvider = "getOtherProductData")
	public void TestProducts_Other(List<String> input) throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		homePage.implicitWait();
		homePage.selectCategoryToBeChecked(OTHER);
		List<String> productsFromUI = homePage.getAllProducts();
		boolean areEqual = productsFromUI.equals(input);
		softAssert.assertTrue(areEqual);
		softAssert.assertAll();
	}
	
	@Test(priority = 5, dataProvider = "getElectronicsProductData")
	public void TestProducts_Electronics(List<String> input) throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		homePage.implicitWait();
		homePage.selectCategoryToBeChecked(ELECTRONICS);
		List<String> productsFromUI = homePage.getAllProducts();
		boolean areEqual = productsFromUI.equals(input);
		softAssert.assertTrue(areEqual);
		softAssert.assertAll();
	}

	@DataProvider
	public Object[] getTelecommunicationsProductData() throws IOException {
		List<String> telecommunicationsProdData = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\salescloud\\data\\Telecommunications.json");
		return new Object[] { telecommunicationsProdData };
	}

	@DataProvider
	public Object[] getUtilitiesProductData() throws IOException {
		List<String> utilitiesProdData = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\salescloud\\data\\Utilities.json");
		return new Object[] { utilitiesProdData };
	}

	@DataProvider
	public Object[] getOtherProductData() throws IOException {
		List<String> otherProdData = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\salescloud\\data\\Other.json");
		return new Object[] { otherProdData };
	}
	
	@DataProvider
	public Object[] getElectronicsProductData() throws IOException {
		List<String> electronicsProdData = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\salescloud\\data\\Electronics.json");
		return new Object[] { electronicsProdData };
	}

}
