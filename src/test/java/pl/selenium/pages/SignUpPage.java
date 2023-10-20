package pl.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.selenium.utils.SeleniumHelper;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpPage {
    private WebDriver driver;
    @FindBy(name = "firstname" )
    private WebElement firstNameInput;

    @FindBy(name = "lastname" )
    private WebElement lastNameInput;

    @FindBy(name = "phone")
    private WebElement phoneInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "confirmpassword")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//button[text()=' Sign Up']")
    private WebElement signUpButton;

    @FindBy(xpath = "//div[@class='alert alert-danger']/p")
    private List<WebElement> errors;
    public SignUpPage (WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public void setFirstNameInput(String firstName){
        firstNameInput.sendKeys(firstName);
    }
    public void setLastNameInput(String lastName){
        lastNameInput.sendKeys(lastName);
    }
    public void setPhoneInput(String phoneNumber){
        phoneInput.sendKeys(phoneNumber);
    }
    public void setEmailInput(String email){
        emailInput.sendKeys(email);
    }
    public void setPasswordInput(String password){
        passwordInput.sendKeys(password);
    }
    public void setConfirmPasswordInput(String confirmPassword){
        confirmPasswordInput.sendKeys(confirmPassword);
    }
    public void signUp(){
        signUpButton.click();
    }
    public List<String> getErrors(){
        SeleniumHelper.waitForNotEmptyList(driver, By.xpath("//div[@class='alert alert-danger']/p"));
        return errors.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
