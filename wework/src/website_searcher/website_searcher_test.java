package website_searcher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Stack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.*;

public class website_searcher_test {
	
	public static Stack<String> urls = new Stack<String>();
	
	
	@BeforeClass
	public void readInputFile() throws IOException
	{  
		String line;
		BufferedReader reader = new BufferedReader(new FileReader("/Users/skodha/sachin/personal/wework/test.txt"));
		reader.readLine();
		while((line=reader.readLine())!=null)
		{
			String[] tmp1 = line.split(",");
			String[] tmp2 = tmp1[1].split("\"");
			urls.push(tmp2[1]);
		}
		reader.close();
		
		System.out.println("Stack is: "+urls.toString());
		
		 try {
		     File file = new File("./testfiles/result.txt");
	
	         boolean isfile_created = file.createNewFile();
	         if(isfile_created)
	        	 System.out.println("Please find the results logged into result.txt");
	    	} catch (IOException e) {
	    		System.out.println("Exception Occurred while creating 'testfiles/result.txt' file");
		        e.printStackTrace();
		  }
	}
	
	@Test(invocationCount = 5, threadPoolSize=5)
	public void finding_search() throws IOException
	{
		while(urls.isEmpty()==false)
		{
			
			//System.setProperty("webdriver.chrome.driver","/Users/skodha/sachin/softwares/Selenium/chromedriver");
//			System.setProperty("webdriver.chrome.driver","lib/chromedriver");
//			WebDriver driver = new ChromeDriver();
			HtmlUnitDriver driver = new HtmlUnitDriver();
			BufferedWriter writer = new BufferedWriter(new FileWriter("testfiles/result.txt", true));
			writer.newLine();
			try{
				long id = Thread.currentThread().getId();
		        System.out.println("Thread id is: " + id);
				String test_url = "http://"+urls.pop();

				driver.get(test_url);
				
				writer.append(test_url+" "+driver.getPageSource().contains("Search"));
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
