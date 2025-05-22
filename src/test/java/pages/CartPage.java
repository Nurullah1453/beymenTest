package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;

    By productPrice = By.id("priceNew");
    By quantityIncreaseBtn = By.className("a-selectControl -small");
    By quantityText = By.cssSelector(".quantity-text");
    By removeProductBtn = By.className("icon icon-remove-new icon");
    By emptyCartMessage = By.xpath("//*[text()='Sepetinizde Ürün Bulunmamaktadır']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCartProductPrice() {
        return driver.findElement(productPrice).getText();
    }

    public void increaseQuantity() {
        driver.findElement(quantityIncreaseBtn).click();
    }

    public String getQuantity() {
        return driver.findElement(quantityText).getText();
    }

    public void removeProduct() {
        driver.findElement(removeProductBtn).click();
    }

    public boolean isCartEmpty() {
        return driver.findElement(emptyCartMessage).isDisplayed();
    }
}
