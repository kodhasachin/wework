package logintest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FieldLensLoginPage {
	
	private WebDriver driver;
	
	@FindBy(xpath="//input[@id='login-email']")
	WebElement emailaddress_field;
	
	@FindBy(xpath="//input[@id='password']")
	WebElement password_field;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement login_button;
	
	public FieldLensLoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void set_emailaddress(String email)
	{
		emailaddress_field.sendKeys(email);
	}
	
	public void set_password(String pwd)
	{
		password_field.sendKeys(pwd);
	}
	
	public void click_loginButton()
	{
		login_button.click();
	}

}
