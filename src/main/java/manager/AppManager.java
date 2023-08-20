package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public interface AppManager {
    WebDriver WEB_DRIVER = new ChromeDriver();

    default void init(){
        WEB_DRIVER.navigate().to("https://demoqa.com/");
       // WEB_DRIVER.manage().window().maximize();
        WEB_DRIVER.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @AfterSuite
    default void tearDown(){
        //  WEB_DRIVER.quit();
    }



}
