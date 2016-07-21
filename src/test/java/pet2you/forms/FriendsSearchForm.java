package pet2you.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
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
    private Label showAllUsers = new Label(By.xpath("//div[contains(text(),'Перейти ко всем найденым пользователям')]"), "Show all found users");
    private Button confirmFriendshipRequest = new Button(By.xpath("//div[contains(text(),'Запрос отправлен')]/following-sibling::div//button[span[contains(text(),'Ok')]]"), "Press Ok");
    private Button confirmAlreadySendFriendshipRequest = new Button(By.xpath("//div[contains(text(),'Вы уже послали приглашение дружить')]/following-sibling::div//button[span[contains(text(),'Ok')]]"), "Press Ok");
    private Label uiDialog = new Label(By.xpath("//div[contains(@class,'ui-dialog')]"), "Modal Dialog");

    public FriendsSearchForm() {
        super(By.id("search-main-input"), "Friends search form");
    }

    public void searchAndAddFriend(String userName, String userSoname){
        String userNameAndSoname = userName + " " + userSoname;
        friendsSearchInput.type(userName);
        friendsSearchSubmit.clickAndWait();
        if (showAllUsers.isPresent()){
            showAllUsers.clickAndScriptWait();
        }
        ElementCollection foundUsers = new ElementCollection(By.xpath("//div[@class=\"user-block\"]//div[contains(@class,'name')]/a[position()=1]"), "All found users");
        int collectionSize = foundUsers.getCollectionSize();
        for (int i=0; i<collectionSize; i++){
            WebElement user = foundUsers.getElement(i);
            String userNameFromCollection = user.getText();
            if (userNameFromCollection.contains(userNameAndSoname)){
                clickOffset(user, 55, 77);
                browser.explicitWait(uiDialog.getLocator());
                confirmFriendshipRequest.click();
                clickOffset(user, 55, 77);
                browser.explicitWait(uiDialog.getLocator());
                doAssert(uiDialog.isPresent(),"Dialog \"Вы уже отправили приглашение\" is present","Dialog \"Вы уже отправили приглашение\" is absent");
                confirmAlreadySendFriendshipRequest.click();
            }
        }

    }

    protected void clickOffset (WebElement element, int xOffset, int yOffset){
        WebDriver driver = browser.getDriver();
        Actions actions = new Actions(driver);
        actions.moveToElement(element, xOffset, yOffset).click();
        Action hoverClick = actions.build();
        hoverClick.perform();
        browser.scriptWait();
    }


}
