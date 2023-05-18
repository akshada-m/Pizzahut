//package StepDefinitions;
//
//import java.time.Duration;
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.List;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.StaleElementReferenceException;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//public class stepDef {
//
//	public WebDriver driver;
//	public WebDriverWait wait;
//	public String PizzaName;
//	public double intActualtotal;
//	public int ActualNoOfProdutsInbasket;
//
//	@Given("User launch Pizzahut application with {string}")
//	public void user_launch_pizzahut_application_with(String URL) {
//		// System.setProperty("webdriver.chrome.driver","C:/Users/USER/Downloads/chromedriver112.exe");
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//		driver.get(URL);
//	}
//
//	@When("User wait for auto location black pop up screen")
//	public void user_wait_for_auto_location_black_pop_up_screen() {
//
//	}
//
//	@Then("User close the pop up screen")
//	public void user_close_the_pop_up_screen() {
//
//	}
//
//	@Then("User see pop up for delivery asking for enter location")
//	public void user_see_pop_up_for_delivery_asking_for_enter_location() {
//		WebElement DeLlocationPopUp = driver.findElement(By.cssSelector("div.localisation.bg-white.p-20.rounded-b"));
//
//		Assert.assertTrue(DeLlocationPopUp.isDisplayed());
//	}
//
//	@Then("User type address as {string}")
//	public void user_type_address_as(String location) {
//		driver.findElement(By.cssSelector("input[placeholder='Enter your location for delivery']")).sendKeys(location);
//	}
//
//	@Then("User select first auto populate drop down option")
//	public void user_select_first_auto_populate_drop_down_option() throws InterruptedException {
//		Thread.sleep(3000);
//
//		List<WebElement> AutoSuggestionPopup = driver
//				.findElements(By.xpath("//button[contains(@id,'PlacesAutocomplete__')]"));
//		System.out.println(AutoSuggestionPopup.size());
//		for (int i = 0; i < AutoSuggestionPopup.size(); i++) {
//			AutoSuggestionPopup.get(0).click();
//			break;
//		}
//
//	}
//
//	@When("User navigate to deails page")
//	public void user_navigate_to_deails_page() {
//		wait = new WebDriverWait(driver, Duration.ofSeconds(8));
//		wait.until(ExpectedConditions.urlMatches("https://www.pizzahut.co.in/order/deals/"));
//	}
//
//	@Then("User validate vegetarian radio button flag is off")
//	public void user_validate_vegetarian_radio_button_flag_is_off() {
//		String val = driver.findElement(By.xpath("(//input[@name='toggle-button-0'])[1]")).getAttribute("value");
//
//		System.out.println(val + "=vegetarian radio button flag status");
//		Assert.assertEquals(val, "false");
//	}
//
//	@Then("User clicks on Pizzas menu bar option")
//	public void user_clicks_on_pizzas_menu_bar_option() {
//		driver.findElement(
//				By.xpath("//div[@class='md:flex md:flex-col lg:flex-row container lg:justify-start lg:pl-15']/a[2]"))
//				.click();
//	}
//
//	@When("User select add button of any pizza from Recommended")
//	public void user_select_add_button_of_any_pizza_from_recommended() throws InterruptedException {
//		PizzaName = "Schezwan Margherita";
//		System.out.println("pizzanmeToBeAdded=" + PizzaName);
//
//		List<WebElement> PizaaList = driver.findElements(By.xpath("//a[@class='list-item list-item--pizza ']"));
//		wait.until(ExpectedConditions.visibilityOfAllElements(PizaaList));
//		System.out.println("NoOfPizzas=" + PizaaList.size());
//
//		Thread.sleep(3000);
//		for (int i = 0; i < PizaaList.size(); i++) {
//			int count = 0;
//			if (PizaaList.get(i).getText().contains(PizzaName)) {
//				driver.findElements(By.xpath("//span[text()='Add']")).get(i).click();
//				System.out.println("1 PizzaAdded");
//				count++;
//				if (count == 1) {
//					break;
//				}
//			}
//
//		}
//	}
//
//	@Then("User see that the pizza is getting added under Your Basket")
//	public void user_see_that_the_pizza_is_getting_added_under_your_basket() {
//		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='basket']")).isDisplayed());
//		WebElement ItemInBasket = driver.findElement(By.xpath("//div[@data-synth='basket-item-type--pizza']"));
//		Assert.assertTrue(ItemInBasket.getText().contains(PizzaName));
//		String CheckoutText = driver.findElement(By.xpath("//a[@class='button button--primary  justify-between']"))
//				.getText();
//		System.out.println("these options displayed in checkout   -"+CheckoutText);
//
//		;
//
//	}
//
//	@Then("User validate pizza price plus Tax is checkout price")
//	public void user_validate_pizza_price_plus_tax_is_checkout_price() {
//		String pizzaPrice = driver.findElement(By.cssSelector("span.subtotal")).getText().replace('₹', ' ').trim();
//		String RestaHandCharges = driver.findElement(By.cssSelector("div.display-supplement-value")).getText()
//				.replace('₹', ' ').trim();
//		;
//		String Tax = driver.findElement(By.xpath("(//span[@class='mr-auto']/following-sibling::span)[2]")).getText()
//				.replace('₹', ' ').trim();
//
//		double intPizzaPrice = Double.parseDouble(pizzaPrice);
//		double intRestaHandCharges = Double.parseDouble(RestaHandCharges);
//		double intTax = Double.parseDouble(Tax);
//
//		String Actualtotal = driver.findElement(By.xpath("(//span[@class='mr-auto']/following-sibling::span)[3]"))
//				.getText().replace('₹', ' ').trim();
//		intActualtotal = Double.parseDouble(Actualtotal);
//		double intExpectedtotal = intPizzaPrice + intRestaHandCharges + intTax;
//
//		Assert.assertEquals(intActualtotal, intExpectedtotal);
//		System.out.println("intActualtotal=" + intActualtotal + "," + "intExpectedtotal=" + intExpectedtotal);
//
//	}
//
//	@Then("User validate checkout button contains Item count")
//	public void user_validate_checkout_button_contains_item_count() {
//		WebElement itemCount = driver.findElement(By.xpath(
//				"//a[@class='button button--primary  justify-between']//span[@class='bg-green-dark pl-5 pr-5 rounded']"));
//		Assert.assertTrue(itemCount.isDisplayed());
//		Assert.assertEquals(itemCount.getText(), "1 item");
//
//	}
//
//	@Then("User validate checkout button contains total price count")
//	public void user_validate_checkout_button_contains_total_price_count() {
//		WebElement totalPrice = driver
//				.findElement(By.xpath("//a[@class='button button--primary  justify-between']/span[3]/span"));
//		System.out.println("price=" + totalPrice.getText().replace('₹', ' ').trim());
//		Assert.assertTrue(totalPrice.isDisplayed());
//	}
//
//	@Then("User clicks on Drinks option")
//	public void user_clicks_on_drinks_option() throws InterruptedException {
//
//		driver.findElement(
//				By.xpath("//div[@class='md:flex md:flex-col lg:flex-row container lg:justify-start lg:pl-15']/a[5]"))
//				.click();
//		Thread.sleep(3000);
//
//	}
//
//	@Then("User select Pepsi option to add into the Basket")
//	public void user_select_pepsi_option_to_add_into_the_basket() {
//		driver.findElement(By.xpath("//button[@data-synth='button--pepsi-600ml--one-tap']/span")).click();
//		System.out.println("Pepsi Added");
//	}
//
//	@Then("User see {int} items are showing under checkout button")
//	public void user_see_items_are_showing_under_checkout_button(Integer ExpectedNoOfProdutsInbasket) {
//		ActualNoOfProdutsInbasket = driver.findElements(By.cssSelector("div[data-testid='basket-item-product']"))
//				.size();
//		Assert.assertEquals(ActualNoOfProdutsInbasket, ExpectedNoOfProdutsInbasket);
//		System.out.println("ActualNoOfProdutsInbasket=" + ActualNoOfProdutsInbasket);
//		System.out.println("ExpectedNoOfProdutsInbasket=" + ExpectedNoOfProdutsInbasket);
//	}
//
//	@Then("User see total price is now more than before")
//	public void user_see_total_price_is_now_more_than_before() {
//		String price = driver.findElement(By.cssSelector("span.amountdue")).getText().replace('₹', ' ').trim();
//		double intActualtotal1 = Double.parseDouble(price);
//		if (intActualtotal1 > intActualtotal) {
//			System.out.println("total price is now more than before," + "PreviousPrice=" + intActualtotal + "Pricenow="
//					+ intActualtotal1);
//			Assert.assertTrue(true);
//		} else {
//			Assert.assertFalse(false);
//		}
//
//	}
//
//	@Then("User remove the Pizza item from Basket")
//	public void user_remove_the_pizza_item_from_basket() {
//		driver.findElement(By.xpath("//button[@class='icon-close relative opacity-25 text-grey ml-10 p-10']")).click();
//		System.out.println("Pizza Removed");
//	}
//
//	@Then("see Price tag got removed from the checkout button")
//	public void see_price_tag_got_removed_from_the_checkout_button() throws InterruptedException {
//
//		String CheckoutText = driver.findElement(By.xpath("//button[@class='button button--primary  justify-between']"))
//				.getText();
//		if (CheckoutText.equals("1 item\r\n" + "Checkout")) {
//			Assert.assertTrue(true);
//		} else {
//			Assert.assertFalse(false, "The price tag not remobved");
//		}
//
//		System.out.println("these options displayed in checkout after removing pizza" + "     " + CheckoutText);
//
//	}
//
//	@Then("User see {int} item showing in checkout button")
//	public void user_see_item_showing_in_checkout_button(Integer ExpectedNoOfProdutsInbasket) {
//		ActualNoOfProdutsInbasket = driver.findElements(By.cssSelector("div[data-testid='basket-item-product']"))
//				.size();
//		Assert.assertEquals(ActualNoOfProdutsInbasket, ExpectedNoOfProdutsInbasket);
//		System.out.println("ActualNoOfProdutsInbasket=" + ActualNoOfProdutsInbasket);
//		System.out.println("ExpectedNoOfProdutsInbasket=" + ExpectedNoOfProdutsInbasket);
//	}
//
//	@Then("User Clicks on Checkout button")
//	public void user_clicks_on_checkout_button() {
//		WebElement checkOutButton = driver.findElement(By.xpath("//span[text()='Checkout']"));
//		checkOutButton.click();
//
//	}
//
//	@Then("User see minimum order required pop up is getting displayed")
//	public void user_see_minimum_order_required_pop_up_is_getting_displayed() {
//		WebElement MinimumorderPopup = driver
//				.findElement(By.cssSelector("div.ReactModal__Content.ReactModal__Content--after-open.modal--small"));
//		Assert.assertTrue(MinimumorderPopup.isDisplayed(), "Minimum orderpopup not displayed");
//		driver.findElement(By.cssSelector("button[class='icon-remove--md absolute top-0 right-0 mr-10 mt-10']"))
//				.click();
//		System.out.println("popup closed");
//
//	}
//}
