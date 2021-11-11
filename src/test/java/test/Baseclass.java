package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Baseclass {

	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest test;
	

	XSSFWorkbook wbook;
    XSSFSheet sheet;
	
	@BeforeTest
	public void ReportSetup() throws IOException {
		report = new ExtentReports("ExtentReports.html"); 
		FileInputStream fis = new FileInputStream("exceldata.xlsx");
		wbook = new XSSFWorkbook(fis);
				sheet = wbook.getSheet("data");
	}
	
	@BeforeMethod
	public void setup() {
		

		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		driver = new ChromeDriver();
		driver.get("https://www.simplilearn.com/");
		//System.setProperty("webdriver.gecko.driver", "geckodriver");
		//WebDriver driver1 = new GeckoDriverInfo();
		//driver1.get("https://www.simplilearn.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);			
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@AfterTest
	public void ReportTearDown() {
		report.flush();
		report.close();
	}

}
