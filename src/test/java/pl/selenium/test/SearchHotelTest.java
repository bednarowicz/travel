package pl.selenium.test;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.selenium.pages.HotelSearchPage;
import pl.selenium.pages.ResultsPage;
import java.util.List;


public class SearchHotelTest extends BaseTest {
    @Test
    public void searchHotel() {

        HotelSearchPage hotelSearchPage =new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("14/04/2021", "21/04/2021");
        hotelSearchPage.setTravellers(1, 1);
        hotelSearchPage.performSearch();

        ResultsPage resultsPage = new ResultsPage(driver);

        List<String> hotelNames = resultsPage.getHotelList();

        hotelNames.forEach(System.out::println);

        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals( hotelNames.get(1), "Oasis Beach Tower");
        Assert.assertEquals( hotelNames.get(2) ,"Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");
    }
    @Test
    public void searchHotelNoName(){
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setDates("14/04/2021", "21/04/2021");
        hotelSearchPage.setTravellers(0, 2);
        hotelSearchPage.performSearch();

        ResultsPage resultsPage = new ResultsPage(driver);
        WebElement sprawdzanyTekst = resultsPage.getResultHeading();
        Assert.assertTrue(sprawdzanyTekst.isDisplayed());
        Assert.assertEquals(sprawdzanyTekst.getText(), "No Results Found" );
    }
}
