package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class ProductPage {
    WebDriver driver;

    By productList = By.className("o-productCard__figure--img lazyload b-loaded"); // örnek selector
    By productName = By.className("o-productDetail__brandLink");
    By productPrice = By.className("m-price__campaignPrice");

    By addToCartBtn = By.className("m-addBasketFavorite__basket btn");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectRandomProduct() {
        List<WebElement> products = driver.findElements(productList);
        if (products.size() > 0) { // liste boş değilse devam et
            new Random().ints(1, 0, products.size())
                    .findFirst()
                    .ifPresent(index -> products.get(index).click());
        } else {
            System.out.println("Ürün bulunamadı, seçim yapılmadı.");
        }
    }


    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }

    public void addToCart() {
        driver.findElement(addToCartBtn).click();
    }
}
