package com.automation.baseclass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AutomationPractiseBaseClass {
    public static WebDriver driver;
    public static Properties prop;
    @BeforeClass
    public void TestBase() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(
                    "C:\\Users\\nsang\\eclipse-workspace\\AutomationPractise\\src\\main\\java\\com\\automationpractise\\config\\config.properties");
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setUp() {
        String browserName = prop.getProperty("browser");
        System.out.println(browserName);
        String urlLink = prop.getProperty("url");
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    "C:\\Users\\nsang\\Downloads\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
        } else
            System.out.println("invalid browser name");
        driver.get(urlLink);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        //String pageLoadTime = prop.getProperty("pageLoad");
        //long pageLoadWait = Long.parseLong(pageLoadTime);
        //System.out.println(pageLoadWait);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod

    public void tearDown() {
        driver.quit();

    }
    public void waitTillElementToBeClickable(WebElement locator){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(locator));

    }

}
