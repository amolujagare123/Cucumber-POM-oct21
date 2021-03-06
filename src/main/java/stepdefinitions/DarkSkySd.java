package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.DarkskyPage;

import java.util.ArrayList;
import java.util.Collections;

import static stepdefinitions.SharedSD.getDriver;

public class DarkSkySd {
        DarkskyPage darkskyPage = new DarkskyPage();

    @Given("^I am on Darksky Home Page$")
    public void i_am_on_darksky_home_page() throws Throwable {

      /*  Assert.assertEquals("this is not a darksky homepage",
                "Dark Sky - Sansad Marg, New Delhi, Delhi",
                getDriver().getTitle());
*/
    }

    @Then("^I verify current temp is equal to Temperature from Daily Timeline$")
    public void i_verify_current_temp_is_equal_to_temperature_from_daily_timeline()
            {

                int expected = darkskyPage.getTimeLineTemp();
                int actual = darkskyPage.getCurrentTemp();

                System.out.println("actual="+actual);
                System.out.println("expected="+expected);


                Assert.assertEquals("temperatures are not equal",expected,actual);
            }


    @Then("^I verify timeline is displayed with two hours incremented$")
    public void i_verify_timeline_is_displayed_with_two_hours_incremented()
    {
       ArrayList<Integer> timelist =  darkskyPage.getTimelist();

        // [10, 12, 2, 4, 6, 8, 10, 12, 2, 4, 6]
        ArrayList<Integer> timeDifflist = new ArrayList<>();

        for(int i=0;i<timelist.size()-1;i++)
        {
            int time1 = timelist.get(i);
            int time2 = timelist.get(i+1);
            int timeDiff = 0;

            if(time2>time1)
              timeDiff = time2 - time1;
            else if  (time2<time1)
                timeDiff = (time2 +12) - time1;

            timeDifflist.add(timeDiff);
        }

        System.out.println(timeDifflist);

        int size = timeDifflist.size();
        int occurance = Collections.frequency(timeDifflist,2);

        boolean result = (size == occurance);// true /false

        Assert.assertTrue("all the differences are not 2",result);
    }

    @Then("^I verify today's lowest and highest temp is displayed correctly$")
    public void i_verify_todays_lowest_and_highest_temp_is_displayed_correctly() throws Throwable {
        darkskyPage.clickExpander();

        ArrayList<String> expected = darkskyPage.getBarTempList();
        ArrayList<String> actual = darkskyPage.getTimelineTempList();

        System.out.println("expected="+expected);
        System.out.println("actual="+actual);

        Assert.assertEquals("temperatures are not correct",expected,actual);

    }
}

