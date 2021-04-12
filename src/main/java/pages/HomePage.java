package pages;

import com.automation.baseclass.AutomationPractiseBaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class HomePage extends AutomationPractiseBaseClass {
    @FindBy(css =".login")
    WebElement signInBtn;

    @FindBy(id="search_query_top")
    WebElement search;

    @FindBy(css="[name='submit_search']")
    WebElement searchSubmit;

    @FindBy(className = "heading-counter")
    WebElement searchResult;

    @FindBy(how= How.PARTIAL_LINK_TEXT,using = "Add to Compare")
    List<WebElement> addToCompare;

    @FindBy(className = "add_to_compare checked")
    WebElement checkCompare;

    @FindBy(how=How.CLASS_NAME,using = "product-name")
    List<WebElement> compareProductName;

    @FindBy(xpath ="//span[contains(text(),'Compare')]")
    WebElement compareButton;

    @FindBy(className = "icon-th-list")
    WebElement listView;

    @FindBy(xpath = "//a[@title='Close']")
    WebElement popUpClose;

    @FindBy(className = "page-heading")
    WebElement compareHeading;
    @FindBy(xpath = "a")
    WebElement linktag;
    public HomePage() {
        PageFactory.initElements(driver,this);
    }

    public void navigateToSignIn() {
        signInBtn.click();
    }
    public int footerLinkCount(){
       WebElement footerDriver= driver.findElement(By.id("footer"));
       List<WebElement> linkCount =  footerDriver.findElements(By.tagName("a"));
       int noOfLinksInFooter=linkCount.size();
       WebElement childFooterDriver=footerDriver.findElement(By.id("block_various_links_footer"));
       List<WebElement> linkChildFooter=childFooterDriver.findElements(By.tagName("a"));
       int noOfChildFooter=linkChildFooter.size();
       int checkLink=Integer.compare(noOfLinksInFooter,noOfChildFooter);
       return checkLink;
    }
    public void checkLinkOpeningInFooter(){
        WebElement footerDriver= driver.findElement(By.id("footer"));
        List<WebElement> linkCount =  footerDriver.findElements(By.tagName("a"));
        int noOfLinksInFooter=linkCount.size();
        WebElement childFooterDriver=footerDriver.findElement(By.cssSelector(".block_content.toggle-footer"));
        List<WebElement> linkChildFooter=childFooterDriver.findElements(By.tagName("a"));
        int noOfChildFooter=linkChildFooter.size();
        for(int i =1;i<=noOfChildFooter;i++) {
            String clickOnLinkTab=Keys.chord(Keys.CONTROL,Keys.ENTER);
            footerDriver.findElements(By.tagName("a")).get(i).sendKeys(clickOnLinkTab);
        }
    }
    public void verifyLinkOpening(){
        Set<String> windowHandle=driver.getWindowHandles();

        int noOfWindow= windowHandle.size();
        Iterator<String> it = windowHandle.iterator();
        ArrayList<String> windowNames=new ArrayList<>(noOfWindow);
        for(int i=0;i<noOfWindow;i++){
            if(it.hasNext()){
                String window= it.next();
                driver.switchTo().window(window);
                String title = driver.getTitle();

                windowNames.add(title);
            }
            for(String s :windowNames) {
                System.out.print(s);
            }

        }


    }

    public String searchByProduct(String productName){
        search.clear();
        search.sendKeys(productName);
        searchSubmit.click();
        String searchMessage = searchResult.getText();
        search.clear();
        return searchMessage;
    }
    public String compareProductAfterSearch(String productName) throws InterruptedException {
        boolean check = false;
        search.clear();
        search.sendKeys(productName);
        searchSubmit.click();
        listView.click();
        for (WebElement k:compareProductName) {
            String name = k.getText();
            check = name.contains(productName);
        }
            for (int i=0;i<5;i++) {
          addToCompare.get(i).click();
            }
         popUpClose.click();
         waitTillElementToBeClickable(compareButton);
         compareButton.click();
        String checkMsg = compareHeading.getText();


        return checkMsg;
    }

    }

