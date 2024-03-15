import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser {
    //Запускать командой mvn clean test -Dbrowser=*  - вместо * указывается желаемый браузер chrome или yandex
    public static WebDriver getWebDriver() {
        String driver = System.getProperty("browser","chrome");
        switch (driver){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                return  new ChromeDriver();
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
                return  new ChromeDriver();
            default:
                throw new IllegalStateException("Поддерживаются два браузера: chrome и yandex");
        }
    }
}
