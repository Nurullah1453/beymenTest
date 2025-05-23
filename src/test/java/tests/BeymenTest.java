package tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import utils.ExcelReader;
import utils.ReusableMethods;
import utils.TestBase;
import utils.TxtUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BeymenTest extends TestBase {

    @Test
    public void fullTest() {
        HomePage home = new HomePage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cart = new CartPage(driver);
        String fileText = "src/test/resources/urunBilgileri.txt";

        //Url'e gidelim
        driver.get("https://www.beymen.com");
        ReusableMethods.bekle(5);
        //Cıkan popopları kapatıyorum
        home.closePopupIfVisibleTwo();
        home.closePopupIfVisible();
        //Ana sayfaya başarılı bir şekilde gidildiğini kontrol ediyorum.
        Assertions.assertTrue(home.isPageOpened(), "Ana sayfa açılmadı");
        //Hazırladıgım excelin yolunu tanıtıyorum.
        ExcelReader excelReader = new ExcelReader();
        String filePath = "src/test/resources/ftest-data.xlsx";
        String searchKey1 = excelReader.getCellValue(filePath, 0, 0, 0); // şort
        String searchKey2 = excelReader.getCellValue(filePath, 0, 0, 1); // gömlek
        home.activateSearchBox();
        //Excelin ilk satırından şort kelimesini alalım
        home.searchProduct(searchKey1);
        //Arama kutusunu temizleyelim
        home.clickDelete();
        //Excelden 2. kelimeyi alalım
        home.searchProduct2(searchKey2);
        home.clickOnRandomProduct();
        ReusableMethods.bekle(5);
        productPage.writeProductInfoToTxt("src/test/resources/urunBilgileri.txt");
        productPage.addToCart();

/*


        String txtFiyat = TxtUtil.readLastPriceFromFile(fileText);
        String urunSayfaFiyat = productPage.getProductPrice();
        String sepetFiyat = cart.getCartPrice();
        assertEquals("Ürün sayfasındaki fiyat, dosyadaki fiyatla uyuşmuyor!", txtFiyat, urunSayfaFiyat);
        assertEquals("Sepetteki fiyat, dosyadaki fiyatla uyuşmuyor!", txtFiyat, sepetFiyat);

 */
        cart.add();

        assertEquals("2", cart.getQuantity(), "Adet 2 değil");

        cart.removeProduct();
        Assertions.assertTrue(cart.isCartEmpty(), "Sepet boş değil");

    }
}