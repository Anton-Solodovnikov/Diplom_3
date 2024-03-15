import api.steps.UserSteps;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.LoginPage;
import pageobject.RegistrationPage;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationPageTest {
    private WebDriver driver;
    private ArrayList<String> tokens = new ArrayList<>();
    private LoginPage loginPage;
    private UserSteps userSteps;
    private RegistrationPage registrationPage;
    private Faker faker = new Faker();
    private String email = faker.random().hex(15) + "@praktikum.ru";
    private String password = faker.random().hex(15);
    private String name = faker.name().firstName();
    WebDriverWait wait;

    @Before
    public void setUp() {
        driver = Browser.getWebDriver();
        driver.manage().window().maximize();
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        userSteps = new UserSteps();
        wait = new WebDriverWait(driver, 5);
    }
    @After
    public void teardown() {
        driver.quit();
        if(tokens.isEmpty())
            return;
        for (String token: tokens) {
            userSteps.deleteUser(token);
        }
    }
    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    public void successRegistrationNewUserTest(){
        driver.get(Constant.REGISTER_PAGE);
        registrationPage.userRegistration(name, email, password);
        wait.until(ExpectedConditions.urlToBe(Constant.LOGIN_PAGE));
        userSteps.loginUser(email, password);
        tokens.add(userSteps.getToken());
        assertTrue(driver.findElement(loginPage.getEnterButton()).isDisplayed());
    }
    @Test
    @DisplayName("Регистрация с неверным паролем")
    public void registrationNewUserWithIncorrectPasswordTest(){
        String incorrectPassword = faker.random().hex(5);
        driver.get(Constant.REGISTER_PAGE);
        registrationPage.userRegistration(name, email, incorrectPassword);
        userSteps.loginUser(email, incorrectPassword);
        userSteps.checkStatusCode(401);
        assertEquals("Некорректный пароль",registrationPage.getWarningIncorrectPassword());
    }
}
