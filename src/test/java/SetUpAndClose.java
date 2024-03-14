import api.User;
import api.UserSteps;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static constants.RandomData.*;
import static java.sql.DriverManager.getDriver;

public class SetUpAndClose {

    public WebDriver driver;
    public UserSteps userSteps;
    public String accessToken;
    public User user;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        //driver = getDriver("firefox"); // раскомментировать для запуска тестов в firefox.
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        user = new User(RANDOM_EMAIL, RANDOM_PASS, RANDOM_NAME);
        userSteps = new UserSteps();
        ValidatableResponse validatableResponse = userSteps.createUser(user);
        accessToken = userSteps.getAccessToken(validatableResponse);
    }

    @After
    public void close() {
        userSteps.deletingUsersAfterTests(accessToken);
        driver.quit();
    }

    private static WebDriver getDriver(String browser) {
        switch (browser) {
            case "chrome":
                return new ChromeDriver();
            case "firefox":
                return new FirefoxDriver();
            default:
                throw new RuntimeException("Неверное название браузера");
        }
    }

}