package com.automation.testcases;

import com.automation.baseclass.AutomationPractiseBaseClass;
import testdata.ExcelDataConfig;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.Map;

public class LoginPageTest extends AutomationPractiseBaseClass {
    @Test(dataProvider = "validUser")

    public void loginWithValidUser(String username, String password) {
        LoginPage loginPg = new LoginPage();
        HomePage homePg = new HomePage();
        homePg.navigateToSignIn();
        String signInMessage = loginPg.loginWithValidCredential(username, password);
        Assert.assertEquals(signInMessage, "Welcome to your account. Here you can manage all of your personal information and orders.", "SignIn message mismatch");

    }

    @DataProvider
    public Object[][] validUser() {
        ExcelDataConfig config = new ExcelDataConfig();
        Map<Object, Object> testValue = config.getData("TestData", "LoginWithValidUser");
        //int n = value.size();
        return new Object[][]{
                {testValue.get("data1"), testValue.get("data2")}
        };
    }

    @Test(dataProvider = "inValidUser")
    public void loginWithInvalidUser(String username, String password) {
        LoginPage loginPg = new LoginPage();
        HomePage homePg = new HomePage();
        homePg.navigateToSignIn();
        String errorMessage = loginPg.loginWithInvalidCredential(username, password);
        Assert.assertEquals(errorMessage,"Authentication failed.","String mismatch");
        System.out.println(errorMessage);

    }
    @DataProvider
    public Object[][] inValidUser() {
        ExcelDataConfig config = new ExcelDataConfig();
        Map<Object, Object> testValue = config.getData("TestData", "LoginWithInValidUser");
        //int n = value.size();
        return new Object[][]{
                {testValue.get("data1"), testValue.get("data2")}
        };
    }
}

