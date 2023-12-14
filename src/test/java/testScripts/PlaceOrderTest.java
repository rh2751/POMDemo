package testScripts;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.TestBase;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductListPage;

public class PlaceOrderTest {
	
	WebDriver driver;
	LoginPage loginPage;
	ProductListPage listPage;
	CartPage cartPage;
	CheckoutPage chkoutPage;
	
	public PlaceOrderTest() {
		TestBase.initDriver();
		driver = TestBase.getDriver();
		loginPage = new LoginPage(driver);
		listPage = new ProductListPage(driver);
		cartPage = new CartPage(driver);
		chkoutPage = new CheckoutPage(driver);
	}
	
	@BeforeTest
	public void setup() {
		TestBase.openUrl("https://www.saucedemo.com/");
		loginPage.loginIntoApp("standard_user", "secret_sauce");
	}
	
	
	@Test(priority=1)
	public void validLogin() {
		Assert.assertTrue(listPage.isOnProducts());
	}
	
	@Test(priority=2)
	public void addItem() {
		listPage.addToCart();
		listPage.viewCart();
		Assert.assertTrue(cartPage.isItemAdded());
	}
	
	@Test(priority=3)
	public void chkoutTest() {
		cartPage.chkoutItems();
		chkoutPage.submitInfo("test", "user", "23456");
		Assert.assertTrue(chkoutPage.onchkoutSummary());
		chkoutPage.submitOrder();
		Assert.assertTrue(chkoutPage.orderComplete());
	}
		
	
}
