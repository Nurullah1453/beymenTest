package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import utils.ReusableMethods;

import java.util.List;

public class CartPage {

    WebDriver driver;

    // Tanımlamalar
    By productPrice = By.id("priceNew");
    By quantityDropdown = By.id("quantitySelect0-key-0");
    By quantityText = By.cssSelector(".quantity-text");
    By removeProductBtn = By.cssSelector(".icon.icon-remove-new.icon");
    By emptyCartMessage = By.xpath("//*[text()='Sepetinizde Ürün Bulunmamaktadır']");
    By cartTotalPrice = By.cssSelector(".m-orderSummary__item.-grandTotal .m-orderSummary__value");
    By emptyBasket = By.xpath("//*[text()='Sepetinizde Ürün Bulunmamaktadır']");
    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Sepetteki ürün fiyatını getir
    public String getCartProductPrice() {
        return driver.findElement(productPrice).getText().trim();
    }

    // Sepetteki toplam fiyatı getir
    public String getCartPrice() {
        return driver.findElement(cartTotalPrice).getText().trim();
    }

    // Ürün adedini dropdown'dan artır
    public boolean increaseQuantity(int quantity) {
        try {
            WebElement dropdown = driver.findElement(quantityDropdown);
            Select select = new Select(dropdown);
            List<WebElement> options = select.getOptions();

            for (WebElement option : options) {
                if (option.getAttribute("value").equals(String.valueOf(quantity))) {
                    select.selectByValue(String.valueOf(quantity));
                    return true;
                }
            }
            System.out.println("İstenilen miktarda stok bulunamadı.");
        } catch (NoSuchElementException e) {
            System.out.println("Dropdown bulunamadı: " + e.getMessage());
        }
        return false;
    }

    // Mevcut ürün adedini getir
    public String getQuantity() {
        return driver.findElement(quantityText).getText().trim();
    }

    // Ürünü sepetten kaldır
    public void removeProduct() {
        driver.findElement(removeProductBtn).click();
    }

    // Sepetin boş olup olmadığını kontrol et
    public boolean isCartEmpty() {
        return !driver.findElements(emptyCartMessage).isEmpty();
    }
    public void clickEmptyCart(){
        ReusableMethods.bekle(5);
        WebElement emptyCart = driver.findElement(By.id("removeCartItemBtn0-key-0"));
        emptyCart.click();
    }

    public boolean isCartEmptyAssert() {
        return !driver.findElements(emptyCartMessage).isEmpty();
    }


}
