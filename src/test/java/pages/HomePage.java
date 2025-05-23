package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.ReusableMethods;

import java.util.List;
import java.util.Random;

public class HomePage {
    WebDriver driver;



    By searchBox = By.xpath("//*[@placeholder='Ürün, Marka Arayın']");
    By cookieRejectBtn = By.id("onetrust-reject-all-handler");
    By suggestionBox = By.id("o-header__search--input");
    By clearButton = By.className("o-header__search--close");
    //By products = By.className("o-header__search--close");



    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public void activateSearchBox() {
        WebElement searchBox = driver.findElement(By.xpath("//*[@placeholder='Ürün, Marka Arayın']"));
        searchBox.click();
    }


    public boolean isPageOpened() {
        return driver.getTitle().toLowerCase().contains("beymen");
    }

    public void rejectCookiesIfPresent() {
        try {
            WebElement btn = driver.findElement(cookieRejectBtn);
            if (btn.isDisplayed()) btn.click();
        } catch (Exception ignored) {}
    }


    public void searchProduct(String productName) {
        WebElement box = driver.findElement(By.id("o-searchSuggestion__input"));
        box.sendKeys(productName);
    }
    public void searchProduct2(String productName) {
        WebElement box = driver.findElement(By.id("o-searchSuggestion__input"));
        box.sendKeys(productName+Keys.ENTER);
        ReusableMethods.bekle(3);
    }

    public void clearSearchBox() {
        WebElement box = driver.findElement(searchBox);
        box.clear();
    }
    public void closePopupIfVisible() {
        try {
            WebElement closeBtn = driver.findElement(By.xpath("(//*[@class='icon icon-close'])[2]"));
            if (closeBtn.isDisplayed()) {
                closeBtn.click();
            }
        } catch (Exception e) {
            // Popup yoksa hata vermez, geçer
        }

    }
    public void closePopupIfVisibleTwo() {
        try {
            WebElement closeBtn = driver.findElement(By.xpath("(//*[text()='Tüm Çerezleri Reddet'])[1]"));
            if (closeBtn.isDisplayed()) {
                closeBtn.click();
            }
        } catch (Exception e) {
            // Popup yoksa hata vermez, geçer
        }

        //(//button[@type='button'])[1]

    }
    public void clickDelete() {
        WebElement delete = driver.findElement(By.xpath("(//button[@type='button'])[1]"));
        delete.click();
    }
    public void clickOnRandomProduct() {
        List<WebElement> productList = driver.findElements(By.xpath("//div[@class='m-productImageList__item']"));

        if (!productList.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(productList.size());
            WebElement selectedProduct = productList.get(index);

            // Scroll
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectedProduct);
            ReusableMethods.bekle(1); // scroll sonrası içerik yüklenmesini bekle

            // Click
            selectedProduct.click();
        } else {
            System.out.println("Ürün bulunamadı.");
        }
    }




}