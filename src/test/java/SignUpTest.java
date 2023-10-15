import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SignUpTest extends BaseTest{

    @Test
    public void signUpTest() {
        String lastName = "Rafal";
        int randomNumber = (int) (Math.random()*100000);
        String email = "tester" + randomNumber + "@tester.pl";
        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
        driver.findElement(By.name("firstname")).sendKeys("Rafal");
        driver.findElement(By.name("lastname")).sendKeys("Bednarowicz");
        driver.findElement(By.name("phone")).sendKeys("123456");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("strongPassword");
        driver.findElement(By.name("confirmpassword")).sendKeys("strongPassword");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));

        Assert.assertTrue(heading.getText().contains(lastName));
        Assert.assertEquals(heading.getText(), "Hi, Rafal Bednarowicz");
    }
    @Test
    public void singnUpTestEmptyFields(){
        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();
        driver.findElement(By.xpath("//button[@type='submit' and text()=' Sign Up']")).click();

        /*List<WebElement> alerty = driver.findElements(By.xpath("//div[@class='alert alert-danger']/p"));
        System.out.println(alerty);
        Assert.assertEquals(alerty.get(0).getText(), "The Email field is required.");
        Assert.assertEquals(alerty.get(1).getText(), "The Password field is required.");
        Assert.assertEquals(alerty.get(2).getText(), "The Password field is required.");
        Assert.assertEquals(alerty.get(3).getText(), "The First name field is required.");
        Assert.assertEquals(alerty.get(4).getText(), "The Last Name field is required.");*/
        List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']/p")).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
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
        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']")).get(1).click();

        driver.findElement(By.name("firstname")).sendKeys("Rafal");
        driver.findElement(By.name("lastname")).sendKeys("Bednarowicz");
        driver.findElement(By.name("phone")).sendKeys("123456");
        driver.findElement(By.name("email")).sendKeys("zlymail.pl");
        driver.findElement(By.name("password")).sendKeys("strongPassword");
        driver.findElement(By.name("confirmpassword")).sendKeys("strongPassword");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']/p")).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        Assert.assertTrue(errors.contains("The Email field must contain a valid email address."));
       // WebElement alert = driver.findElement(By.xpath("//div[@class='alert alert-danger']/p"));
        //System.out.println(alert);
        //Assert.assertEquals(alert.getText(), "The Email field must contain a valid email address.");

    }
}
