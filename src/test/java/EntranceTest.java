import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.PasswordRecoveryPage;
import pageObject.RegistrationPage;

public class EntranceTest extends SetUpAndClose {

    @Test
    @DisplayName("Log in using the Sign in button on the main page")
    @Description("Login verification using the SignIn button on the main page with subsequent authorization")
    public void loginThroughSignInButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAndPassword(user);
        loginPage.clickSignInButton();
        Assert.assertTrue(mainPage.isMainPageOpen());
    }

    @Test
    @DisplayName("login through the Personal account button")
    @Description("Login verification using the Personal account button on the main page with subsequent authorization")
    public void loginThroughPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAndPassword(user);
        loginPage.clickSignInButton();
        Assert.assertTrue(mainPage.isMainPageOpen());
    }

    @Test
    @DisplayName("Login through the button in the registration form")
    @Description("Login verification in the registration form with subsequent authorization")
    public void loginThroughTheButtonInTheRegistrationForm() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.registerUser(user);
        registrationPage.clickSignInButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAndPassword(user);
        loginPage.clickSignInButton();
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isMainPageOpen());
    }

    @Test
    @DisplayName("Checking the login through the button in the password recovery form")
    @Description("Login verification in the password recovery form with subsequent authorization")
    public void loginFromRecoveryPage() {
        PasswordRecoveryPage passRecoveryPage = new PasswordRecoveryPage(driver);
        passRecoveryPage.open();
        passRecoveryPage.clickSignInButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAndPassword(user);
        loginPage.clickSignInButton();
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isMainPageOpen());

    }
}