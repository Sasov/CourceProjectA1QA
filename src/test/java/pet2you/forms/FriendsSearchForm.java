package pet2you.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.ElementCollection;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

/**
 * Created by mikola on 18.07.2016.
 */
public class FriendsSearchForm extends BaseForm {

    private TextBox friendsSearchInput = new TextBox(By.id("search-main-input"), "Friend search line");
    private Button friendsSearchSubmit = new Button(By.id("search-submit"), "Friends search submit button");
    private Label showAllusers = new Label(By.xpath("//div[contains(text(),'Перейти ко всем найденым пользователям')]"), "Show all found users");
    private Label uiMessageSendRequest = new Label(By.xpath("//div[contains(text(),'Запрос отправлен')]"));
    private Button confirmFriendshipRequest = new Button(By.xpath("//div[contains(text(),'Запрос отправлен')]/following-sibling::div//button[span[contains(text(),'Ok')]]"), "Press Ok");
    private Label uiMessageAlreadySendRequest = new Label(By.xpath("//div[contains(text(),'Вы уже послали приглашение дружить')]"));
    private Button confirmAlreadySendFriendshipRequest = new Button(By.xpath("//div[contains(text(),'Вы уже послали приглашение дружить')]/following-sibling::div//button[span[contains(text(),'Ok')]]"), "Press Ok");
    private Label uiDialog = new Label(By.xpath("//div[contains(@class,'ui-dialog')]"), "Modal Dialog");

    public FriendsSearchForm() {
        super(By.xpath("//.[contains(text(),'Расширенный поиск')]"), "Friends search form");
    }

    public void searchAndAddFriend(String userName, String userSoname){
        String userNameAndSoname = userName + " " + userSoname;
        friendsSearchInput.type(userName);
        friendsSearchSubmit.clickAndWait();
        if (showAllusers.isPresent()){
            showAllusers.clickAndWait();
        }
        browser.scrollToElement(By.className("copyright"));
        ElementCollection foundUsers = new ElementCollection(By.xpath("//div[@class=\"user-block\"]//div[contains(@class,'name')]/a[position()=1]"), "All found users");
        int collectionSize = foundUsers.getCollectionSize();
        for (int i=0; i<collectionSize; i++){
            WebElement user = foundUsers.getElement(i);
            String userNameFromCollection = user.getText();
            if (userNameFromCollection.contains(userNameAndSoname)){
                Button addFriend = new Button(By.xpath("//.[contains(text(),'" + userNameAndSoname + "')]/ancestor::div[1]/following-sibling::div//a[@original-title=\"Добавить в друзья\"]"), "Add Friend " + userNameAndSoname + " Button");
                addFriend.clickAndExplicitWait(uiDialog.getLocator());
                doAssert(uiMessageSendRequest.isPresent(),"Confirmation message \"Запрос отправлен\" is present on page","Confirmation message \"Запрос отправлен\" are not present on page");
                confirmFriendshipRequest.click();
                addFriend.clickAndExplicitWait(uiDialog.getLocator());
                doAssert(uiMessageAlreadySendRequest.isPresent(),"Confirmation message \"Вы уже послали приглашение дружить\" is present on page","Confirmation message \"Вы уже послали приглашение дружить\" are not present on page");
                confirmAlreadySendFriendshipRequest.click();
            }
        }

    }
}
