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
        List<String> hotelNames = hotelSearchPage.setCity("Dubai")
                .setDates("14/04/2021", "21/04/2021")
                .setTravellers(1, 1)
                .performSearch()
                .getHotelList();

        hotelNames.forEach(System.out::println);

        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals( hotelNames.get(1), "Oasis Beach Tower");
        Assert.assertEquals( hotelNames.get(2) ,"Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");
    }
    @Test
    public void searchHotelNoName(){
        ResultsPage resultsPage = new HotelSearchPage(driver)
                .setDates("14/04/2021", "21/04/2021")
                .setTravellers(0, 2)
                .performSearch();

        Assert.assertTrue(resultsPage.getResultHeading().isDisplayed());
        Assert.assertEquals(resultsPage.getResultHeading().getText(), "No Results Found" );
    }
}
