package logintest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FieldLensHomePage {
	
	private WebDriver driver;
	
	@FindBy(xpath="//a[@title='Feed']")
	WebElement feed_link;
	
	@FindBy(xpath="//a[@title='People']")
	WebElement people_link;
	
	@FindBy(xpath="//a[@title='Drawings']")
	WebElement drawings_link;
	
	public FieldLensHomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean is_FeedLink_Displayed()
	{
		return feed_link.isDisplayed();
	}
	
	public boolean is_PeopleLink_Displayed()
	{
		return people_link.isDisplayed();
	}
	
	public boolean is_DrawingsLink_Displayed()
	{
		return drawings_link.isDisplayed();
	}
	
	

}
