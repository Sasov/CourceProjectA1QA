package pet2you.tests;

import pet2you.forms.AccountForm;
import pet2you.forms.FriendsAreaForm;
import pet2you.forms.MainForm;

/**
 * Created by mikola on 20.07.2016.
 */
public class AcceptFriedshipRequestTest extends Pet2youBaseTest {



    public void runTest() throws InterruptedException {

        String secondUserNameAndSurname = secondUserName + " " + secondUserSoname;

        logger.step(1);
        MainForm mainForm = new MainForm();
        mainForm.login(secondUserEmail, secondUserPassword, secondUserNameAndSurname);

        logger.step(2);
        AccountForm accountForm = new AccountForm(secondUserNameAndSurname);

        logger.step(3);
        accountForm.assertNewFriends();

        logger.step(4);
        accountForm.clickFriends();

        logger.step(5);
        FriendsAreaForm friendsAreaForm = new FriendsAreaForm();

        logger.step(6);
        friendsAreaForm.assertFriendshipRequests();
        friendsAreaForm.confirmFriendship(firstUserName, firstUserSoname);


    }
}
