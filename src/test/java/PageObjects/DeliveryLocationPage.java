package PageObjects;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeliveryLocationPage {
	
	public DeliveryLocationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(css="div[class='localisation bg-white p-20 rounded-b']") private WebElement deliveryPopup;
	@FindBy(css="input[placeholder='Enter your location for delivery']") private WebElement locationSearchBox;
	@FindBy(xpath="//button[contains(@id,'PlacesAutocomplete__')]") private List<WebElement> AutoSuggOptionsForLocation;
	
	
	
	
	public void verifyDeLlocationPopUpIsDisplayed()
	{
		
		System.out.println(deliveryPopup.isDisplayed());
		Assert.assertTrue(deliveryPopup.isDisplayed());
	}
	
	public void enterLocation(String location)
	{
		locationSearchBox.sendKeys(location);
		System.out.println("AutoSuggOptionsForLocation= "+AutoSuggOptionsForLocation.size());
		
		
		
	}
	
	public void selectFirstOptionFromLocation()
	{
		AutoSuggOptionsForLocation.get(0).click();
	}
	
}
