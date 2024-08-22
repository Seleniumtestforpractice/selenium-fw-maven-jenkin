package com.selenium.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.hc.client5.http.auth.StandardAuthScheme;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.api.model.Driver;
import com.selenium.pageobject.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver = null;
	public LandingPage landingPage =null;
	public WebDriver initializeDriver() throws IOException {

		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/com/selenium/resources/global-data.properties");
		properties.load(fileInputStream);
		
		//  Set Global Parameters using Maven commands and Update tests at run time
		String browserName 	= System.getProperty("browser")!=null?System.getProperty("browser"):properties.getProperty("browser");
		//String browserName = properties.getProperty("browser");
		
		if (browserName.contains("chrome")) {
			ChromeOptions chromeOptions=new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
				chromeOptions.addArguments("headless"); // Headless mode
			}
			driver = new ChromeDriver(chromeOptions);
			driver.manage().window().setSize(new Dimension(1440, 990)); // Full screen
		} else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}

	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		WebDriver driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
		
	}
	@AfterMethod(alwaysRun = true) // alwaysRun = true : If we are trying to run test cases  for specific groups then we have to declare alwaysRun=true  or that specific group name otherwise test case will be  failed . 
	public void tearDown() {
		driver.close();
	}

	
	
	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException {
		
		// read json to String
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//String to HashMap --> Jackson databind
		ObjectMapper mapper = new ObjectMapper(); // ObjectMapper provides functionality for reading and writing JSON
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() { // TypeReference --> we need to tell mapper to create a List of Hashmap type reference 
		});
		
		return data;
		
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot screenshot=(TakesScreenshot)driver;
		File source=screenshot.getScreenshotAs(OutputType.FILE);
		File destination=new File(System.getProperty("user.dir")+"/reports/"+testCaseName+".png");
		FileUtils.copyFile(source, destination);
		
		return System.getProperty("user.dir")+"/reports/"+testCaseName+".png";
		
	}
}
