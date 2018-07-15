package logintest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {
	
	public static WebDriver driver;
	
	
	@BeforeClass
	public void openChrome()
	{
		System.setProperty("webdriver.chrome.driver", "/Users/skodha/sachin/softwares/Selenium/chromedriver");
		driver = new ChromeDriver();
	}
	
	@BeforeMethod
	public void openURL()
	{
		driver.get("https://app.fieldlens.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void test_login()
	{
		FieldLensLoginPage loginpage = new FieldLensLoginPage(driver);
		FieldLensHomePage homepage = new FieldLensHomePage(driver);
		
		loginpage.set_emailaddress("sachinkodha@gmail.com");
		loginpage.set_password("pass1234");
		loginpage.click_loginButton();
		
		Assert.assertEquals(homepage.is_DrawingsLink_Displayed(), true);
		Assert.assertEquals(homepage.is_FeedLink_Displayed(), true);
		Assert.assertEquals(homepage.is_PeopleLink_Displayed(), true);
	}

	@AfterClass
	public void close()
	{
		driver.quit();
	}

}
