package com.portfolio.product_management.e2e.steps;

import com.portfolio.product_management.e2e.pages.ProductPage;
import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductSteps {

    private WebDriver driver;
    private ProductPage productPage;

    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Given("I am on the product management page")
    public void iAmOnTheProductManagementPage() {
        driver.get("http://localhost:" + port + "/products");
        productPage = new ProductPage(driver);
    }

    @When("I fill in the product form with name {string}, price {string}, description {string} and image {string}")
    public void iFillInTheProductForm(String name, String price, String description, String imageUrl) {
        productPage.clickAddNewProduct();
        productPage.fillProductForm(name, price, description, imageUrl);
    }

    @When("I fill in the product form with price {string} and description {string}")
    public void iFillInTheProductFormWithoutName(String price, String description) {
        productPage.clickAddNewProduct();
        productPage.fillProductForm(null, price, description, null);
    }

    @And("I click the submit button")
    public void iClickTheSubmitButton() {
        productPage.clickSubmit();
    }

    @Then("the product {string} should be visible in the product list")
    public void theProductShouldBeVisibleInTheProductList(String productName) {
        Assertions.assertTrue(productPage.isProductInList(productName));
    }

    @Then("an error message {string} should be displayed")
    public void errorMessageShouldBeDisplayed(String expectedMessage) {
        String actualMessage = productPage.getErrorMessageText();
        assertEquals(expectedMessage, actualMessage);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}