package pl.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ResultsPage {
    @FindBy(xpath = "//h4[contains(@class,'list_title')]//b" )
    private List<WebElement> hotelList;

    @FindBy(xpath = "//h2[@class='text-center']")
    private WebElement resultHeading;

    public ResultsPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    public List<String> getHotelList(){
        return  hotelList
                .stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());
    }

    public WebElement getResultHeading(){
        return resultHeading;
    }
}
