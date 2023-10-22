package pl.selenium.test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pl.selenium.pages.HotelSearchPage;
import pl.selenium.pages.ResultsPage;
import pl.selenium.utils.ExcelReader;
import pl.selenium.utils.ExtentTestManager;
import pl.selenium.utils.SeleniumHelper;
import pl.selenium.utils.TestListener;

import java.io.IOException;
import java.util.List;

@Listeners(value = {TestListener.class})
public class SearchHotelTest extends BaseTest {
    @Test
    public void searchHotel() throws IOException {
        ExtentTest test = ExtentTestManager.startTest("test123", "Search hoter");

        HotelSearchPage hotelSearchPage =new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("14/04/2021", "21/04/2021");
        hotelSearchPage.setTravellers(1, 1);
        hotelSearchPage.performSearch();
        ResultsPage resultsPage = new ResultsPage(driver);
        Assert.assertEquals("a", "b");
        List<String> hotelNames = resultsPage.getHotelList();

        hotelNames.forEach(System.out::println);

        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals( hotelNames.get(1), "Oasis Beach Tower");
        Assert.assertEquals( hotelNames.get(2) ,"Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");
        //test.log(Status.PASS, "Assertions done");
    }
    @Test
    public void searchHotelNoName() throws IOException {
        ExtentTest test = ExtentTestManager.startTest("test123", "Search hotel with no name");

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setDates("14/04/2021", "21/04/2021");
        hotelSearchPage.setTravellers(0, 2);
        hotelSearchPage.performSearch();
        ExtentTestManager.getTest().log(Status.PASS, "Search performed");

        ResultsPage resultsPage = new ResultsPage(driver);
        WebElement sprawdzanyTekst = resultsPage.getResultHeading();
        Assert.assertTrue(sprawdzanyTekst.isDisplayed());
        Assert.assertEquals(sprawdzanyTekst.getText(), "No Results Found" );
    }
    @Test(dataProvider = "data")
    public void searchHotelTestWithDataProvider(String city, String hotel) {

        ExtentTest test = ExtentTestManager.startTest("data provider", "Search Hotel with data provider");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity(city);
        test.log(Status.PASS, "Set city from data provider " + city + " done");
        hotelSearchPage.setDates("14/04/2021", "21/04/2021");
        test.log(Status.PASS, "Set dates for " + city);
        hotelSearchPage.setTravellers(1, 1);
        test.log(Status.PASS, "Set travellers for " + city);
        hotelSearchPage.performSearch();
        test.log(Status.PASS, "Perform search for " + city);

        ResultsPage resultsPage = new ResultsPage(driver);
        List<String> hotelNames = resultsPage.getHotelList();
        test.log(Status.PASS,"Results page loaded");

        Assert.assertEquals(hotelNames.get(0), hotel);
        test.log(Status.PASS, "Assertions done for " + city);
    }
    @DataProvider
    public Object[][] data() throws IOException {
        return ExcelReader.readExcel("testData.xlsx");
    }
}
