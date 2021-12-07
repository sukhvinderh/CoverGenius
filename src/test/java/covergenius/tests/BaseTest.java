package covergenius.tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import covergenius.utils.ExtentReportUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected WebDriver driver;
	static ExtentReports extent;
	static ExtentTest test;

	@BeforeClass(alwaysRun = true)
	public static void beforeClass() {
		WebDriverManager.chromedriver().setup();
	}
	
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		
		extent = ExtentReportUtil.getExtentReport();
		
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		
		test = extent.createTest(method.getName());

		this.driver = new ChromeDriver();
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		this.driver.manage().window().maximize();
		this.driver.navigate().to("https://www.rentalcover.com");

	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		ExtentReportUtil.getResults(result, driver, test);
		 driver.quit();
	}
	
	@AfterSuite
	public void afterSuite() {
			
		extent.flush();
    }
	
}

