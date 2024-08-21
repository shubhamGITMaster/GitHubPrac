package salescloud.abstractcomponents;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstactComponents {
	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;

	public AbstactComponents(WebDriver driver) {
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	public void waitForWebElementToApper(WebElement element) {

		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waitForElementToClickable(WebElement element) {

		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementsToClickable(List<WebElement> element) {

		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public void waitForWebElementsToApper(List<WebElement> element) {

		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public void scrollToTop() {
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.HOME).build().perform();
	}

	public void scrollToBottom() {
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.END).build().perform();
	}

	public void zoomIn() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='100%'");
	}

	public void zoomOut() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='67%'");
	}

	public void scrollByWidth() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 200);");
	}


	public void waitForPageToLoad() {
		// Wait until the JavaScript document.readyState is 'complete'
		wait.until(driver -> js.executeScript("return document.readyState").equals("complete"));
	}
	public void waitUntilPageIsLoaded(By locator) {
	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public void waitForElementToDisappear(WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}


	public void scrollUpByWidth() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, -200);");
	}
	

	public void wait5Seconds() throws InterruptedException {
		Thread.sleep(5);
	}
	

}
