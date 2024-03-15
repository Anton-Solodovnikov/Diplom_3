package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage {
    private final By personalPageButton = By.xpath("//a[contains(@href,'/account')]");
    private final By enterAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By bunsTab = By.xpath("//span[text()='Булки']");
    private final By saucesTab = By.xpath("//span[text()='Соусы']");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']");
    private final By bunsIsSelected = By.xpath(".//span[text()='Булки']/parent::div[contains(@class,'tab_tab_type_current__2BEPc')]");
    private final By saucesIsSelected = By.xpath(".//span[text()='Соусы']/parent::div[contains(@class,'tab_tab_type_current__2BEPc')]");
    private final By fillingsIsSelected = By.xpath(".//span[text()='Начинки']/parent::div[contains(@class,'tab_tab_type_current__2BEPc')]");

    private WebDriver driver;
    private WebDriverWait wait;
    public MainPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,5);
    }
    public void clickPersonalPageButton() {
        driver.findElement(personalPageButton).click();
    }
    public void clickEnterAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(enterAccountButton)).click();
    }
    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }
    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }
    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
    }
    public boolean bunsHeaderIsDisplayed() {
        return driver.findElement(bunsIsSelected).isDisplayed();
    }
    public boolean saucesHeaderIsDisplayed() {
        return driver.findElement(saucesIsSelected).isDisplayed();
    }
    public boolean fillingsHeaderIsDisplayed() {
        return driver.findElement(fillingsIsSelected).isDisplayed();
    }
}
