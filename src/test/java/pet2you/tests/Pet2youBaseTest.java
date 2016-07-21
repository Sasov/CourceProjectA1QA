package pet2you.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pet2you.forms.AccountForm;
import webdriver.BaseTest;
import webdriver.PropertiesResourceManager;

public abstract class Pet2youBaseTest extends BaseTest {

    protected String firstUserName;
    protected String firstUserSoname;
    protected String firstUserEmail;
    protected String firstUserPassword;
    protected String secondUserName;
    protected String secondUserSoname;
    protected String secondUserEmail;
    protected String secondUserPassword;
    protected String message;


    @BeforeClass
    @Parameters({"firstUserName","firstUserSoname", "firstUserEmail", "firstUserPassword", "secondUserName","secondUserSoname", "secondUserEmail", "secondUserPassword", "message"})
    public void readParameters( String firstUserName,
                                String firstUserSoname,
                                String firstUserEmail,
                                String firstUserPassword,
                                String secondUserName,
                                String secondUserSoname,
                                String secondUserEmail,
                                String secondUserPassword,
                                String message) {
        this.firstUserName = firstUserName;
        this.firstUserSoname = firstUserSoname;
        this.firstUserEmail = firstUserEmail;
        this.firstUserPassword = firstUserPassword;
        this.secondUserName = secondUserName;
        this.secondUserSoname = secondUserSoname;
        this.secondUserEmail = secondUserEmail;
        this.secondUserPassword = secondUserPassword;
        this.message = message;
    }



    @AfterClass
    public void after() {

        AccountForm accountFormForExit = new AccountForm();
        accountFormForExit.logout();
        if (browser.isBrowserAlive()) {
            browser.exit();
            checkAndKill();
        }
    }


}
