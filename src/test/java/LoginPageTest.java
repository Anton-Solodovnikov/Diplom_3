import api.json.UserRequest;
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
import pageobject.MainPage;
import pageobject.RegistrationPage;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class LoginPageTest {
   private WebDriver driver;
   private ArrayList<String> tokens = new ArrayList<>();
   private LoginPage loginPage;
   private MainPage mainPage;
   private UserSteps userSteps;
   private RegistrationPage registrationPage;
   private Faker faker = new Faker();
   private String email = faker.random().hex(15) + "@praktikum.ru";
   private String password = faker.random().hex(15);
   private String name = faker.name().firstName();
   private UserRequest createBody = new UserRequest(email, password, name);
   WebDriverWait wait;

   @Before
   public void setUp() {
        driver = Browser.getWebDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        userSteps = new UserSteps();
        registrationPage = new RegistrationPage(driver);
        userSteps.registerUser(createBody);
        userSteps.checkStatusCode(200);
        tokens.add(userSteps.getToken());
        wait = new WebDriverWait(driver, 5);
   }
    @After
    public void teardown() {
        if(tokens.isEmpty())
            return;
        for (String token: tokens) {
            userSteps.deleteUser(token);
        }
        driver.quit();
    }

    @Test
    @DisplayName("Авторизация через кнопку Войти в аккаунт")
    public void loginFromEnterAccountButtonTest() {
       driver.get(Constant.MAIN_PAGE);
       mainPage.clickEnterAccountButton();
       loginPage.userLogin(email, password);
       wait.until(ExpectedConditions.urlToBe(Constant.MAIN_PAGE));
       assertEquals(Constant.MAIN_PAGE, driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Авторизация через ссылку Войти на странице регистрации")
    public void loginFromRegistrationPageTest(){
       driver.get(Constant.LOGIN_PAGE);
       loginPage.clickRegistrationButton();
       registrationPage.clickEnterButton();
       loginPage.userLogin(email, password);
       wait.until(ExpectedConditions.urlToBe(Constant.MAIN_PAGE));
       assertEquals(Constant.MAIN_PAGE, driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Авторизация по ссылке Войти на странице восстановления пароля")
    public void loginFromRecoveryPageTest(){
       driver.get(Constant.LOGIN_PAGE);
       loginPage.clickRecoverPasswordButton();
       loginPage.clickEnterButton();
       loginPage.userLogin(email, password);
       wait.until(ExpectedConditions.urlToBe(Constant.MAIN_PAGE));
       assertEquals(Constant.MAIN_PAGE, driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Авторизация через кнопку в Личном кабинете")
    public void loginFromPersonalPageTest(){
       driver.get(Constant.MAIN_PAGE);
       mainPage.clickPersonalPageButton();
       loginPage.userLogin(email, password);
       wait.until(ExpectedConditions.urlToBe(Constant.MAIN_PAGE));
       assertEquals(Constant.MAIN_PAGE, driver.getCurrentUrl());
    }


}
