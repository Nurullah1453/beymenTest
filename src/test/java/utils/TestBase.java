package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class TestBase {
    protected WebDriver driver;
    protected Logger logger = LogManager.getLogger(TestBase.class);

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        logger.info("Browser opened and maximized");
    }

    @AfterEach
    public void tearDown() {
        if(driver != null) {
            driver.quit();
            logger.info("Browser closed");
        }
    }
}
