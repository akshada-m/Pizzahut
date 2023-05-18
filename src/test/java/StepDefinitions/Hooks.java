package StepDefinitions;

import io.cucumber.java.After;
import utils.TestContextSetup;

public class Hooks {
	
	public TestContextSetup txtSetup;
	
	
	public Hooks(TestContextSetup txtSetup  )
	{
		this.txtSetup=txtSetup;
	}
	

	@After
	public void CloseBrowser()
	{
		txtSetup.driver.quit();
	}
	
}
