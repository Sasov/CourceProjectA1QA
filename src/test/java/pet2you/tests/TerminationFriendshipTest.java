package pet2you.tests;

import pet2you.forms.AccountForm;
import pet2you.forms.FriendsAreaForm;
import pet2you.forms.MainForm;

/**
 * Created by mikola on 20.07.2016.
 */
public class TerminationFriendshipTest extends Pet2youBaseTest {


    public void runTest() throws InterruptedException {

        String firstUserNameAndSurname = firstUserName + " " + firstUserSoname;
        String secondUserNameAndSurname = secondUserName + " " + secondUserSoname;

        logger.step(1);
        MainForm mainForm = new MainForm();
        mainForm.login(firstUserEmail, firstUserPassword, firstUserNameAndSurname);

        logger.step(2);
        AccountForm accountForm = new AccountForm(firstUserNameAndSurname);

        logger.step(3);
        accountForm.clickFriends();

        logger.step(4);
        FriendsAreaForm friendsAreaForm = new FriendsAreaForm();

        logger.step(5);
        friendsAreaForm.deleteFriend(secondUserName, secondUserSoname);

        logger.step(6);
        FriendsAreaForm friendsAreaFormAfterDelete = new FriendsAreaForm();
        friendsAreaFormAfterDelete.assertFriendAbsent(secondUserNameAndSurname);
    }
}
