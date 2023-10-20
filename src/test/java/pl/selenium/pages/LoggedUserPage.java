package pl.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.selenium.utils.SeleniumHelper;

public class LoggedUserPage {
    private WebDriver driver;

    @FindBy(xpath = "//h3[@class='RTL']")
    private WebElement heading;
    public LoggedUserPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getHeading(){
        SeleniumHelper.waitForElementToExist(driver, heading);
        return heading.getText();
    }
}
