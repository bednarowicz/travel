package pl.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.selenium.model.User;

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
    public SignUpPage setFirstNameInput(String firstName){
        firstNameInput.sendKeys(firstName);
        return this;
    }
    public SignUpPage setLastNameInput(String lastName){
        lastNameInput.sendKeys(lastName);
        return this;
    }
    public SignUpPage setPhoneInput(String phoneNumber){
        phoneInput.sendKeys(phoneNumber);
        return this;
    }
    public SignUpPage setEmailInput(String email){
        emailInput.sendKeys(email);
        return this;
    }
    public SignUpPage setPasswordInput(String password){
        passwordInput.sendKeys(password);
        return this;
    }
    public SignUpPage setConfirmPasswordInput(String confirmPassword){
        confirmPasswordInput.sendKeys(confirmPassword);
        return this;
    }
    public LoggedUserPage signUp(){
        signUpButton.click();
        return new LoggedUserPage(driver);
    }
    public List<String> getErrors(){
        return errors.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
   /* public void fillSignForm(String firstName, String secondName, String phone, String email, String password){
        setFirstNameInput(firstName);
        setLastNameInput(secondName);
        setPhoneInput(phone);
        setEmailInput(email);
        setPasswordInput(password);
        setConfirmPasswordInput(password);
        signUpButton.click();
    }
    public void fillSignForm(User user){
        setFirstNameInput(user.getFirstName());
        setLastNameInput(user.getSecondName());
        setPhoneInput(user.getPhone());
        setEmailInput(user.getEmail());
        setPasswordInput(user.getPassword());
        setConfirmPasswordInput(user.getPassword());
        signUpButton.click();
    }*/
}
