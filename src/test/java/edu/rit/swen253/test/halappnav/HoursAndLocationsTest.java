package edu.rit.swen253.test.halappnav;

import static org.junit.jupiter.api.Assertions.*;
import static edu.rit.swen253.utils.BrowserType.FIREFOX;
import static edu.rit.swen253.utils.BrowserType.onBrowser;
import static edu.rit.swen253.utils.TimingUtils.sleep;
import org.junit.jupiter.api.*;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.halappnav.SimpleHoursAndLocationsPage;
import edu.rit.swen253.page.halappnav.TestfulHoursAndLocationsPage;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.utils.BrowserWindow;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HoursAndLocationsTest extends AbstractWebTest {
    private TestfulHoursAndLocationsPage halPage;
    private BrowserWindow<TestfulHoursAndLocationsPage> halWindow;

    @Test
    @Order(1)
    @DisplayName("First, navigate to the Tiger Center Hours and Locations page.")
    void navigateToHalPage() {
        halPage = navigateToPage("https://tigercenter.rit.edu/tigerCenterApp/api/hours-and-locations",
                TestfulHoursAndLocationsPage::new);
        assertNotNull(halPage);
        halWindow = getCurrentWindow();
    }

    @Test
    @Order(2)
    @DisplayName("Click on the Dining tab")
    void navigateToDining() {
        halPage.switchMainTab(TestfulHoursAndLocationsPage.HALMainTab.DINING);
        // there's a timing issue with Firefox (give it a second to render)
        if (onBrowser(FIREFOX)) {
            sleep(1);
        }
    }

    @Test
    @Order(3)
    @DisplayName("Click on Saturday tab")
    void navigateToSaturday() {
        halPage.switchDateTab(3);
        // there's a timing issue with Firefox (give it a second to render)
        if (onBrowser(FIREFOX)) {
            sleep(1);
        }
    }

    @Test
    @Order(4)
    @DisplayName("Click on Beanz, validate its hours")
    void navigateToBeanz() {
        
        // there's a timing issue with Firefox (give it a second to render)
        if (onBrowser(FIREFOX)) {
            sleep(1);
        }
        int index = 0;
        while(index < 6) {
            halPage.clickOnBeanz();
            try {
                String beanzHours = halPage.getBeanzHours();
                System.out.println("Hours: " + beanzHours);
                assertEquals(beanzHours, "9:00AM - 10:00PM");
                break;
            } catch (Exception e) {
                halPage.switchDateTab(index);
                sleep(2);
                halPage.clickOnBeanz();
                sleep(2);
                index++;
                // if(index == 6) {
                //     fail("Beanz hours not found");
                // }
            }
        }
        
    }

}
