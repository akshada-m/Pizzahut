package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.Before;

public class TestBase {

	
	public TestContextSetup txtSetup;
	
	public TestBase(TestContextSetup txtSetup)
	{
		this.txtSetup=txtSetup;
	}
	
	@Before
	public WebDriver Initializbrowser()
	{
		
		txtSetup.driver= new ChromeDriver();
		txtSetup.driver.manage().window().maximize();
		txtSetup.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		return txtSetup.driver;
	}
}
