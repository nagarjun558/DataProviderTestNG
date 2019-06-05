package TestNGData;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.TestUtil;

public class LoginTest 
{
	WebDriver driver;
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "E:\\selenium jars\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://classic.crmpro.com/index.html");
	}
	
	@DataProvider
	public Object[][]  getLoginData( )
	{
		Object[][] data = TestUtil.getTestData("login");
		return data;
		
	}
	
	@Test(dataProvider = "getLoginData")
	public void loginTest(String username,String password)
	{
		driver.findElement(By.xpath("//*[@id='loginForm']/div/input[1]")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id='loginForm']/div/input[2]")).sendKeys(password);
		
		//loginbtn
		
		WebElement loginBtn = driver.findElement(By.xpath("//*[@id='loginForm']/div/div/input"));
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", loginBtn);
	}
	
@AfterMethod
public void tearDown()
{
	driver.quit();
}
	

}
