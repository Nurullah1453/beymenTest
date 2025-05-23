package pages;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ReusableMethods;

import java.util.List;
import java.util.Random;

public class ProductPage {
    WebDriver driver;


    By productList = By.xpath("//img[contains(@class, 'o-productCard__figure--img')]");
    By productName = By.className("o-productDetail__description");
    By productPrice = By.className("m-price__new");
    By addToCartBtn = By.xpath("//*[@id='addBasket']");

    public ProductPage(WebDriver driver) {
        this.driver = driver;  // Burada kesinlikle bu şekilde olmalı!
    }


    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }

    public void addToCart() {
        // Elemente scroll yap
        WebElement addToCartElement = driver.findElement(By.xpath("//*[@id='addBasket']"));
        ReusableMethods.bekle(5);

        WebElement size= driver.findElement(By.xpath("//*[text()='M']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", size);
        size.click();
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartElement);
        ReusableMethods.bekle(5);


        ReusableMethods.bekle(5); // scroll sonrası içerik yüklenmesini bekle
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartElement);
        WebElement goBasket = driver.findElement(By.xpath("//*[@class='m-notification__button btn']"));
        goBasket.click();


        ReusableMethods.bekle(15);
    }

    public void selectProduct(){
        WebElement productSel = driver.findElement(productList);
        productSel.click();
    }


    public void writeProductInfoToTxt(String filePath) {
        String productName = getProductName();
        String productPrice = getProductPrice();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) { // true = dosyaya ekleme yap
            writer.write("Ürün Adı: " + productName);
            writer.newLine();
            writer.write("Fiyat: " + productPrice);
            writer.newLine();
            writer.write("----------------------");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
