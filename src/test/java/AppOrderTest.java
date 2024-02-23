import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AppOrderTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }


    @AfterEach
    void tearDowd() {
        driver.quit();
        driver = null;
    }

    @Test
    void SendingFormTest() throws InterruptedException {
        driver.get("http://localhost:9999/");
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Алексей Бабкин");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79102436802");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();
        assertEquals("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text);
        Thread.sleep(5000);
    }
}