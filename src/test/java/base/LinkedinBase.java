package base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import page.LinkedinHomepage;
import page.LinkedinLoginpage;
import page.LinkedinLogoutpage;
import page.LinkedinProfilepage;
import page.LinkedinSearchpage;

public class LinkedinBase {
	
	
	public static WebDriverWait wait;
	public static WebDriver driver;
	
	public static ExtentSparkReporter reporter;
	public static ExtentTest test;
	public static ExtentReports extent;
	
	public LinkedinLoginpage login;
	public LinkedinHomepage home;
	public LinkedinSearchpage search;
	public LinkedinProfilepage profile;
	public LinkedinLogoutpage logout;
	
	@BeforeTest
	public void setUp()
	{
		reporter=new ExtentSparkReporter("./Reports/myreport1.html");
		reporter.config().setDocumentTitle("Automationreport");
		reporter.config().setReportName("Functional test");
		reporter.config().setTheme(Theme.DARK);
		extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Hostname", "Localhost");
		extent.setSystemInfo("OS", "Windows10");
		extent.setSystemInfo("Testername", "Ramsana");
		extent.setSystemInfo("Browsername", "Chrome");
			
		ChromeOptions options=new ChromeOptions();
		//incognito
		//options.addArguments("--incognito");
		driver= new ChromeDriver(options);
		//driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.linkedin.com/");
		
		
	}
	@BeforeMethod
	public void beforemthd(Method mthd)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(80));
	    login = new LinkedinLoginpage (driver,wait);
	    home= new LinkedinHomepage (driver,wait);
	    search= new LinkedinSearchpage (driver,wait);
	    profile= new LinkedinProfilepage (driver,wait);
	    logout= new LinkedinLogoutpage (driver,wait);
	    
	
		test=extent.createTest(mthd.getName());//common line for display the testname
		
	}
	
	@AfterMethod
	public void browserclose(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			test.log(Status.FAIL, "test case failed is"+result.getName());
			test.log(Status.FAIL, "test case failed is"+result.getThrowable());
			String screenshotpath=screenshotMethod(driver,result.getName());
			test.addScreenCaptureFromPath(screenshotpath);
			
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			test.log(Status.SKIP, "test case skipped is"+result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			test.log(Status.PASS, "test case passed is"+result.getName());
		}
		
	}
	public static String screenshotMethod(WebDriver driver, String screenshotname) throws IOException
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destination="./Screenshots/"+screenshotname+".jpeg";
		FileHandler.copy(src, new File(destination));
		return destination;
	}
	@AfterTest
	public void teardown()
	{
		extent.flush();
		if (driver != null) {
            driver.quit();
        }
	}
	
}



