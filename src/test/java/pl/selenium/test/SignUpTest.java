package pl.selenium.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.selenium.pages.HotelSearchPage;
import pl.selenium.pages.LoggedUserPage;
import pl.selenium.pages.SignUpPage;
import java.util.List;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {
        String lastName = "Bednarowicz";
        int randomNumber = (int) (Math.random()*100000);
        String email = "tester" + randomNumber + "@tester.pl";
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSingUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstNameInput("Rafal");
        signUpPage.setLastNameInput(lastName);
        signUpPage.setPhoneInput("123456");
        signUpPage.setEmailInput(email);
        signUpPage.setPasswordInput("strongPassword");
        signUpPage.setConfirmPasswordInput("strongPassword");
        signUpPage.signUp();

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);


        Assert.assertTrue(loggedUserPage.getHeading().contains(lastName));
        Assert.assertEquals(loggedUserPage.getHeading(), "Hi, Rafal Bednarowicz");
    }
    @Test
    public void singnUpTestEmptyFields(){
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSingUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUp();


        /*List<WebElement> alerty = driver.findElements(By.xpath("//div[@class='alert alert-danger']/p"));
        System.out.println(alerty);
        Assert.assertEquals(alerty.get(0).getText(), "The Email field is required.");
        Assert.assertEquals(alerty.get(1).getText(), "The Password field is required.");
        Assert.assertEquals(alerty.get(2).getText(), "The Password field is required.");
        Assert.assertEquals(alerty.get(3).getText(), "The First name field is required.");
        Assert.assertEquals(alerty.get(4).getText(), "The Last Name field is required.");*/
        List<String> errors = signUpPage.getErrors();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();
    }

    @Test
    public void signUpTestIncorrectEmail(){
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSingUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstNameInput("Rafal");
        signUpPage.setLastNameInput("Bednarowicz");
        signUpPage.setPhoneInput("123456");
        signUpPage.setEmailInput("zlyMail.pl");
        signUpPage.setPasswordInput("strongPassword");
        signUpPage.setConfirmPasswordInput("strongPassword");
        signUpPage.signUp();

       // WebElement alert = driver.findElement(By.xpath("//div[@class='alert alert-danger']/p"));
        //System.out.println(alert);
        Assert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address.") );

    }
}
