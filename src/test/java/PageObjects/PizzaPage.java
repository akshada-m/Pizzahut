package PageObjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.TestContextSetup;

public class PizzaPage {

	public String NameofPizzaAdded;
	public double intActualtotal;
	public int intActItemCount;
	public TestContextSetup txtSetup;
	
	public PizzaPage(WebDriver driver,TestContextSetup txtSetup) 
	{
		this.txtSetup=txtSetup;
		PageFactory.initElements(driver, this);
	}
	
	

	@FindBy(xpath = "//div[@class='md:flex md:flex-col lg:flex-row container lg:justify-start lg:pl-15']/a[2]")
	private WebElement pizzaMenu;
	
	@FindBy(xpath = "(//span[text()='Add'])[1]")
	private WebElement AddPizzaBtn;
	
	@FindBy(xpath = "(//div[@class='typography-4 list-item__name flex-1 px-10 pt-10'])[1]")
	private WebElement PizzaAdded;
	
	@FindBy(xpath = "//div[@data-synth='basket-item-type--pizza']")
	private WebElement ItemsInBasket;
	
	@FindBy(css = "span.subtotal")
	private WebElement pizzaPriceElement;
	
	@FindBy(css = "div.display-supplement-value")
	private WebElement RestaHandChargesElement;
	
	@FindBy(xpath = "(//span[@class='mr-auto']/following-sibling::span)[2]")
	private WebElement TaxElement;
	
	@FindBy(xpath = "(//span[@class='mr-auto']/following-sibling::span)[3]")
	private WebElement ActualtotalElement;
	
	@FindBy(xpath = "//span[@class='bg-green-dark pl-5 pr-5 rounded']")
	private WebElement checkoutItemCount;
	
	@FindBy(xpath = "//a[@class='button button--primary  justify-between']/span[3]/span")
	private WebElement checkoutTotalPrice;
	
	
	
	public void clickPizzaMenu() {
		pizzaMenu.click();
	}

	
	public void addPizza() {
		AddPizzaBtn.click();
		NameofPizzaAdded = PizzaAdded.getText().replace("NEW"," ").trim();
		System.out.println(" NameofPizzaAdded=" + NameofPizzaAdded);
	}

	public void VerifyPizzaAddedInBasket() {
		String NameOfPizzaInBasket = ItemsInBasket.getText();
		
		System.out.println("NameOfPizzaInBasket=" + NameOfPizzaInBasket);
		Assert.assertTrue(NameOfPizzaInBasket.contains(NameofPizzaAdded));
//NameofPizzaAdded=Schezwan MargheritaNEW    Medium Schezwan Margherita
	}

	public void verifyPriceCalculation() {
		String pizzaPrice = pizzaPriceElement.getText().replace('₹', ' ').trim();
		String RestaHandCharges = RestaHandChargesElement.getText().replace('₹', ' ').trim();
		String Tax = TaxElement.getText().replace('₹', ' ').trim();

		double intPizzaPrice = Double.parseDouble(pizzaPrice);
		double intRestaHandCharges = Double.parseDouble(RestaHandCharges);
		double intTax = Double.parseDouble(Tax);

		String Actualtotal = ActualtotalElement.getText().replace('₹', ' ').trim();
		txtSetup.intActualtotal = Double.parseDouble(Actualtotal);
		double intExpectedtotal = intPizzaPrice + intRestaHandCharges + intTax;

		if(txtSetup.intActualtotal==intExpectedtotal)
		{
			System.out.println("intActualtotal=" + txtSetup.intActualtotal + "," + "intExpectedtotal=" + intExpectedtotal);
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);

			System.out.println("intActualtotal=" + txtSetup.intActualtotal + "," + "intExpectedtotal=" + intExpectedtotal);

		}

	}

	public void verifyItemCountInCheckout(int ExpItemCount) {
		String ActItemCount = checkoutItemCount.getText().split(" ")[0];
		intActItemCount = Integer.parseInt(ActItemCount);
//		ExpItemCount= "1 item";
		System.out.println("ActItemCount=" + intActItemCount + " ExpItemCount=" + ExpItemCount);
		Assert.assertTrue(checkoutItemCount.isDisplayed());
		Assert.assertEquals(intActItemCount, ExpItemCount);
	}

	public void verifyTotalPriceInCheckout() {
		String ActPriceCheckout = checkoutTotalPrice.getText().replace('₹', ' ').trim();
		System.out.println("price=" + ActPriceCheckout);
		Assert.assertTrue(checkoutTotalPrice.isDisplayed());
	}
}
