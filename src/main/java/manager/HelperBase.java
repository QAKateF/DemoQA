package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface HelperBase extends AppManager{
    default void click(By locator){
        WEB_DRIVER.findElement(locator).click();
    }
    default void type(By locator, String text){
        WebElement element = WEB_DRIVER.findElement(locator);
        element.click();
        element.clear();
        element.sendKeys(text);
    }
    default void pause(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
