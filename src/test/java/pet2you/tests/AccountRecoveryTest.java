package pet2you.tests;

import pet2you.forms.AccountForm;
import pet2you.forms.MainForm;
import pet2you.forms.NewsPageForm;

/**
 * Created by mikola on 20.07.2016.
 */
public class AccountRecoveryTest extends Pet2youBaseTest {


    public void runTest() throws InterruptedException {

        String firstUserNameAndSurname = firstUserName + " " + firstUserSoname;

        logger.step(1);
        MainForm mainForm = new MainForm();
        mainForm.login(firstUserEmail, firstUserPassword, firstUserNameAndSurname);

        logger.step(2);
        AccountForm accountForm = new AccountForm(firstUserNameAndSurname);
        accountForm.deleteProfile();

        logger.step(3);
        MainForm mainFormAfterDeleteProfile = new MainForm();

        logger.step(4);
        mainFormAfterDeleteProfile.userRecovery(firstUserEmail, firstUserPassword, firstUserNameAndSurname);

        logger.step(5);
        NewsPageForm newsPageForm = new NewsPageForm();
        newsPageForm.clickProfile();

        logger.step(6);
        AccountForm accountFormAfterRecovery = new AccountForm(firstUserNameAndSurname);


    }
}
