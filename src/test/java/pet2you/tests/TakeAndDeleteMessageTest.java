package pet2you.tests;

import pet2you.forms.AccountForm;
import pet2you.forms.DialogForm;
import pet2you.forms.MainForm;
import pet2you.forms.MessagesForm;

/**
 * Created by mikola on 20.07.2016.
 */
public class TakeAndDeleteMessageTest extends Pet2youBaseTest {


    public void runTest() throws InterruptedException {

        String secondUserNameAndSurname = secondUserName + " " + secondUserSoname;

        logger.step(1);
        MainForm mainForm = new MainForm();
        mainForm.login(secondUserEmail, secondUserPassword, secondUserNameAndSurname);

        logger.step(2);
        AccountForm accountForm = new AccountForm(secondUserNameAndSurname);

        logger.step(3);
        accountForm.assertNewMessage();

        logger.step(4);
        accountForm.clickMassages();

        logger.step(5);
        MessagesForm messagesForm = new MessagesForm();

        logger.step(6);
        messagesForm.navigateToDialog(message);

        logger.step(7);
        DialogForm dialogForm = new DialogForm();

        logger.step(8);
        dialogForm.checkLastMessage(message);

        logger.step(9);
        dialogForm.deleteLastMessage();

        logger.step(10);
        dialogForm.checkDeleteMessage(message);
    }
}
