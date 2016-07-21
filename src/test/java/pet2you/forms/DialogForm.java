package pet2you.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

/**
 * Created by mikola on 18.07.2016.
 */
public class DialogForm extends BaseForm {

    private TextBox messageTextBox = new TextBox(By.xpath("//div[@id=\"MessagesPageWidget_input\"]//div[@contenteditable=\"true\"]"),"Message input area");
    private Button messageSubmit = new Button(By.id("send_message"), "Send message");
    private Label lastMessage = new Label(By.xpath("//li[contains(@id,'MessagesPageWidget_message_')][last()]//p"), "Last sent message");
    private Button lastMessageDelete = new Button(By.xpath("(//div[contains(@class,'delete-message')])[last()]"), "Last message delete");

    public DialogForm() {
        super(By.xpath("//div[@id=\"MessagesPageWidget_input\"]//div[@contenteditable=\"true\"]"), "Dialog form");
    }

    public void sendMessage(String message){
        messageTextBox.type(message);
        messageSubmit.clickAndScriptWait();
    }

    public void checkLastMessage(String message){
        String displayedLastMessage = lastMessage.getText();
        doAssert(displayedLastMessage.contains(message),"Message is displayed in dialog box","Message is not displayed in dialog box");
    }

    public void deleteLastMessage(){
        lastMessageDelete.clickAndScriptWait();
    }

    public void checkDeleteMessage(String message){
        Label searchDeleteMessage = new Label(By.xpath("//.[contains(text(),'" + message + "')]"), "Deleted message");
        boolean searchResult = searchDeleteMessage.isPresent();
        doAssert(!searchResult,"Deleted message is absent on page","Deleted message is present on page");
    }
}
