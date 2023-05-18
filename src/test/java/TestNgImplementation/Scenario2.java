package TestNgImplementation;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Scenario2 {

	
	public Sheet sh;
	public String url;
	public String location;
	public WebDriver driver;
	public WebDriverWait wait;
	public String dessertnametxt;
	public int basketItemCount ;
	
	public Double BasketTotalamount ;
	
	
	public Scenario2()
	{
		
		driver = new ChromeDriver();

	}

	@BeforeClass
	public void user_launch_pizzahut_application_with() throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\ExcelSheets\\Pizzahut.xlsx");
		Sheet sh = WorkbookFactory.create(file).getSheet("Sheet1");
		url = sh.getRow(1).getCell(0).getStringCellValue();
		location = sh.getRow(1).getCell(1).getStringCellValue();
		// driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get(url);

	}

	@Test
	public void Test1_user_wait_for_auto_location_black_pop_up_screen_and_close_it() {
		System.out.println("User wait for auto location black pop up screen");
		System.out.println("User close the pop up screen");
	}

	@Test(dependsOnMethods = {"Test1_user_wait_for_auto_location_black_pop_up_screen_and_close_it"})
	public void Test2_Set_the_user_delivery_location() {
		WebElement DeLlocationPopUp = driver.findElement(By.cssSelector("div.localisation.bg-white.p-20.rounded-b"));
		Assert.assertTrue(DeLlocationPopUp.isDisplayed());
		driver.findElement(By.cssSelector("input[placeholder='Enter your location for delivery']")).sendKeys(location);

		List<WebElement> AutoSuggestionPopup = driver
				.findElements(By.xpath("//button[contains(@id,'PlacesAutocomplete__')]"));
		wait.until(ExpectedConditions.visibilityOfAllElements(AutoSuggestionPopup));
		System.out.println("No of options in AutoSuggestion Location Popup= "+AutoSuggestionPopup.size());
		for (int i = 0; i < AutoSuggestionPopup.size();) {
			AutoSuggestionPopup.get(0).click();
			break;
		}
	}

	@Test(dependsOnMethods = {"Test2_Set_the_user_delivery_location"})
	public void Test3_user_is_now_on_deals_page_and_validate_Url_contains_deals() {
		wait.until(ExpectedConditions.urlToBe("https://www.pizzahut.co.in/order/deals/"));
		Assert.assertTrue(driver.getCurrentUrl().contains("deals"));
		

	}

	@Test(dependsOnMethods = {"Test3_user_is_now_on_deals_page_and_validate_Url_contains_deals"})
	public void Test4_Go_to_sides_and_add_any_item_that_is_below_200() throws InterruptedException {
		driver.findElement(By.xpath("(//span[text()='Desserts'])[2]")).click();
		List<WebElement> dessertList = driver.findElements(By.xpath("//a[@class='list-item list-item--dessert ']"));
		Thread.sleep(2000);
		System.out.println("No of desserts on page="+dessertList.size());
		
		for (int i = 0; i < dessertList.size(); i++) {
			int count = 1;

			String Price = dessertList.get(i).findElements(By.xpath("//span[@class='w-auto ml-3']")).get(i).getText()
					.replace("₹", " ").trim();
			dessertnametxt = dessertList.get(i).getText();

			int intprice = Integer.parseInt(Price);
			//System.out.println(intprice);
			if (intprice < 200) {

				driver.findElements(By.xpath("//span[text()='Add']")).get(i).click();
				System.out.println("1 item added and item price="+intprice);
				count++;
				if (count == 2) {
					break;
				}
			}

		}

	}

	@Test(dependsOnMethods = {"Test4_Go_to_sides_and_add_any_item_that_is_below_200"} )
	public void Test5_Validate_that_the_product_is_added_under_Basket_but_checkout_button_price_item_is_still_not_showing() {

		String dessertname = dessertnametxt.split("\\r?\\n")[0].trim();
		System.out.println("Name of item we added="+dessertname);
		int basketItemCount = driver.findElements(By.cssSelector("div.mb-20.px-20")).size();
		System.out.println("No of items in basket="+basketItemCount);
		String basketItemname = driver.findElements(By.cssSelector("div.mb-20.px-20")).get(0).getText().split("\\r?\\n")[0];
		System.out.println("Name of item in basket="+basketItemname);
		Assert.assertEquals(basketItemname, dessertname);
		String[] checkoutTxt = driver.findElement(By.xpath("//button[@data-synth='link--checkout']")).getText().split("\\r?\\n");
		System.out.println(checkoutTxt.length);
		
		if(checkoutTxt.length==2)
		{
			Assert.assertTrue(true);
			System.out.println("checkout_button_price_item_is_still_not_showing()");
		}
		else
			
		{
			Assert.assertTrue(false);
		}
		
		
		
	}
	
	@Test(dependsOnMethods = {"Test5_Validate_that_the_product_is_added_under_Basket_but_checkout_button_price_item_is_still_not_showing"})
	public void Test6_Navigate_to_the_Drinkspage_Add_any_two_drinks_so_that_total_cart_pricing_is_more_than_200() throws InterruptedException
	{
		driver.findElement(By.cssSelector("a[data-synth='link--drinks--side']")).click();
		wait.until(ExpectedConditions.urlContains("drinks"));
		String basketTotalPrice = driver.findElement(By.cssSelector("span[class='amountdue']")).getText().replace("₹", " ").trim();
		BasketTotalamount = Double.parseDouble(basketTotalPrice);
		System.out.println("BasketTotalamount="+BasketTotalamount);
		
		List<WebElement> drinklist = driver.findElements(By.xpath("//span[@data-testid='drinks']//a"));
		for(int i=0;i<=1;i++)
		{
	
			drinklist.get(i).findElements(By.xpath("//span[text()='Add']")).get(i).click();
			System.out.println("two drinks Added");
		}
		basketItemCount = driver.findElements(By.cssSelector("div.mb-20.px-20")).size();
		System.out.println("basketItemCount="+basketItemCount);
		basketTotalPrice = driver.findElement(By.cssSelector("span[class='amountdue']")).getText().replace("₹", " ").trim();
		BasketTotalamount = Double.parseDouble(basketTotalPrice);
		Assert.assertTrue(BasketTotalamount>200);
		System.out.println("BasketTotalamount="+BasketTotalamount);
		String[] checkoutTxt = driver.findElement(By.xpath("(//a[@data-synth='link--checkout'])[2]")).getText().split("\\r?\\n");
		System.out.println(checkoutTxt.length);
		System.out.println(driver.findElement(By.xpath("(//a[@data-synth='link--checkout'])[2]")).getText());
		Assert.assertEquals(checkoutTxt.length, 3);
		
		
		
	}

	@Test(dependsOnMethods = {"Test6_Navigate_to_the_Drinkspage_Add_any_two_drinks_so_that_total_cart_pricing_is_more_than_200"})
	public void Test7_Click_on_the_Checkout_button()
	{
		driver.findElement(By.xpath("//span[text()='Checkout']")).click();
		wait.until(ExpectedConditions.urlContains("checkout"));
		
	}
	
	@Test(dependsOnMethods =  {"Test7_Click_on_the_Checkout_button"})
	public void Test8_validate_that_online_Payment_Selected_ByDefault()
	{
		WebElement onlinePaymentRadioButton = driver.findElement(By.cssSelector("input#payment-method--razorpay.block.rounded.p-5.radio.border-green"));
	
		Assert.assertTrue(onlinePaymentRadioButton.isSelected());
	}
	
	@Test(dependsOnMethods = {"Test8_validate_that_online_Payment_Selected_ByDefault"})
	public void Test9_change_payment_option_to_cash()
	{//cash payment
		driver.findElement(By.cssSelector("label[class='flex border py-20 px-10 rounded mb-10 items-center']")).click();

	}
	
	@Test(dependsOnMethods = {"Test9_change_payment_option_to_cash"})
	public void Test10_Validate_that_the_I_agree_checkbox_is_checked_by_default()
	{
		
		
		Assert.assertTrue(driver.findElement(By.cssSelector("input#marketingOptIn")).isSelected());
	}
	
	@Test(dependsOnMethods = {"Test10_Validate_that_the_I_agree_checkbox_is_checked_by_default"})
	public void Test11_Enter_name_Mobile_And_Email()
	{
		driver.findElement(By.cssSelector("input[id='checkout__name']")).sendKeys("testabc Testing");
		driver.findElement(By.cssSelector("input[id='checkout__phone']")).sendKeys("1234567890");
		driver.findElement(By.cssSelector("input[id='checkout__email']")).sendKeys("testabc@test.com");
	}
	
	@Test(dependsOnMethods = {"Test11_Enter_name_Mobile_And_Email"})
	public void Test12_Click_on_the_Apply_Gift_Card_link_And_popup_Should_Appear()
	{
		driver.findElement(By.xpath("//span[text()='Apply Gift Card']")).click();
		WebElement popup=driver.findElement(By.cssSelector("div[class='sc-fzoant smfTD p-10 pt-20 bg-white m-20 rounded shadow relative']"));
		wait.until(ExpectedConditions.visibilityOf(popup));
	}
	
	@Test(dependsOnMethods = {"Test12_Click_on_the_Apply_Gift_Card_link_And_popup_Should_Appear"})
	public void Test13_Give_the_Voucher_code_as_12345_and_submit()
	{
		driver.findElement(By.cssSelector("div[class='sc-fznMnq cKbUSD']")).click();
		driver.findElement(By.cssSelector("div[class='sc-fznMnq gdZumo']")).click();
		driver.findElement(By.xpath("//input[@data-testid='voucher-input']")).sendKeys("12345");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("div.sc-fznJRM.ciBEcK")).isDisplayed());
		String Expectedwarning = "Sorry, we don’t currently support that voucher code.";
		String ActWarning = driver.findElement(By.cssSelector("div.sc-fznJRM.ciBEcK")).getText();
		Assert.assertEquals(ActWarning, Expectedwarning);
		driver.findElement(By.cssSelector("button.icon-remove-3.absolute.top-0.right-0.mr-20.mt-20")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Your Basket']"))));
		
	}
	
	
	

	@AfterClass
	public void closebrowser() {
		driver.quit();
	}
}
