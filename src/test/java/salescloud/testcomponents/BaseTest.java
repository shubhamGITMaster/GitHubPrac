package salescloud.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import salescloud.pageobjects.HomePage;

public class BaseTest {
	public SoftAssert softAssert = new SoftAssert();
	public WebDriver driver;
	public HomePage homePage;

	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\salescloud\\resources\\GlobalData.properties");
		prop.load(fis);
		String borwserName = prop.getProperty("browser");
		if (borwserName.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (borwserName.contains("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}

	@BeforeMethod(alwaysRun=true)
	public HomePage launchApplication() throws IOException {
		driver = initializeDriver();
		homePage = new HomePage(driver);
		homePage.goTo();
		return homePage;
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File surce = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(surce, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";

	}

	public List<String> getJsonData(String filePath) throws IOException {
		// read json to String
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<String> data = mapper.readValue(jsonContent, new TypeReference<List<String>>() {
		});
		return data;

	}
	
	public List<HashMap<String, String>> getJsonDataKeyValue(String filePath) throws IOException {
		// read json to String
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		return data;

	}

}
