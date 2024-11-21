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
    void navHome(){
        halPage = navigateToPage("https://tigercenter.rit.edu/tigerCenterApp/api/hours-and-locations",
                SimpleHoursAndLocationsPage::new);
        halWindow = getCurrentWindow();
        
    }
    @Test
    @Order(1)
    @DisplayName("First, navigate to the Tiger Center Hours and Locations page.")
    void navigateToHalPage() {
        navHome();
        assertNotNull(halPage);
        
    }

    @Test
    @Order(2)
    @DisplayName("Second, click on the TopBar < Feedback button and validate navigation.")
    void navigateToFeedback() {
        halPage.clickOnTopNav(SimpleHoursAndLocationsPage.HALTopNav.FEEDBACK);
        final SimplePage feedbackPage = assertNewPage(SimplePage::new);
        // there's a timing issue with Firefox (give it a second to render)
        if (onBrowser(FIREFOX)) {
            sleep(1);
        }

        assertTrue(feedbackPage.getURL().startsWith("https://help.rit.edu/"));
        navHome();
    }
    @Test
    @Order(3)
    @DisplayName("Click on TopBar < Support button and validate navigation.")
    void navigateToSupport() {
        halPage.clickOnTopNav(SimpleHoursAndLocationsPage.HALTopNav.SUPPORT);
        final SimplePage supportPage = assertNewWindowAndSwitch(SimplePage::new);

        // there's a timing issue with Firefox (give it a second to render)
        if (onBrowser(FIREFOX)) {
            sleep(1);
        }
        System.out.println(supportPage.getURL());
        assertTrue(supportPage.getURL().startsWith("https://help.rit.edu/"));
        switchToWindow(halWindow);
    }


    @Test
    @Order(4)
    @DisplayName("Click on TopBar < Directory button and validate navigation.")
    void navigateToDirectory() {
        halPage.clickOnTopNav(SimpleHoursAndLocationsPage.HALTopNav.DIRECTORY);
        final SimplePage directoryPage = assertNewWindowAndSwitch(SimplePage::new);

        // there's a timing issue with Firefox (give it a second to render)
        if (onBrowser(FIREFOX)) {
            sleep(1);
        }
        System.out.println(directoryPage.getURL());
        assertTrue(directoryPage.getURL().startsWith("https://www.rit.edu/directory"));
        switchToWindow(halWindow);
    }


    @Test
    @Order(5)
    @DisplayName("Click on TopBar < RIT Home button and validate navigation.")
    void navigateToRitHome() {
        halPage.clickOnTopNav(SimpleHoursAndLocationsPage.HALTopNav.RIT_HOME);
        final SimplePage ritHomePage = assertNewWindowAndSwitch(SimplePage::new);

        // there's a timing issue with Firefox (give it a second to render)
        if (onBrowser(FIREFOX)) {
            sleep(1);
        }
        System.out.println(ritHomePage.getURL());
        assertTrue(ritHomePage.getURL().startsWith("https://www.rit.edu/"));
        switchToWindow(halWindow);
    }

    @Test
    @Order(6)
    @DisplayName("Click on the navbar button provided")
    void navigateToClassSearch() {
        halPage.clickOnNavbar(SimpleHoursAndLocationsPage.HALSideNav.CLASS_SEARCH);
        sleep(1);
        System.out.println(halPage.getURL());
        assertTrue(halPage.getURL().startsWith("https://tigercenter.rit.edu/tigerCenterApp/api/class-search"));

    }

    @Test
    @Order(7)
    @DisplayName("Click on the navbar button provided")
    void navigateToCourseCatalog() {
        halPage.clickOnNavbar(SimpleHoursAndLocationsPage.HALSideNav.GPA_CALCULATOR);
        sleep(1);
        System.out.println(halPage.getURL());
        assertTrue(halPage.getURL().startsWith("https://tigercenter.rit.edu/tigerCenterApp/api/gpa-calc"));
    }

    @Test
    @Order(8)
    @DisplayName("Click on the navbar button provided")
    void navigateToGpaCalculator() {
        halPage.clickOnNavbar(SimpleHoursAndLocationsPage.HALSideNav.HOURS_AND_LOCATIONS);
        // there's a timing issue with Firefox (give it a second to render)
        sleep(1);
        System.out.println(halPage.getURL());
        assertTrue(halPage.getURL().startsWith("https://tigercenter.rit.edu/tigerCenterApp/api/hours-and-locations"));
    }


}
