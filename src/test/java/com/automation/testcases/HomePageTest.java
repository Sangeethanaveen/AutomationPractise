package com.automation.testcases;

import com.automation.baseclass.AutomationPractiseBaseClass;
import testdata.ExcelDataConfig;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.Map;

public class HomePageTest extends AutomationPractiseBaseClass {
    public HomePageTest() {
        super();
    }

    @Test
    public void signInWithValidUser() {
        HomePage homePg = new HomePage();
        homePg.navigateToSignIn();

    }
@Test
    public void numberOfLinksInFooter(){
        HomePage home=new HomePage();
        int linkCount =home.footerLinkCount();
        Assert.assertEquals(1,linkCount);
    }
    @Test
    public void checkLinksEnabledInFooter() throws InterruptedException {
        HomePage home=new HomePage();
        home.checkLinkOpeningInFooter();
        home.verifyLinkOpening();
        Thread.sleep(2000l);
    }
    @Test(dataProvider = "search")
    public void searchByProductName(String productName) {
        HomePage homePg = new HomePage();
        String searchMsg = homePg.searchByProduct(productName);
        Assert.assertEquals(searchMsg, "1 result has been found.", "String mismatch");
    }

    @DataProvider
    public Object[][] search() {
        ExcelDataConfig config = new ExcelDataConfig();
        Map<Object, Object> testValue = config.getData("TestData", "search");
        return new Object[][]{
                {testValue.get("data1")}, {testValue.get("data2")}
        };
    }

    @Test(dataProvider = "compare")
    public void searchAndCompareProduct(String productName) throws InterruptedException {
        HomePage homePg = new HomePage();
        String check = homePg.compareProductAfterSearch(productName);
        Assert.assertEquals(check, "PRODUCT COMPARISON", "String Mismatch");
    }
    @DataProvider
    public Object[][] compare() {
        ExcelDataConfig config = new ExcelDataConfig();
        Map<Object, Object> testValue = config.getData("TestData", "CompareProduct");
        return new Object[][]{
                {testValue.get("data1")}
        };
    }
}