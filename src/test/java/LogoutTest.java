import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.ProfilePage;

public class LogoutTest extends SetUpAndClose {

    @Test
    @DisplayName("Checking the exit by clicking - Выход - button in your account")
    @Description("We check that after exiting the profile, the authorization page is displayed - Вход")
    public void logoutSuccess() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAndPassword(user);
        loginPage.clickSignInButton();
        mainPage.clickAccountButton();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLogoutButton();
        Assert.assertTrue(loginPage.isLoginIndicatorDisplayed());
    }

}