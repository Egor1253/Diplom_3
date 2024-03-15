import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObject.MainPage;

import java.util.concurrent.TimeUnit;

public class ConstructorTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driver = getDriver("chrome"); // раскомментировать для запуска тестов в chrome.
        //driver = getDriver("firefox"); // раскомментировать для запуска тестов в firefox.
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Transitions to the section Булки")
    @Description("Checking if the button is selected by getting the text")
    public void menuBunIsActiveByClick() throws InterruptedException {
        mainPage = new MainPage(driver);
        mainPage.open();
        Thread.sleep(3000);
        mainPage.clickFillingsButton();
        mainPage.clickBunsButton();
        Assert.assertEquals("Булки", mainPage.getTextFromSelectedMenu());
    }

    @Test
    @DisplayName("Transitions to the section Соусы")
    @Description("Checking if the button is selected by getting the text")
    public void menuSaucesIsActiveByClick() throws InterruptedException {
        mainPage = new MainPage(driver);
        mainPage.open();
        Thread.sleep(3000);
        mainPage.clickFillingsButton();
        mainPage.clickSaucesButton();
        Assert.assertEquals("Соусы", mainPage.getTextFromSelectedMenu());
    }

    @Test
    @DisplayName("Transitions to the section Начинки")
    @Description("Checking if the button is selected by getting the text")
    public void menuFillingIsActiveByClick() throws InterruptedException {
        mainPage = new MainPage(driver);
        mainPage.open();
        Thread.sleep(3000);
        mainPage.clickSaucesButton();
        mainPage.clickFillingsButton();
        Assert.assertEquals("Начинки", mainPage.getTextFromSelectedMenu());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private static WebDriver getDriver(String browser) {
        switch (browser) {
            case "chrome":
                return new ChromeDriver();
            case "firefox":
                return new FirefoxDriver();
            default: throw new RuntimeException("Неверное название браузера");
        }
    }
}

