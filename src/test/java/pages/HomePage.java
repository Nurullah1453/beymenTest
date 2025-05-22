package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    WebDriver driver;

    By searchBox = By.xpath("//*[@placeholder='Ürün, Marka Arayın']");
    By cookieRejectBtn = By.id("onetrust-reject-all-handler");

    public HomePage(WebDriver driver) {
        this.driver = driver;
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
        rejectCookiesIfPresent();
        WebElement box = driver.findElement(searchBox);
        box.sendKeys(productName);
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


}
