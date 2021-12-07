package covergenius.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import covergenius.exceptions.RentalCoverException;
import covergenius.utils.Utilities;

public class RentalCoverLandingPage extends BasePage {

	public RentalCoverLandingPage(WebDriver driver) {
		super(driver);
	}
	
	public PolicyInfoAndPayment getPolicyQuoteForVehicleRental1Day(String country, String vehicleType) {
		typeCountryName(country);
		selectVehicleType(vehicleType);
		
		return getYourInstantQuote();
	}
	
	public PolicyInfoAndPayment getPolicyQuoteForVehicleRentalForDuration(String country, String vehicleType, int days) {
		typeCountryName(country);
		setEndDate(Utilities.addDaysToCurrentDate(days));
		selectVehicleType(vehicleType);
		
		return getYourInstantQuote();
	}
	
	private void setEndDate(String date) {
		
		clickOnDateField();
		waitForAWhile(4000);
//		new WebDriverWait(driver, Duration.ofSeconds(20))
//		.until(driver -> driver.findElement(By.className("ui-datepicker-calendar")));
//		
		WebElement datePicker = driver.findElement(By.className("ui-datepicker-calendar"));
		waitForAWhile(2000);
		datePicker.findElement(By.linkText(date)).click();
		
	}
	
	private void clickOnDateField() {
		
		new WebDriverWait(driver, Duration.ofSeconds(15)).until(driver -> driver.findElement(By.id("QuoteForm_ToDate-datepicker")));
		driver.findElement(By.id("QuoteForm_ToDate-datepicker")).click();
	}
	
	public PolicyInfoAndPayment getPolicyQuoteForVehicleRentalForDateRange(String country, String vehicleType, int daysPlusCurrentDate) {
		typeCountryName(country);
		setEndDate(Utilities.addDaysToCurrentDate(daysPlusCurrentDate));
		selectVehicleType(vehicleType);
		
		return getYourInstantQuote();
	}

	private void typeCountryName(String country) {
		new WebDriverWait(driver, Duration.ofSeconds(15))
				.until(driver -> driver.findElements(By.cssSelector("[class^='QuoteForm-destination-select']")).get(1));
		driver.findElements(By.cssSelector("[class^='QuoteForm-destination-select']")).get(1).sendKeys(country);
	}

	private void selectVehicleType(String vehicleType) {
		clickOnChangeVehicleLink();
		selectVehicleTypeInDropdown(vehicleType);
	}

	private PolicyInfoAndPayment getYourInstantQuote() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
				.until(driver -> driver.findElement(By.cssSelector("button[class^='QuoteForm-submit']")));
		scrollIntoViewUp(By.cssSelector("button[class^='QuoteForm-submit']"));
		
		driver.findElement(By.cssSelector("button[class^='QuoteForm-submit']")).click();

		return new PolicyInfoAndPayment(driver);
	}
	
	private void selectVehicleTypeInDropdown(String vehicleType) {
		new WebDriverWait(driver, Duration.ofSeconds(15))
				.until(driver -> driver.findElement(By.cssSelector("[class^='QuoteForm-vehicleType-select']")));

		scrollIntoViewDown(By.cssSelector("[class^='QuoteForm-vehicleType-select']"));
		Select vehicle = new Select(driver.findElement(By.cssSelector("[class^='QuoteForm-vehicleType-select']")));
		vehicle.selectByValue(vehicleType);

	}
	

	private void clickOnChangeVehicleLink() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
				.until(driver -> driver.findElement(By.cssSelector("[href='#QuoteForm-vehicleType-field']")));
		driver.findElement(By.cssSelector("[href='#QuoteForm-vehicleType-field']")).click();

	}

}
