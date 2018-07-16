package website_searcher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
		
//		Scanner scanner = new Scanner(System.in);
//        String inputFilePath;
//        System.out.println("Please provide full input file path location: ");
//        inputFilePath=scanner.nextLine();
//        scanner.close();
//		InputStream is = getClass().getResourceAsStream("/Users/skodha/sachin/personal/wework/test.txt");
//		InputStreamReader isr = new InputStreamReader(is);
//		String line;
//		//BufferedReader reader = new BufferedReader(new FileReader("/Users/skodha/sachin/personal/wework/test.txt"));
//		//BufferedReader reader = new BufferedReader(new FileReader("testfiles/test.txt"));
//		BufferedReader reader = new BufferedReader(isr);
//		System.out.println("Got the file");
//		//BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
//		reader.readLine();
//		while((line=reader.readLine())!=null)
//		{
//			String[] tmp1 = line.split(",");
//			String[] tmp2 = tmp1[1].split("\"");
//			urls.push(tmp2[1]);
//		}
//		reader.close();
		
		//System.out.println("Stack is: "+urls.toString());
		
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
			
			//System.setProperty("webdriver.chrome.driver","/Users/skodha/sachin/softwares/Selenium/chromedriver");
//			System.setProperty("webdriver.chrome.driver","lib/chromedriver");
//			WebDriver driver = new ChromeDriver();
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
