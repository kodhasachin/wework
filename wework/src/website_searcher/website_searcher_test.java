package website_searcher;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Stack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.*;

public class website_searcher_test {
	
	public static Stack<String> urls = new Stack<String>();
	public static WebDriver driver;
	
	
	@BeforeClass
	public void readURLs() throws IOException
	{  
		driver = new HtmlUnitDriver();
		driver.get("https://s3.amazonaws.com/fieldlens-public/urls.txt");
		String str = driver.getPageSource().replaceAll("\"Rank\",\"URL\",\"Linking Root Domains\",\"External Links\",\"mozRank\",\"mozTrust\"", "");
		String[] lines = str.split("\n");
		for(int i=1; i<lines.length; i++)
		{
			String[] rawURL = lines[i].split(",");
			String[] url = rawURL[1].split("\"");
			urls.push(url[1].replace('/', ' ').trim());
		}
		

		 try {
		     File file = new File("result.txt");
	
	         boolean isfile_created = file.createNewFile();
	         if(isfile_created)
	        	 System.out.println("Please find the results logged into result.txt");
	    	} catch (IOException e) {
	    		System.out.println("Exception Occurred while creating 'testfiles/result.txt' file");
		        e.printStackTrace();
		  }
	}
	
	@Test(invocationCount = 100, threadPoolSize=20, invocationTimeOut=10)
	public void finding_search() throws IOException
	{
		while(urls.isEmpty()==false)
		{

			HtmlUnitDriver driver = new HtmlUnitDriver();
			BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt", true));
			writer.newLine();
			try{
				long id = Thread.currentThread().getId();
		        System.out.println("Thread id is: " + id);
				String test_url = "http://www."+urls.pop();
				driver.get(test_url);
		        writer.append(test_url+" -> "+driver.getPageSource().contains("Search"));
				driver.quit();
				writer.close();
				
			}
			catch(EmptyStackException e)
			{
				driver.quit();
				writer.close();
			}
			finally
			{
				driver.quit();
				writer.close();
			}
			
		}
		
	 }
	
	

}
