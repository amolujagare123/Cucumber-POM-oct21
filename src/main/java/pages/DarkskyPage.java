package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;

import static stepdefinitions.SharedSD.getDriver;

public class DarkskyPage extends Base{

    By currTempRow = By.xpath("//span[@class='summary swap']") ;
    By timelineTempRaw = By.xpath("//span[@class='first']//span");
    By timeListRaw = By.xpath("//span[@class='hour']/span");
    By expander = By.xpath("//a[@data-day='0']//span[@class='toggle']");
    By barTempMin = By.xpath("//a[@data-day='0']//span[@class='minTemp']");
    By barTempMax = By.xpath("//a[@data-day='0']//span[@class='maxTemp']");
    By timeLineTempList = By.xpath("//div[contains(@class,'revealed')]//span[@class='temp']");

    By lnkDarkSkyAPI = By.xpath("//a[normalize-space()='Dark Sky API']");

    public void clickLnkDarkskyAPI()
    {
        clickOn(lnkDarkSkyAPI);
    }

    public ArrayList<String> getBarTempList()
    {
        ArrayList<String> tempList = new ArrayList(){{

        add(getTextFromElement(barTempMin).split("˚")[0]); // 70˚
        add(getTextFromElement(barTempMax).split("˚")[0]);

        }};

        return tempList;
    }


    public ArrayList<String> getTimelineTempList()
    {
        ArrayList<String> tempRowList = getElementTextList(timeLineTempList);
        ArrayList<String> tempList = new ArrayList(){
            {

                add(tempRowList.get(0).split("˚")[0]); // 70˚
                add(tempRowList.get(1).split("˚")[0]);
            }};

        return tempList;



    }


    public void clickExpander()
    {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,800)");
        js.executeScript("arguments[0].click()",webAction(expander));


        // clickOn(expander);
    }


    public ArrayList<Integer> getTimelist()
    {
        ArrayList<String> timeRawList =  getElementTextList(timeListRaw);
        System.out.println(timeRawList); // 9pm,11pm
        ArrayList<Integer> timelist = new ArrayList<>();

        for(int i=0;i<timeRawList.size();i++)
        {
           String temp =  timeRawList.get(i) ;  // 9pm
            int l = temp.length();
            String timeStr = temp.substring(0,l-2);

            timelist.add(Integer.parseInt(timeStr));
        }
        System.out.println(timelist); // 9pm,11pm




        return timelist;
    }




    public int getCurrentTemp()
    {
        String tempRow = getTextFromElement(currTempRow);
        // 87˚ Clear.
        return Integer.parseInt(tempRow.split("˚")[0]);
    }

    public int getTimeLineTemp()
    {
        String tempRow = getTextFromElement(timelineTempRaw);
        //89°
        return Integer.parseInt(tempRow.split("°")[0]);
    }



}
