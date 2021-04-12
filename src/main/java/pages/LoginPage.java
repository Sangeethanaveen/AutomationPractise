package pages;

import com.automation.baseclass.AutomationPractiseBaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class LoginPage extends AutomationPractiseBaseClass {
    @FindBy(id="email")
    WebElement userName;
    @FindBy(id="passwd")
    WebElement passWord;
    @FindBy(id="SubmitLogin")
    WebElement signInBtn;
    @FindBy(className ="info-account")
    WebElement signInInfo;
    @FindBy(xpath = "//div[@class='alert alert-danger']/ol/li")
    WebElement signInError;
    @FindBy(className ="logout")
    WebElement signOut;


    public LoginPage() {
        PageFactory.initElements(driver,this);
    }
    @Test(dataProvider="loginDetails")
    public String loginWithValidCredential(String username,String password){
        userName.sendKeys(username);
        passWord.sendKeys(password);
        signInBtn.click();
        String msg= signInInfo.getText();
        signOut.click();
        return msg;
    }
    public String loginWithInvalidCredential(String username,String password){
        userName.sendKeys(username);
        passWord.sendKeys(password);
        signInBtn.click();
        String errorMsg=signInError.getText();
        return errorMsg;
    }
}
