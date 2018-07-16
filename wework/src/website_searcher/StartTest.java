package website_searcher;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class StartTest {

	public static void main(String[] args) {
//		StartTest st = new StartTest();
//		TestNG tng = new TestNG();
//		List<String> suites = new ArrayList<String>();
//		InputStream is = st.getClass().getResourceAsStream("/wework/testng.xml");
//		System.out.println((is.toString()));
//		suites.add(is.toString());//path to xml..
//		tng.setTestSuites(suites);
//		tng.run();
		
		
		
		TestListenerAdapter tla = new TestListenerAdapter();
		   TestNG testng = new TestNG();
		   testng.setTestClasses(new Class[] { website_searcher_test.class });
		   testng.addListener(tla);
		   testng.run();

	}

}
