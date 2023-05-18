package PageObjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.TestContextSetup;

public class DrinksPage {

	public TestContextSetup txtSetup;
	public DrinksPage(WebDriver driver,TestContextSetup txtSetup) 
	{
		this.txtSetup=txtSetup;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath = "//div[@class='md:flex md:flex-col lg:flex-row container lg:justify-start lg:pl-15']/a[5]")
	private WebElement DrinkMenu;
	
	@FindBy(xpath = "//button[@data-synth='button--pepsi-600ml--one-tap']/span")
	private WebElement AddPepsiBtn;
	
	@FindBy(css = "span.amountdue")
	private WebElement priceTXt;
	
	@FindBy(xpath = "(//button[@class='icon-close relative opacity-25 text-grey ml-10 p-10'])[1]")
	private WebElement pizzaRemoveBtn;
	

	@FindBy(xpath = "(//a[@data-synth='link--checkout'])[2]")
	private WebElement checkoutTxtBefore;
	
	@FindBy(xpath = "//button[@class='button button--primary  justify-between']")
	private WebElement checkoutTxtAfter;
	
	@FindBy(xpath = "//span[@class='ml-auto text-right']")
	private WebElement pricetagCheckout;
	
	@FindBy(xpath = "//span[text()='Checkout']")
	private WebElement checkoutBtn;
	
	@FindBy(css = "div.ReactModal__Content.ReactModal__Content--after-open.modal--small")
	private WebElement MinimumOrderPopup;
	
	@FindBy(css = "button[class='icon-remove--md absolute top-0 right-0 mr-10 mt-10']")
	private WebElement MinimumOrderPopupcloseBtn;
	
	
	
	public void clickDrinkMenu() throws InterruptedException {
		DrinkMenu.click();
		Thread.sleep(3000);
	}

	public void AddPepsi() {
		AddPepsiBtn.click();
	}
	
	public void verifyTotalPriceIsMoreThanbefore() {
		String price = priceTXt.getText().replace('₹', ' ').trim();
		double intActualtotal1 = Double.parseDouble(price);
		if (intActualtotal1 > txtSetup.intActualtotal) {
			System.out.println("total price is now more than before," + "intActualtotalPrevious=" + txtSetup.intActualtotal+ " ,intActualtotalnow=" + intActualtotal1);
			Assert.assertTrue(true);
		} else {
			System.out.println("total price is not more than before," + "intActualtotalPrevious=" + txtSetup.intActualtotal+ " ,intActualtotalnow=" + intActualtotal1);
			Assert.assertTrue(false);
		}
	}

	public void RemovePizza(WebDriverWait wait) {
		String CheckoutTextBeforeRemovingPizza = checkoutTxtBefore.getText();
		System.out.println("CheckoutTextBeforeRemovingPizza="+CheckoutTextBeforeRemovingPizza);
		System.out.println(pricetagCheckout.isDisplayed());
		pizzaRemoveBtn.click();
		wait.until(ExpectedConditions.invisibilityOf(pricetagCheckout));
		
	}

	public void VerifyPriceTagRemoved( ) {
		
		String CheckoutTextAfterRemovingPizza = checkoutTxtAfter.getText();
		
		System.out.println("CheckoutTextAfterRemovingPizza="+CheckoutTextAfterRemovingPizza);

		
	}

	public void clickOnCheckout() {
		checkoutBtn.click();
	}
	
	public void verifyMinimumOrderPopupDisplayed() throws InterruptedException {
		Assert.assertTrue(MinimumOrderPopup.isDisplayed());
		Thread.sleep(1000);
		MinimumOrderPopupcloseBtn.click();
	}
	
}
