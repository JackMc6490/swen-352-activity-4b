package edu.rit.swen253.test.halappnav;

import static org.junit.jupiter.api.Assertions.*;
import static edu.rit.swen253.utils.BrowserType.FIREFOX;
import static edu.rit.swen253.utils.BrowserType.onBrowser;
import static edu.rit.swen253.utils.TimingUtils.sleep;
import org.junit.jupiter.api.*;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.halappnav.SimpleHoursAndLocationsPage;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.utils.BrowserWindow;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppNavigationTest extends AbstractWebTest {
    private SimpleHoursAndLocationsPage halPage;
    private BrowserWindow<SimpleHoursAndLocationsPage> halWindow;

    @Test
    @Order(1)
    @DisplayName("First, navigate to the Tiger Center Hours and Locations page.")
    void navigateToHalPage() {
        halPage = navigateToPage("https://tigercenter.rit.edu/tigerCenterApp/api/hours-and-locations",
                SimpleHoursAndLocationsPage::new);
        assertNotNull(halPage);
        halWindow = getCurrentWindow();
    }

    @Test
    @Order(2)
    @DisplayName("Second, click on the TopBar < Feedback button and validate navigation.")
    void navigateToMaps() {
        halPage.clickOnTopNav(SimpleHoursAndLocationsPage.HALTopNav.FEEDBACK);
        final SimplePage feedbackPage = assertNewWindowAndSwitch(SimplePage::new);
        // there's a timing issue with Firefox (give it a second to render)
        if (onBrowser(FIREFOX)) {
            sleep(1);
        }

        assertTrue(feedbackPage.getURL().startsWith("https://help.rit.edu/"));
    }
    @Test
    @Order(3)
    @DisplayName("Go back to the HAL page.")
    void switchToApp() {
      assertNotSame(halPage, getCurrentWindow().page(), "Before switch");
      switchToWindow(halWindow);
      assertSame(halPage, getCurrentWindow().page(), "After switch");
    }
    @Test
    @Order(4)
    @DisplayName("Click on TopBar < Support button and validate navigation.")
    void navigateToSupport() {
        halPage.clickOnTopNav(SimpleHoursAndLocationsPage.HALTopNav.SUPPORT);
        final SimplePage supportPage = assertNewWindowAndSwitch(SimplePage::new);

        // there's a timing issue with Firefox (give it a second to render)
        if (onBrowser(FIREFOX)) {
            sleep(1);
        }

        assertTrue(supportPage.getURL().startsWith("https://help.rit.edu/"));
    }

    @Test
    @Order(5)
    @DisplayName("Go back to the HAL page.")
    void switchToApp2() {
      assertNotSame(halPage, getCurrentWindow().page(), "Before switch");
      switchToWindow(halWindow);
      assertSame(halPage, getCurrentWindow().page(), "After switch");
    }

    @Test
    @Order(6)
    @DisplayName("Click on TopBar < Directory button and validate navigation.")
    void navigateToDirectory() {
        halPage.clickOnTopNav(SimpleHoursAndLocationsPage.HALTopNav.DIRECTORY);
        final SimplePage directoryPage = assertNewWindowAndSwitch(SimplePage::new);

        // there's a timing issue with Firefox (give it a second to render)
        if (onBrowser(FIREFOX)) {
            sleep(1);
        }

        assertTrue(directoryPage.getURL().startsWith("https://www.rit.edu/directory"));
    }

    @Test
    @Order(7)
    @DisplayName("Go back to the HAL page.")
    void switchToApp3() {
      assertNotSame(halPage, getCurrentWindow().page(), "Before switch");
      switchToWindow(halWindow);
      assertSame(halPage, getCurrentWindow().page(), "After switch");
    }

    @Test
    @Order(8)
    @DisplayName("Click on TopBar < RIT Home button and validate navigation.")
    void navigateToRitHome() {
        halPage.clickOnTopNav(SimpleHoursAndLocationsPage.HALTopNav.RIT_HOME);
        final SimplePage ritHomePage = assertNewWindowAndSwitch(SimplePage::new);

        // there's a timing issue with Firefox (give it a second to render)
        if (onBrowser(FIREFOX)) {
            sleep(1);
        }

        assertTrue(ritHomePage.getURL().startsWith("https://www.rit.edu/"));
    }

    @Test
    @Order(9)
    @DisplayName("Go back to the HAL page.")
    void switchToApp4() {
      assertNotSame(halPage, getCurrentWindow().page(), "Before switch");
      switchToWindow(halWindow);
      assertSame(halPage, getCurrentWindow().page(), "After switch");
    }

}
