package pet2you.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;

/**
 * Created by mikola on 20.07.2016.
 */
public class AccountDisabledForm extends BaseForm {

    private Button userRecovery = new Button(By.xpath("//a[contains(text(),'Восстановить страницу')]"), "User Recovery");

    public AccountDisabledForm() {
        super(By.xpath("//div[contains(text(),'Пользователь удалил свою страницу')]"), "Account Disabled Form");
    }

    public void userRecovery(){
        userRecovery.clickAndWait();
    }
}
