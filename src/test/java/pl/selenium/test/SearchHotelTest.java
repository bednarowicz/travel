package pl.selenium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.selenium.pages.HotelSearchPage;
import pl.selenium.test.BaseTest;

import java.util.List;
import java.util.stream.Collectors;

public class SearchHotelTest extends BaseTest {
    @Test
    public void searchHotel() {

        HotelSearchPage hotelSearchPage =new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("14/04/2021", "21/04/2021");
        hotelSearchPage.setTravellers();
        hotelSearchPage.performSearch();

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
