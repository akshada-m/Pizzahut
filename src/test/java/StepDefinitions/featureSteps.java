package StepDefinitions;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.dealsPage;
import PageObjects.DeliveryLocationPage;
import PageObjects.DrinksPage;
import PageObjects.PizzaPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.TestBase;
import utils.TestContextSetup;

public class featureSteps {
	public WebDriver driver;
	public WebDriverWait wait;
	public DeliveryLocationPage p1;
	public dealsPage dp;
	public TestContextSetup txtSetup;
	public PizzaPage pp;
	public DrinksPage dr;
	public TestBase base;
	public Hooks hk;

	public featureSteps(TestContextSetup txtSetup) {
		this.txtSetup = txtSetup;
		hk = new Hooks(txtSetup);
		base = new TestBase(txtSetup);
	}

	
	@Given("User launch Pizzahut application with {string}")
	public void user_launch_pizzahut_application_with(String url) {
		base.Initializbrowser().get(url);
		
	}

	@When("User wait for auto location black pop up screen")
	public void user_wait_for_auto_location_black_pop_up_screen() {
		System.out.println("User wait for auto location black pop up screen");
	}

	@Then("User close the pop up screen")
	public void user_close_the_pop_up_screen() {
		System.out.println("User close the pop up screen");
	}

	@Then("User see pop up for delivery asking for enter location")
	public void user_see_pop_up_for_delivery_asking_for_enter_location() {
		p1 = new DeliveryLocationPage(txtSetup.driver);
		p1.verifyDeLlocationPopUpIsDisplayed();
	}

	@Then("User type address as {string}")
	public void user_type_address_as(String location) {
		p1.enterLocation(location);

	}

	@Then("User select first auto populate drop down option")
	public void user_select_first_auto_populate_drop_down_option() {
		p1.selectFirstOptionFromLocation();
	}

	@When("User navigate to deails page")
	public void user_navigate_to_deails_page() {
		txtSetup.wait = new WebDriverWait(txtSetup.driver, Duration.ofSeconds(8));
		txtSetup.wait.until(ExpectedConditions.urlToBe("https://www.pizzahut.co.in/order/deals/"));
		Assert.assertEquals(txtSetup.driver.getCurrentUrl(), "https://www.pizzahut.co.in/order/deals/");
	}

	@Then("User validate vegetarian radio button flag is off")
	public void user_validate_vegetarian_radio_button_flag_is_off() throws InterruptedException {
		dp = new dealsPage(txtSetup.driver);
		dp.VerifyVegetarianRadioBtnStatus();
	}

//
	@Then("User clicks on Pizzas menu bar option")
	public void user_clicks_on_pizzas_menu_bar_option() throws InterruptedException {
		pp = new PizzaPage(txtSetup.driver, txtSetup);
		pp.clickPizzaMenu();
		Thread.sleep(3000);
	}

	@When("User select add button of any pizza from Recommended")
	public void user_select_add_button_of_any_pizza_from_recommended() throws InterruptedException {
		pp.addPizza();
		Thread.sleep(2000);
	}

	@Then("User see that the pizza is getting added under Your Basket")
	public void user_see_that_the_pizza_is_getting_added_under_your_basket() {
		pp.VerifyPizzaAddedInBasket();
	}

	@Then("User validate pizza price plus Tax is checkout price")
	public void user_validate_pizza_price_plus_tax_is_checkout_price() {
		pp.verifyPriceCalculation();

	}

	@Then("User validate checkout button contains {int} Item count")
	public void user_validate_checkout_button_contains_item_count(int inta) {
		pp.verifyItemCountInCheckout(inta);
	}

	@Then("User validate checkout button contains total price count")
	public void user_validate_checkout_button_contains_total_price_count() {
		pp.verifyTotalPriceInCheckout();
	}

	@Then("User clicks on Drinks option")
	public void user_clicks_on_drinks_option() throws InterruptedException {
		dr = new DrinksPage(txtSetup.driver, txtSetup);
		dr.clickDrinkMenu();
	}

	@Then("User select Pepsi option to add into the Basket")
	public void user_select_pepsi_option_to_add_into_the_basket() {
		dr.AddPepsi();
	}

	@Then("User see {int} items are showing under checkout button")
	public void user_see_items_are_showing_under_checkout_button(int int1) {
		pp.verifyItemCountInCheckout(int1);
	}

	@Then("User see total price is now more than before")
	public void user_see_total_price_is_now_more_than_before() {
		dr.verifyTotalPriceIsMoreThanbefore();
	}

	@Then("User remove the Pizza item from Basket")
	public void user_remove_the_pizza_item_from_basket() {
		dr.RemovePizza(txtSetup.wait);
	}

	@Then("see Price tag got removed from the checkout button")
	public void see_price_tag_got_removed_from_the_checkout_button() throws InterruptedException {
		dr.VerifyPriceTagRemoved();
		Thread.sleep(2000);
	}

	@Then("User see {int} item showing in checkout button")
	public void user_see_item_showing_in_checkout_button(int int1) {
		pp.verifyItemCountInCheckout(int1);
	}

	@Then("User Clicks on Checkout button")
	public void user_clicks_on_checkout_button() {
		dr.clickOnCheckout();
	}

	@Then("User see minimum order required pop up is getting displayed")
	public void user_see_minimum_order_required_pop_up_is_getting_displayed() throws InterruptedException {
		dr.verifyMinimumOrderPopupDisplayed();
	}

	
}
