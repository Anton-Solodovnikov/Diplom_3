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
import pageobject.PersonalPage;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PersonalPageTest {
    private WebDriver driver;
    private ArrayList<String> tokens = new ArrayList<>();
    private LoginPage loginPage;
    private UserSteps userSteps;
    private MainPage mainPage;
    private PersonalPage personalPage;
    private Faker faker = new Faker();
    private String email = faker.random().hex(15) + "@praktikum.ru";
    private String password = faker.random().hex(15);
    private String name = faker.name().firstName();
    private UserRequest createBody = new UserRequest(email, password, name);
    WebDriverWait wait;

    @Before
    public void setUp(){
        driver = Browser.getWebDriver();
        driver.manage().window().maximize();
        driver.get(Constant.LOGIN_PAGE);
        personalPage = new PersonalPage(driver);
        loginPage = new LoginPage(driver);
        userSteps = new UserSteps();
        mainPage = new MainPage(driver);
        userSteps.registerUser(createBody);
        userSteps.checkStatusCode(200);
        tokens.add(userSteps.getToken());
        loginPage.userLogin(email, password);
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(Constant.MAIN_PAGE));
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
    @DisplayName("Открытие личного кабинета")
    public void checkPersonalPageTest(){
        mainPage.clickPersonalPageButton();
        wait.until(ExpectedConditions.urlToBe(Constant.PERSONAL_PAGE));
        assertEquals(Constant.PERSONAL_PAGE, driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Переход на конструктор при нажатии на логотип")
    public void checkLogoButtonTest(){
        mainPage.clickPersonalPageButton();
        wait.until(ExpectedConditions.urlToBe(Constant.PERSONAL_PAGE));
        personalPage.clickLogo();
        wait.until(ExpectedConditions.urlToBe(Constant.MAIN_PAGE));
        assertEquals(Constant.MAIN_PAGE,driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Переход на конструктор при нажатии на Конструктор")
    public void checkConstructorButtonTest(){
        mainPage.clickPersonalPageButton();
        wait.until(ExpectedConditions.urlToBe(Constant.PERSONAL_PAGE));
        personalPage.clickConstructorButton();
        wait.until(ExpectedConditions.urlToBe(Constant.MAIN_PAGE));
        assertEquals(Constant.MAIN_PAGE,driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Выход из аккаунта")
    public void checkLogOutTest() {
        mainPage.clickPersonalPageButton();
        wait.until(ExpectedConditions.urlToBe(Constant.PERSONAL_PAGE));
        personalPage.clickExitButton();
        wait.until(ExpectedConditions.urlToBe(Constant.LOGIN_PAGE));
        assertEquals(Constant.LOGIN_PAGE,driver.getCurrentUrl());
    }

}
