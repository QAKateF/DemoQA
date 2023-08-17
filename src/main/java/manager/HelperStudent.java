package manager;

import models.Gender;
import models.Hobby;
import models.StudentDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import java.util.List;

public interface HelperStudent extends HelperBase{
    default void selectForms(){
        click(By.xpath("//div[@class='category-cards']/div[2]"));
    }
    default void selectPractiseForm(){
        click(By.xpath("//div[@class='element-list collapse show']//li[@id='item-0']"));
    }
    default void fillForm(StudentDTO studentDTO){
        type(By.id("firstName"), studentDTO.getFirstName());
        type(By.id("lastName"), studentDTO.getLastName());
        type(By.id("userEmail"), studentDTO.getEmail());
        selectGender(studentDTO.getGender());
        type(By.id("userNumber"), studentDTO.getPhone());
        type(By.id("dateOfBirthInput"), studentDTO.getBirthday());
        addSubject(studentDTO.getSubjects());
        selectHobby(studentDTO.getHobbies());
        type(By.id("currentAddress"), studentDTO.getAddress());
        typeState(studentDTO.getState());
        typeCity(studentDTO.getCity());
    }
    default void selectGender(Gender gender){
        if(gender.equals(Gender.MALE)){
            click(By.id("gender-radio-1"));
        } else if(gender.equals(Gender.FEMALE)){
            click(By.id("gender-radio-2"));
        } else click(By.id("gender-radio-3"));
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
                case SPORTS:click(By.id("hobbies-checkbox-1"));break;
                case READING:click(By.id("hobbies-checkbox-2"));break;
                case MUSIC:click(By.id("hobbies-checkbox-3"));break;
            }
        }
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
}
