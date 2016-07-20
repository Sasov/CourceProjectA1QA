package pet2you.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.BaseForm;
import webdriver.elements.ElementCollection;

/**
 * Created by mikola on 19.07.2016.
 */
public class MessagesForm extends BaseForm {



    public MessagesForm() {
        super(By.xpath("//.[contains(text(),'Диалоги')]"), "Massages form");
    }

    public void navigateToDialog(String message){
        ElementCollection dialogs = new ElementCollection(By.xpath("//div[contains(@class,'message')]/div[contains(@class,'text')]"), "All dialogs on page");
        int dialogsSize = dialogs.getCollectionSize();
        for (int i=0; i<dialogsSize; i++){
            WebElement dialog = dialogs.getElement(i);
            String messageOnPage = dialog.getText();
            if (messageOnPage.contains(message)){
                info("Dialog with message \"" + message + "\" founded in dialog list");
                dialog.click();
                browser.waitForPageToLoad();
            }
        }
    }
}
