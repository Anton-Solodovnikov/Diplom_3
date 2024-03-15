package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final By emailInput = By.xpath("//input[@name='name']");
    private final By passwordInput = By.xpath("//input[@name='Пароль']");
    private final By enterButton = By.xpath("//button[text()='Войти']");
    private final By registrationButton = By.xpath("//a[@href='/register']");
    private final By recoverPasswordButton = By.xpath("//a[@href='/forgot-password']");
    private final By enterRecoverButton = By.xpath("//a[contains(@href,'/login')]");

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public void setEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }
    public void clickToTheEnterButton() {
        driver.findElement(enterButton).click();
    }
    @Step("Ввод логина и пароля, нажатие на кнопку войти")
    public void userLogin(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickToTheEnterButton();
    }
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }
    public void clickRecoverPasswordButton() {
        driver.findElement(recoverPasswordButton).click();
    }
    public By getEnterButton() {
        return enterButton;
    }
    public void clickEnterButton() {
        driver.findElement(enterRecoverButton).click();
    }


}
