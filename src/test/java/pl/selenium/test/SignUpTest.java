package pl.selenium.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.selenium.model.User;
import pl.selenium.pages.HotelSearchPage;
import pl.selenium.pages.LoggedUserPage;
import pl.selenium.pages.SignUpPage;

import java.util.List;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {
        String lastName = "Bednarowicz";
        int randomNumber = (int) (Math.random() * 100000);

        LoggedUserPage loggedUserPage = new HotelSearchPage(driver).
                openSingUpForm().
                setFirstNameInput("Rafal").
                setLastNameInput(lastName).
                setPhoneInput("123456").
                setEmailInput("tester" + randomNumber + "@tester.pl").
                setPasswordInput("strongPassword").
                setConfirmPasswordInput("strongPassword").
                signUp();

        Assert.assertTrue(loggedUserPage.getHeading().contains(lastName));
        Assert.assertEquals(loggedUserPage.getHeading(), "Hi, Rafal Bednarowicz");
    }

    /* @Test
     public void signUpTest2() {
         String lastName = "Bednarowicz";
         int randomNumber = (int) (Math.random()*100000);
         String email = "tester" + randomNumber + "@tester.pl";
         HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
         hotelSearchPage.openSingUpForm();

         SignUpPage signUpPage = new SignUpPage(driver);
         signUpPage.fillSignForm("Rafal", lastName, "123456", email, "strongPassword" );

         LoggedUserPage loggedUserPage = new LoggedUserPage(driver);


         Assert.assertTrue(loggedUserPage.getHeading().contains(lastName));
         Assert.assertEquals(loggedUserPage.getHeading(), "Hi, Rafal Bednarowicz");
     }*/
   /* @Test
    public void signUpTest3() {
        String lastName = "Bednarowicz";
        int randomNumber = (int) (Math.random()*100000);
        String email = "tester" + randomNumber + "@tester.pl";
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSingUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        User user = new User();
        user.setFirstName("Rafal");
        user.setSecondName(lastName);
        user.setPhone("123456");
        user.setEmail(email);
        user.setPassword("strongPassword");
        signUpPage.fillSignForm(user );

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);


        Assert.assertTrue(loggedUserPage.getHeading().contains(lastName));
        Assert.assertEquals(loggedUserPage.getHeading(), "Hi, Rafal Bednarowicz");
    }*/
    @Test
    public void singnUpTestEmptyFields() {
        SignUpPage signUpPage = new HotelSearchPage(driver).openSingUpForm();

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
    public void signUpTestIncorrectEmail() {
        SignUpPage signUpPage = new HotelSearchPage(driver).openSingUpForm().
                setFirstNameInput("Rafal").
                setLastNameInput("Bednarowicz").
                setPhoneInput("123456").
                setEmailInput("zlyMail.pl").
                setPasswordInput("strongPassword").
                setConfirmPasswordInput("strongPassword");
        signUpPage.signUp();

        // WebElement alert = driver.findElement(By.xpath("//div[@class='alert alert-danger']/p"));
        //System.out.println(alert);
        Assert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));

    }
}
