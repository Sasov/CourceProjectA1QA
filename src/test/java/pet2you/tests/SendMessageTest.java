package pet2you.tests;

import pet2you.forms.AccountForm;
import pet2you.forms.DialogForm;
import pet2you.forms.FriendsAreaForm;
import pet2you.forms.MainForm;

/**
 * Created by mikola on 20.07.2016.
 */
public class SendMessageTest extends Pet2youBaseTest {


    public void runTest() throws InterruptedException {

        String firstUserNameAndSurname = firstUserName + " " + firstUserSoname;

        logger.step(1);
        MainForm mainForm = new MainForm();
        mainForm.login(firstUserEmail, firstUserPassword, firstUserNameAndSurname);

        logger.step(2);
        AccountForm accountForm = new AccountForm(firstUserNameAndSurname);
        accountForm.clickFriends();

        logger.step(3);
        FriendsAreaForm friendsAreaForm = new FriendsAreaForm();
        friendsAreaForm.sendMessage(secondUserName, secondUserSoname, message);

        logger.step(4);
        DialogForm dialogForm = new DialogForm();
        dialogForm.checkLastMessage(message);
    }
}
