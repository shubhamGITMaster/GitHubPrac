package salescloud.abstractcomponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstactComponents {
	WebDriver driver;
	
	public AbstactComponents(WebDriver driver) {
		this.driver=driver;
		
	}
	
	public void waitForWebElementToApper(WebElement element) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	public void waitForElementToClickable(WebElement element) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public void waitForElementsToClickable(List<WebElement> element) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	public void waitForWebElementsToApper(List<WebElement> element) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	public void scrollToTop() {
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.HOME).build().perform();
	}
	
	public void scrollToBottom() {
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.END).build().perform();
	}
	

}
