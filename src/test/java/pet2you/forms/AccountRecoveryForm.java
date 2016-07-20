package pet2you.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;

/**
 * Created by mikola on 18.07.2016.
 */
public class AccountRecoveryForm extends BaseForm {

    private Button accountRecovery = new Button(By.xpath("//div[@class=\"disabled-user-info\"]/a[contains(text(),'Восстановить страницу')]"), "Account Recovery");

    public AccountRecoveryForm(By locator, String formTitle) {
        super(locator, formTitle);
    }
}
