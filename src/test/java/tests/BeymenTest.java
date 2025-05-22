package tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import utils.ExcelUtil;
import utils.ReusableMethods;
import utils.TestBase;
import utils.TxtUtil;

public class BeymenTest extends TestBase {

    @Test
    public void fullTest() {
        HomePage home = new HomePage(driver);

        //Url'e gidelim
        driver.get("https://www.beymen.com");
        ReusableMethods.bekle(5);

        //Cıkan popopları kapatıyorum
        home.closePopupIfVisibleTwo();
        home.closePopupIfVisible();

        //Ana sayfaya başarılı bir şekilde gidildiğini kontrol ediyorum.
        Assertions.assertTrue(home.isPageOpened(), "Ana sayfa açılmadı");

        //Hazırladıgım excelin yolunu tanıtıyorum.
        ExcelUtil excel = new ExcelUtil("src/test/resources/testdata.xlsx");
        String firstProduct = excel.getData(0, 0, 0);  // 1.satır, 1.sütun = şort
        String secondProduct = excel.getData(0, 0, 1); // 1.satır, 2.sütun = gömlek
        ReusableMethods.bekle(5);
        //Excelin ilk satırından şort kelimesini alalım
        home.searchProduct(firstProduct);
        ReusableMethods.bekle(5);
        //Arama kutusunu temizleyelim
        home.clickDelete();
        ReusableMethods.bekle(3);
        //Excelden 2. kelimeyi alalım
        home.searchProduct(secondProduct);

        ProductPage productPage = new ProductPage(driver);
        productPage.selectRandomProduct();

        String productName = productPage.getProductName();
        String productPrice = productPage.getProductPrice();

        TxtUtil.writeToFile("src/resources/productInfo.txt",
                "Ürün Adı: " + productName + " - Fiyatı: " + productPrice);

        productPage.addToCart();

        CartPage cart = new CartPage(driver);

        Assertions.assertEquals(productPrice, cart.getCartProductPrice(), "Fiyatlar eşleşmiyor");

        cart.increaseQuantity();
        Assertions.assertEquals("2", cart.getQuantity(), "Adet 2 değil");

        cart.removeProduct();
        Assertions.assertTrue(cart.isCartEmpty(), "Sepet boş değil");

        excel.closeWorkbook();
    }
}
