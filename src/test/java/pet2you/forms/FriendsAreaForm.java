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
    private Label friendshipRequestsWrapper = new Label(By.id("friend-invite-list"), "Friendship request");
    private Button friendsButton = new Button(By.xpath("//div[@id=\"left-menu-container\"]/a[contains(text(),\"Друзья\")]"), "Friends Button");

    public FriendsAreaForm() {
        super(By.xpath("//div[@id=\"UserFriendsWidget_AddFriends\"]"), "Friends area form");
    }

    public void addFriend(String userName, String userSoname){
        addFriends.clickAndWait();
        FriendsSearchForm friendSearch = new FriendsSearchForm();
        friendSearch.searchAndAddFriend(userName, userSoname);
    }

    public void assertFriendshipRequests(){
        doAssert(friendshipRequestsWrapper.isPresent(),"Friendship requests are present on page","Friendship requests are not present on page");
    }

    public void confirmFriendship(String userName, String userSoname){
        String userNameAndSoname = userName + " " + userSoname;
        ElementCollection friendshipRequests = new ElementCollection(By.xpath("//div[contains(@class,'friend row messages')]/div[3]//a"), "Friends requests list");
        int collectionSize = friendshipRequests.getCollectionSize();
        for (int i=0; i<collectionSize; i++){
            WebElement friendshipRequest = friendshipRequests.getElement(i);
            String displayedName = friendshipRequest.getText();
            if (displayedName.contains(userName)){
                friendshipRequest.click();
                browser.waitForPageToLoad();
                AccountForm friendAccount = new AccountForm(userNameAndSoname);
                friendAccount.confirmFriendship();
                browser.waitForPageToLoad();
            }
        }
    }

    public void assertFriendAbsent(String userNameAndSoname) {
        Label friendOnPage = new Label(By.xpath("//.[contains(text(),'" + userNameAndSoname + "')]"), "Friend on page");
        doAssert(!friendOnPage.isPresent(),"Friend is absent friends list","Friend is present in friends list");
    }

    public void sendMessage(String userName, String userSoname, String message){
        String userNameAndSoname = userName + " " + userSoname;
        ElementCollection friends = new ElementCollection(By.xpath("//div[contains(@id,'block_friend')]/div[2]/a"), "Friends list");
        int collectionSize = friends.getCollectionSize();
        for (int i=0; i<collectionSize; i++) {
            WebElement friend = friends.getElement(i);
            String displayedName = friend.getText();
            if (displayedName.contains(userNameAndSoname)) {
                friend.click();
                browser.waitForPageToLoad();
                AccountForm friendAccount = new AccountForm(userNameAndSoname);
                friendAccount.writeMessage();
                DialogForm dialog = new DialogForm();
                dialog.sendMessage(message);
            }
        }
    }

    public void deleteFriend(String userName, String userSoname){
        String userNameAndSoname = userName + " " + userSoname;
        ElementCollection friends = new ElementCollection(By.xpath("//div[contains(@id,'block_friend')]/div[2]/a"), "Friends list");
        int collectionSize = friends.getCollectionSize();
        for (int i=0; i<collectionSize; i++) {
            WebElement friend = friends.getElement(i);
            String displayedName = friend.getText();
            if (displayedName.contains(userNameAndSoname)) {
                friend.click();
                browser.waitForPageToLoad();
                AccountForm friendAccount = new AccountForm(userNameAndSoname);
                friendAccount.deleteFriend();
                friendsButton.clickAndWait();
            }
        }
        browser.waitForPageToLoad();
    }
}
