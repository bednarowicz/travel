package pl.selenium.test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.selenium.pages.HotelSearchPage;
import pl.selenium.pages.ResultsPage;
import pl.selenium.utils.ExcelReader;
import pl.selenium.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;


public class SearchHotelTest extends BaseTest {
    @Test
    public void searchHotel() throws IOException {

        ExtentTest test = extentReports.createTest("Search Hotel Test");

        HotelSearchPage hotelSearchPage =new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        test.log(Status.PASS, "Setting city done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setDates("14/04/2021", "21/04/2021");
        test.log(Status.PASS, "Setting dates done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setTravellers(1, 1);
        test.log(Status.PASS, "Adding travelers done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.performSearch();
        test.log(Status.PASS, "Performing search done", SeleniumHelper.getScreenshot(driver));
        test.log(Status.PASS, "Performing search done", SeleniumHelper.getScreenshot(driver));
        test.log(Status.PASS,"Screenshot", SeleniumHelper.getScreenshot(driver));
        ResultsPage resultsPage = new ResultsPage(driver);

        List<String> hotelNames = resultsPage.getHotelList();

        hotelNames.forEach(System.out::println);

        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals( hotelNames.get(1), "Oasis Beach Tower");
        Assert.assertEquals( hotelNames.get(2) ,"Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");
        test.log(Status.PASS, "Assertions done");
    }
    @Test
    public void searchHotelNoName() throws IOException {
        ExtentTest test = extentReports.createTest("Search Hotel No Name Test");

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setDates("14/04/2021", "21/04/2021");
        test.log(Status.PASS, "Setting date done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setTravellers(0, 2);
        test.log(Status.PASS, "Setting travelers done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.performSearch();
        test.log(Status.PASS, "Performing search done", SeleniumHelper.getScreenshot(driver));
        test.log(Status.INFO,"Info test", SeleniumHelper.getScreenshot(driver));
        test.log(Status.WARNING, "warning test", SeleniumHelper.getScreenshot(driver));
        test.log(Status.FAIL, "fail test", SeleniumHelper.getScreenshot(driver));

        ResultsPage resultsPage = new ResultsPage(driver);
        WebElement sprawdzanyTekst = resultsPage.getResultHeading();
        Assert.assertTrue(sprawdzanyTekst.isDisplayed());
        Assert.assertEquals(sprawdzanyTekst.getText(), "No Results Found" );
    }
    @Test(dataProvider = "data")
    public void searchHotelTestWithDataProvider(String city, String hotel) {

        ExtentTest test = extentReports.createTest("Search Hotel with data provider");
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
