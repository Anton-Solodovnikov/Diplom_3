import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.MainPage;

import static org.junit.Assert.assertTrue;

public class MainPageTest {
    WebDriver driver;
    MainPage mainPage;

    @Before
    public void setUp() {
        driver = Browser.getWebDriver();
        mainPage = new MainPage(driver);
        driver.manage().window().maximize();
    }
    @After
    public void teardown() {
        driver.quit();
    }
    @Test
    @DisplayName("Переключение на вкладку Булки")
    public void checkBunsTubIsDisplayedTest() {
        driver.get(Constant.MAIN_PAGE);
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();
        assertTrue(mainPage.bunsHeaderIsDisplayed());
    }
    @Test
    @DisplayName("Переключение на вкладку Начинки")
    public void checkFillingsTubIsDisplayedTest() {
        driver.get(Constant.MAIN_PAGE);
        mainPage.clickFillingsTab();
        assertTrue(mainPage.fillingsHeaderIsDisplayed());
    }
    @Test
    @DisplayName("Переключение на вкладку Соуса")
    public void checkSaucesTubIsDisplayedTest() {
        driver.get(Constant.MAIN_PAGE);
        mainPage.clickSaucesTab();
        assertTrue(mainPage.saucesHeaderIsDisplayed());
    }
}
