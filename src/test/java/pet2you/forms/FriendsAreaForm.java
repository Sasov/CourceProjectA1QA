package pet2you.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.ElementCollection;
import webdriver.elements.Label;

/**
 * Created by mikola on 18.07.2016.
 */
public class FriendsAreaForm extends BaseForm {

    private Button addFriends = new Button(By.id("UserFriendsWidget_AddFriends"), "Add Friends");
    private Label friendshipRequestsHeader = new Label(By.xpath("//.[contains(text(),'Запросы на дружбу')]"));
    private Label uiDialog = new Label(By.xpath("//div[contains(@class,'ui-dialog')]"), "Modal Dialog");
    private Label uiMessageDeleteFriendConfirm = new Label(By.xpath("//div[contains(text(),'Вы уверены, что хотите удалить друга?')]"));
    private Button confirmDeleteFriend = new Button(By.xpath("//div[contains(text(),'Вы уверены, что хотите удалить друга?')]/following-sibling::div//button[span[contains(text(),'Да')]]"), "Press Ok");
    private Label uiMessageDeleteFriendDone = new Label(By.xpath("//div[contains(text(),'Вы больше не друзья')]"));
    private Button pressOk = new Button(By.xpath("//div[contains(text(),'Вы больше не друзья')]/following-sibling::div//button[span[contains(text(),'Ok')]]"), "Press Ok");

    public FriendsAreaForm() {
        super(By.xpath("//.[contains(text(),'Мои друзья')]"), "Friends area form");
    }

    public void addFriend(String userName, String userSoname){
        addFriends.clickAndWait();
        FriendsSearchForm friendSearch = new FriendsSearchForm();
        friendSearch.searchAndAddFriend(userName, userSoname);
    }

    public void assertFriendshipRequests(){
        doAssert(friendshipRequestsHeader.isPresent(),"Friendship requests are present on page","Friendship requests are not present on page");
    }

    public void confirmFriendship(String userName, String userSoname){
        String userNameAndSoname = userName + " " + userSoname;
        browser.scrollToElement(By.id("copyright"));
        assertFriendshipRequests();
        ElementCollection friendshipRequests = new ElementCollection(By.xpath("//div[contains(@class,'friend row messages')]/div[3]//a"), "Friends requests list");
        int collectionSize = friendshipRequests.getCollectionSize();
        for (int i=0; i<collectionSize; i++){
            WebElement friendshipRequest = friendshipRequests.getElement(i);
            String displayedName = friendshipRequest.getText();
            if (displayedName.contains(userName)){
                Button confirmFriendshipRequest = new Button(By.xpath("//.[contains(text(),'" + userName + "')]/ancestor::div[1]//following-sibling::div//.[contains(text(),'Подтвердить дружбу')]"),"Confirm friendship request");
                assertFriend(userNameAndSoname);
            }
        }
    }

    public void assertFriend(String userNameAndSoname) {
        browser.scrollToElement(By.id("copyright"));
        Label friendOnPage = new Label(By.xpath("//div[@class=\"friends-list\"]//.[contains(text(),'" + userNameAndSoname + "')]"), "Friend on page");
        doAssert(friendOnPage.isPresent(),"New friend are present in friends list","New friend are not present in friends list");
    }

    public void sendMessage(String userName, String userSoname, String message){
        String userNameAndSoname = userName + " " + userSoname;
        assertFriend(userNameAndSoname);
        Button sendMessage = new Button(By.xpath("//.[contains(text(),'" + userNameAndSoname + "')]/ancestor::div[1]/following-sibling::div//a[@original-title=\"Написать сообщение\"]"),"Send message button");
        sendMessage.clickAndWait();
        DialogForm dialog = new DialogForm();
    }

    public void deleteFriend(String userName, String userSoname){
        String userNameAndSoname = userName + " " + userSoname;
        assertFriend(userNameAndSoname);
        Button deleteFriend = new Button(By.xpath("//.[contains(text(),'" + userNameAndSoname + "')]/ancestor::div[1]/following-sibling::div//a[@original-title=\"Удалить из друзей\"]"),"Delete friend button");
        deleteFriend.clickAndExplicitWait(uiDialog.getLocator());
        doAssert(uiMessageDeleteFriendConfirm.isPresent(),"Confirm delete friend message is present","Confirm delete friend message is not present");
        confirmDeleteFriend.clickAndExplicitWait(uiDialog.getLocator());
        doAssert(uiMessageDeleteFriendDone.isPresent(),"Delete friend message is present","Delete friend message is not present");
        pressOk.click();
    }
}
