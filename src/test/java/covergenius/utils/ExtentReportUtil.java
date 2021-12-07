package covergenius.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportUtil {
	
	static ExtentSparkReporter htmlReporter;
	static ExtentReports extent;
	static ExtentTest test;
	
	public static ExtentReports getExtentReport() {
		htmlReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/Reports/"+getReportFileName());
		htmlReporter.config().setDocumentTitle("Cover Genius - Test Automation");
		htmlReporter.config().setReportName("Cover Genius Test Automation Challenge");
		htmlReporter.config().setTimeStampFormat("yyyy-MM-dd hh:mm:ss a");
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("OS", "Windows");
		
		return extent;
	}
	
	private static String getReportFileName() {
		
		String pattern = "yyyy.MM.dd.HH.mm.ss";
		
		String timeStamp = new SimpleDateFormat(pattern).format(new Date());
		
		return "CoverGeniusTestReport "+timeStamp+".html";
		
	}
	
	public static void getResults(ITestResult result, WebDriver driver, ExtentTest test) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test Case FAILED: " + result.getMethod().getMethodName());
			test.log(Status.FAIL, "Test Case FAILED At: " + result.getThrowable());
			
			String screenshotPath = ExtentReportUtil.getScreenshot(driver, result.getName());
			test.addScreenCaptureFromPath(screenshotPath);

		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Case SKIPPED: " + result.getMethod().getMethodName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Case PASSED:  " + result.getMethod().getMethodName());
		}
	}
	
	private static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}


}
