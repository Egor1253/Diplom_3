package pageObject;

import api.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static constants.Urls.REGISTER_PAGE_URL;

public class RegistrationPage {

    private final WebDriver driver;
    private final By nameField = By.xpath(".//label[text() = 'Имя']/../input[contains(@name, 'name')]"); //локатор поля ввода имя
    private final By emailField = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]"); //локатор поля ввода email
    private final By passwordField = By.xpath(".//label[text() = 'Пароль']/../input[contains(@type, 'password')]"); //локатор поля ввода пароль
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']"); //локатор кнопки зарегистрироваться
    private final By signInButton = By.xpath(".//a[text()='Войти']"); //локтор кнопки войти внизу страницы
    private final By wrongPassword = By.xpath(".//*[text()='Некорректный пароль']"); //локатор надписи Некорректный пароль

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(REGISTER_PAGE_URL);
    }

    @Step("Open registration page")
    public void clickRegister() {
        driver.findElement(registerButton).click();
    }

    @Step("Enter registration data in the fields email, name and password")
    public void registerUser(User user) {
        driver.findElement(emailField).click();
        driver.findElement(emailField).sendKeys(user.getEmail());
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).sendKeys(user.getPassword());
        driver.findElement(nameField).click();
        driver.findElement(nameField).sendKeys(user.getName());
    }

    @Step("Receiving a boolean value when an incorrect password is entered")
    public boolean isWrongPasswordDisplayed() {
        return driver.findElement(wrongPassword).isDisplayed();
    }

    @Step("Click the login button at the bottom of the page")
    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }
}