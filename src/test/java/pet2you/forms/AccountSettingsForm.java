package pet2you.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;

/**
 * Created by mikola on 18.07.2016.
 */
public class AccountSettingsForm extends BaseForm {

    private Button privacy = new Button(By.xpath("//ul[contains(@class,\"edit-profile-tabs\")]//a[contains(text(),\"Приватность\")]"), "Privacy Button");
    private Button deleteProfile = new Button(By.id("delete_profile"), "Delete profile");
    private Button confirmDeleteProfile = new Button(By.xpath("//div[@class=\"ui-dialog-buttonset\"]/button[span[contains(text(),'Да')]]"),"Confirm Delete");

    public AccountSettingsForm() {
        super(By.xpath("//h6[contains(text(),'Редактор профиля')]"), "Account settings Form");
    }

    public void deleteProfile(){
        privacy.clickAndExplicitWait(deleteProfile.getLocator());
        deleteProfile.clickAndExplicitWait(confirmDeleteProfile.getLocator());
        confirmDeleteProfile.clickAndWait();
    }
}
