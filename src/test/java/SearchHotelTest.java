import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;
import java.util.stream.Collectors;

public class SearchHotelTest extends BaseTest {
    @org.testng.annotations.Test
    public void searchHotel() {
        driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']")).click();
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Dubai");
        driver.findElement(By.xpath("//span[@class='select2-match' and text()='Dubai']")).click();
        driver.findElement(By.name("checkin")).sendKeys("14/04/2021");
        //driver.findElement(By.name("checkout")).sendKeys("20/04/2021");
        driver.findElement(By.name("checkout")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='28']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[text()=' Search']")).click();
        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class,'list_title')]//b"))
                .stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());
        System.out.println(hotelNames.size());
        //hotelNames.forEach(el -> System.out.println(el));
        hotelNames.forEach(System.out::println);

        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals( hotelNames.get(1), "Oasis Beach Tower");
        Assert.assertEquals( hotelNames.get(2) ,"Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");
    }
    @Test
    public void searchHotelNoName(){
        driver.findElement(By.name("checkin")).sendKeys("10/10/2023");
        driver.findElement(By.name("checkout")).click();
        driver.findElements(By.xpath(("//td[@class='day ' and text()='28']"))).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElements(By.xpath("//button[@type='submit' and contains(@class, 'loader')]")).get(0).click();

        WebElement sprawdzanyTekst = driver.findElement(By.xpath("//h2[@class='text-center']"));
        Assert.assertTrue(sprawdzanyTekst.isDisplayed());
        Assert.assertEquals(sprawdzanyTekst.getText(), "No Results Found" );
    }
}
