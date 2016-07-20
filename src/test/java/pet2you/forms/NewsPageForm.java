package pet2you.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;

/**
 * Created by mikola on 18.07.2016.
 */
public class NewsPageForm extends BaseForm {

    private Button profile = new Button(By.xpath("//div[@id=\"left-menu-container\"]/a[contains(text(),\"Профиль\")]"), "Profile Button");

    public NewsPageForm() {
        super(By.xpath("//h6[contains(text(),'Новости')]"), "News Page Form");
    }

    public void clickProfile(){
        profile.clickAndWait();
    }
}
