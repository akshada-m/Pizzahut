
package PageObjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class dealsPage {

	public dealsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//input[@name='toggle-button-0'])[1]")
	private WebElement vegetarianRadioBtn;


	public void VerifyVegetarianRadioBtnStatus() {
		String flagStatus = vegetarianRadioBtn.getAttribute("value");
		Assert.assertEquals(flagStatus, "false");
		System.out.println("VerifyVegetarianRadioBtnStatus= " + flagStatus);
	}

}
