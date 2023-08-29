package manager;

import models.Gender;
import models.Hobby;
import models.StudentDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.Month;
import java.util.List;

public interface HelperStudent extends HelperBase{
    default void selectForms(){
        if(isElementPresent(By.id("adplus-anchor"))){
            hideAds();
        }
        click(By.xpath("//div[@class='category-cards']/div[2]"));
    }
    default void selectPractiseForm(){
        click(By.xpath("//span[.='Practice Form']"));
    }
    default void fillForm(StudentDTO studentDTO){
        type(By.id("firstName"), studentDTO.getFirstName());
        type(By.id("lastName"), studentDTO.getLastName());
        type(By.id("userEmail"), studentDTO.getEmail());
        selectGender(studentDTO.getGender());
        type(By.id("userNumber"), studentDTO.getPhone());
        //typeBDay(studentDTO.getBirthday());
        typeBDaySelect(studentDTO.getBirthday());
        addSubject(studentDTO.getSubjects());
        selectHobby(studentDTO.getHobbies());
        uplodePicture();
        type(By.id("currentAddress"), studentDTO.getAddress());
        typeState(studentDTO.getState());
        typeCity(studentDTO.getCity());
    }

    default void typeBDay(String birthday) {
        WebElement date = WEB_DRIVER.findElement(By.id("dateOfBirthInput"));
        date.click();
        String os = System.getProperty("os.name");
        System.out.println(os);
        if(os.startsWith("Windows")){
            date.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        } else {
            date.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        }
        date.sendKeys(birthday);
        date.sendKeys(Keys.ENTER);
    }

    default void typeBDaySelect(String birthday){
        String[] date = birthday.split(" ");
        click(By.id("dateOfBirthInput"));
        new Select(WEB_DRIVER.findElement(By.className("react-datepicker__month-select"))).selectByValue("" + (Integer.parseInt(date[0]) - 1));
        new Select(WEB_DRIVER.findElement(By.className("react-datepicker__year-select"))).selectByValue(date[2]);
        String day = String.format("//div[.='%s']", date[1]);
        List<WebElement> days = WEB_DRIVER.findElements(By.xpath(day));
        if(days.size() > 1 && Integer.parseInt(date[1]) > 15){
            days.get(1).click();
        } else {
            days.get(0).click();
        }

    }

    default void selectGender(Gender gender){
        if(gender.equals(Gender.MALE)){
            click(By.cssSelector("label[for='gender-radio-1']"));
        } else if(gender.equals(Gender.FEMALE)){
            click(By.cssSelector("label[for='gender-radio-2']"));
        } else click(By.cssSelector("label[for='gender-radio-3']"));
    }
    default void addSubject(String subjects) {
        String[] split = subjects.split(",");
        String locator = "subjectsInput";
        click(By.id(locator));
        for (String s : split) {
            WEB_DRIVER.findElement(By.id(locator)).sendKeys(s);
            WEB_DRIVER.findElement(By.id(locator)).sendKeys(Keys.ENTER);
        }
    }
    default void selectHobby(List<Hobby> hobbies){
        for(Hobby hobby : hobbies){
            switch (hobby){
                case SPORTS:click(By.cssSelector("label[for='hobbies-checkbox-1']"));break;
                case READING:click(By.cssSelector("label[for='hobbies-checkbox-2']"));break;
                case MUSIC:click(By.cssSelector("label[for='hobbies-checkbox-3']"));break;
            }
        }
    }

    default void uplodePicture(){
        WEB_DRIVER.findElement(By.id("uploadPicture")).sendKeys("C:\\Users\\kiril\\Desktop\\Studies\\GitHub\\DemoQA\\student.jpeg");
    }

    default void typeState(String state) {
        WEB_DRIVER.findElement(By.id("react-select-3-input")).sendKeys(state);
        WEB_DRIVER.findElement(By.id("react-select-3-input")).sendKeys(Keys.ENTER);
    }

    default void typeCity(String city) {
        WEB_DRIVER.findElement(By.id("react-select-4-input")).sendKeys(city);
        WEB_DRIVER.findElement(By.id("react-select-4-input")).sendKeys(Keys.ENTER);
    }
    default void submit(){
        click(By.id("submit"));
    }
    default void selectMonth(List<Month> months){
        click(By.xpath("//select[@class='react-datepicker__month-select']"));
        for(Month month : months){
            switch(month) {
                case JANUARY:click(By.xpath("//option[@value='0']"));break;
                case FEBRUARY:click(By.xpath("//option[@value='1']"));break;
                case MARCH:click(By.xpath("//option[@value='2']"));break;
                case APRIL:click(By.xpath("//option[@value='3']"));break;
                case MAY:click(By.xpath("//option[@value='4']"));break;
                case JUNE:click(By.xpath("//option[@value='5']"));break;
                case JULY:click(By.xpath("//option[@value='6']"));break;
                case AUGUST:click(By.xpath("//option[@value='7']"));break;
                case SEPTEMBER:click(By.xpath("//option[@value='8']"));break;
                case OCTOBER:click(By.xpath("//option[@value='9']"));break;
                case NOVEMBER:click(By.xpath("//option[@value='10']"));break;
                case DECEMBER:click(By.xpath("//option[@value='11']"));break;
            }
        }
    }
}
