package pet2you.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;

/**
 * Created by mikola on 17.07.2016.
 */
public class AccountForm extends BaseForm {

    private Button settings = new Button(By.xpath("//div[@id=\"left-menu-container\"]/a[contains(text(),\"Настройки\")]"), "Settings Button");
    private Button friends = new Button(By.xpath("//div[@id=\"left-menu-container\"]/a[contains(text(),\"Друзья\")]"), "Friends Button");
    private Button messages = new Button(By.xpath("//div[@id=\"left-menu-container\"]/a[contains(text(),\"Сообщения\")]"), "Massages Button");
    private Button beforeLogout = new Button(By.id("settings"), "Call to header settings container");
    private Button logout = new Button(By.xpath("//div[@id=\"settings-hidden-container\"]//a[contains(text(),'Выход')]"), "Logout Button");
    private Label newFriendsIndicator = new Label(By.id("MymainmenuWidget_friends_count"), "New friends indicator");
    private Label newMessageIndicator = new Label(By.id("MymainmenuWidget_messages_count"), "New message indicator");


    public AccountForm(String userNameAndSurname) {
        super(By.xpath("//a[@class=\"name-area\" and contains(text(),'" + userNameAndSurname + "')]"), "Account form by "+userNameAndSurname);
    }

    public AccountForm(){
        super(By.xpath("//img[contains(@class,'AvatarOnMain')]"),"Return to account form for exit");
    }

    public AccountForm(String formlocator, String formTitle) {
        super(formlocator, formTitle);
    }

    public void deleteProfile(){
        settings.clickAndWait();
        AccountSettingsForm settings = new AccountSettingsForm();
        settings.deleteProfile();
    }

    public void logout(){
        beforeLogout.clickAndExplicitWait(logout.getLocator());
        logout.clickAndWait();
    }

    public void assertNewFriends(){
        doAssert(newFriendsIndicator.isPresent(),"Indicator \"New friend request\" is present on page","Indicator \"New friend request\" are not present on page");
    }

    public void assertNewMessage(){
        doAssert(newMessageIndicator.isPresent(),"Indicator \"New message\" is present on page","Indicator \"New message\" are not present on page");
    }

    public void clickFriends(){
        friends.clickAndWait();
    }

    public void clickMassages(){
        messages.clickAndWait();
    }
}
