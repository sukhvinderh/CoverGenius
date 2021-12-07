package covergenius.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PolicyInfoAndPayment extends BasePage {

	public PolicyInfoAndPayment(WebDriver driver) {
		super(driver);

	}

	private WebElement getPolicyInformation() {
		new WebDriverWait(driver, Duration.ofSeconds(15))
				.until(driver -> driver.findElement(By.cssSelector("div[class='PolicySummary']")));

		return driver.findElement(By.cssSelector("div[class='PolicySummary']"));
	}

	public String getDestinationCountry() {
		return getPolicyInformation().findElements(By.cssSelector("[class$='Field-data']")).get(0).getText();
	}

	public String getStartDate() {
		return getPolicyInformation().findElements(By.cssSelector("[class$='Field-data']")).get(1).getText();
	}

	public String getEndDate() {
		return getPolicyInformation().findElements(By.cssSelector("[class$='Field-data']")).get(2).getText();
	}

	public String getVehicleType() {
		return getPolicyInformation().findElements(By.cssSelector("[class$='Field-data']")).get(3).getText();
	}

	public String getCoverage() {
		return getPolicyInformation().findElements(By.cssSelector("[class$='Field-data']")).get(4).getText();
	}

	public String getCurrency() {
		return getPolicyInformation().findElements(By.cssSelector("[class$='xLarge Price-now']")).get(0).getText()
				.substring(0, 3);
	}

	public float getTotalPrice() {
		return Float.parseFloat(getPolicyInformation().findElements(By.cssSelector("[class$='xLarge Price-now']"))
				.get(0).getText().substring(3));
	}

}
