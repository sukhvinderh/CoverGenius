package covergenius.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	protected WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	protected void scrollIntoViewDown(By by) {
		
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(driver -> driver.findElement(by));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
	   
	}
	
	protected void scrollIntoViewUp(By by) {
		
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(driver -> driver.findElement(by));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", driver.findElement(by));
	   
	}
	
	protected void waitForAWhile(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
