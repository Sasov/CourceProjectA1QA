package pet2you.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

/**
 * Created by mikola on 17.07.2016.
 */
public class MainForm extends BaseForm {

    private Button login = new Button(By.className("btn-login"), "Login Button");
    private TextBox loginEmail = new TextBox(By.id("Login_email"), "User Email");
    private TextBox loginPassword = new TextBox(By.id("Login_password"), "User Password");
    private Button submitLogin = new Button(By.className("button-submit"), "Submit authorization");

    public MainForm() {
        super(By.className("btn-login"), "Pet2you main form");
    }

    public void login(String userEmail, String userPassword, String userNameAndSurname){
        enterLoginData(userEmail, userPassword);
    }

    private void enterLoginData(String userEmail, String userPassword) {
        login.clickAndExplicitWait(loginEmail.getLocator());
        loginEmail.type(userEmail);
        loginPassword.type(userPassword);
        submitLogin.clickAndWait();
    }


    public void userRecovery(String userEmail, String userPassword, String userNameAndSurname){
        enterLoginData(userEmail, userPassword);
        Label disabledUser = new Label(By.xpath("//.[contains(text(),'Страница пользователя удалена. Информация недоступна.')]"), "Disabled user notification");
        Button userRecovery = new Button(By.xpath("//a[contains(text(),'Восстановить страницу')]"), "User Recovery");
        doAssert(disabledUser.isPresent(),"Disabled user notification is present","Disabled user notification is absent");
        userRecovery.clickAndWait();
        NewsPageForm newsPage = new NewsPageForm();
        newsPage.clickProfile();
        AccountForm userAccount = new AccountForm(userNameAndSurname);
    }

}
