package com.portfolio.product_management.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Mapeamento dos elementos HTML
    private final By addProductButton = By.id("btn-add-product");
    private final By nameInput = By.id("name");
    private final By priceInput = By.id("price");
    private final By descriptionInput = By.id("description");
    private final By imageUrlInput = By.id("imageUrl");
    private final By saveButton = By.id("btn-save");
    private final By productsTable = By.id("products-table");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        // Timeout curto de 3 segundos para evitar travamentos em retries
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void clickAddNewProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(addProductButton)).click();
    }

    public void fillProductForm(String name, String price, String description, String imageUrl) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(name);
        driver.findElement(priceInput).sendKeys(price);
        driver.findElement(descriptionInput).sendKeys(description);
        driver.findElement(imageUrlInput).sendKeys(imageUrl);
    }

    public void clickSubmit() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        
        try {
            button.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }
    }

    public boolean isProductInList(String productName) {
        // Validação direta via texto da tabela sem buscas redundantes no DOM
        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(productsTable));
        return table.getText().contains(productName);
    }
}