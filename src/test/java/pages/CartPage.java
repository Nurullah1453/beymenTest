package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
    WebDriver driver;

    By productPrice = By.id("priceNew");
    By quantityIncreaseBtn = By.cssSelector(".a-selectControl.-small");
    By quantityText = By.cssSelector(".quantity-text");
    By removeProductBtn = By.cssSelector(".icon.icon-remove-new.icon");
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
        return !driver.findElements(emptyCartMessage).isEmpty();
    }
    public String getCartPrice() {
        WebElement priceElement = driver.findElement(By.cssSelector(".m-orderSummary__item.-grandTotal .m-orderSummary__value"));
        return priceElement.getText().trim();
    }
    public void add(){
        WebElement add = driver.findElement(By.id("quantitySelect0-key-0"));
        add.click();
        add.sendKeys(Keys.ARROW_DOWN);
        add.sendKeys(Keys.ENTER);
    }




}
