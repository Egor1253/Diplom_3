import api.Credentials;
import api.User;
import api.UserSteps;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegistrationPage;

import java.util.concurrent.TimeUnit;

import static constants.RandomData.*;

public class RegistrationTest {
    private WebDriver driver;
    private User user;

    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe"); //для запуска тестов в Яндекс браузере нужно раскомментировать эту строку
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Checking registration with correct password")
    @Description("Registration from the Registration page with the correct password - more than 5 characters")
    public void registrationOnRegPageSuccess() {
        user = new User(RANDOM_EMAIL, RANDOM_PASS, RANDOM_NAME);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.registerUser(user);
        registrationPage.clickRegister();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAndPassword(user);
        loginPage.clickSignInButton();
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isMainPageOpen());
    }

    @Test
    @DisplayName("Checking registration with correct password")
    @Description("Registration from the Login page with the correct password - more than 5 characters")
    public void registrationOnLoginPageSuccess() {
        user = new User(RANDOM_EMAIL, RANDOM_PASS, RANDOM_NAME);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.clickRegisterButton();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registerUser(user);
        registrationPage.clickRegister();
        loginPage.enterEmailAndPassword(user);
        loginPage.clickSignInButton();
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isMainPageOpen());
    }

    @Test
    @DisplayName("Checking registration with correct password")
    @Description("Registration from the Main page with the correct password - more than 5 characters")
    public void registrationOnMainPageSuccess() {
        user = new User(RANDOM_EMAIL, RANDOM_PASS, RANDOM_NAME);
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterButton();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registerUser(user);
        registrationPage.clickRegister();
        loginPage.enterEmailAndPassword(user);
        loginPage.clickSignInButton();
        Assert.assertTrue(mainPage.isMainPageOpen());
    }

    @Test
    @DisplayName("Checking registration with wrong password")
    @Description("Checking registration with wrong password - which consists of 5 characters")
    public void checkRegistrationWithWrongPassError() {
        user = new User(RANDOM_EMAIL, RANDOM_PASS_5_CHAR, RANDOM_NAME);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.registerUser(user);
        Assert.assertTrue(registrationPage.isWrongPasswordDisplayed());
    }

    @After
    public void tearDown() {
        UserSteps userSteps = new UserSteps();
        Credentials credentials = new Credentials(user.getEmail(), user.getPassword());
        ValidatableResponse responseLogin = userSteps.login(credentials);
        String accessToken = userSteps.getAccessToken(responseLogin);
        userSteps.deletingUsersAfterTests(accessToken);
        driver.quit();
    }
}