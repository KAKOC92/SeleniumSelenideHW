import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class AppOrderTestNegativeTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        //System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
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
    void SendingFormTestWithInvalidName() throws InterruptedException {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Alexey Babkin");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79102436802");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub" )).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text);
        Thread.sleep(5000);
    }

    @Test
    void SendingFormTestWithoutName() throws InterruptedException {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79102436802");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub" )).getText();
        assertEquals("Поле обязательно для заполнения", text);
        Thread.sleep(5000);
    }

    @Test
    void SendingFormTestWithOutPhoneNumber() throws InterruptedException {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Алексей Бабкин");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub" )).getText();
        assertEquals("Поле обязательно для заполнения", text);
        Thread.sleep(5000);
    }

    @Test
    void SendingFormTestWithInvalidPhoneNumber() throws InterruptedException {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Алексей Бабкин");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("Phone number");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub" )).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text);
        Thread.sleep(5000);
    }

    @Test
    void SendingFormTestWithOutCheckBox() throws InterruptedException {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Алексей Бабкин");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79102436802");
        driver.findElement(By.cssSelector("button.button")).click();
        assertTrue (driver.findElement(By.cssSelector("[data-test-id='agreement'].input_invalid" )).isDisplayed());
        Thread.sleep(5000);
    }
}
