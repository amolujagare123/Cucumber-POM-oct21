package pages;

import org.openqa.selenium.By;

public class DarkskyAPI extends DarkskyPage{

    By lnkLogin = By.xpath("//a[normalize-space()='Log In']");

    public void clickLnkLogin()
    {
        clickOn(lnkLogin);
    }

}
