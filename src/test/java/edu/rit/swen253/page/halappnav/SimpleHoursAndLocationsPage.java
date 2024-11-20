package edu.rit.swen253.page.halappnav;

import org.openqa.selenium.By;

import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.utils.DomElement;

public class SimpleHoursAndLocationsPage extends AbstractAngularPage{
    static By CLASS_SEARCH_BUTTON = By.cssSelector("button[mattooltip='Class Search']");
    static By HAL_BUTTON = By.cssSelector("button[mattooltip='Hours & Locations']");
    static By GPA_CALCULATOR_BUTTON = By.cssSelector("button[mattooltip='GPA Calculator']");
    static By FEEDBACK_BUTTON = By.linkText("Feedback");
    static By SUPPORT_BUTTON = By.linkText("Support");
    static By DIRECTORY_BUTTON = By.linkText("Directory");
    static By RIT_HOME_BUTTON = By.linkText("RIT Home");

    DomElement classSearchButton = findOnPage(CLASS_SEARCH_BUTTON);
    DomElement halButton = findOnPage(HAL_BUTTON);
    DomElement gpaCalculatorButton  = findOnPage(GPA_CALCULATOR_BUTTON);
    DomElement feedbackButton = findOnPage(FEEDBACK_BUTTON);
    DomElement supportButton = findOnPage(SUPPORT_BUTTON);
    DomElement directoryButton = findOnPage(DIRECTORY_BUTTON);
    DomElement ritHomeButton = findOnPage(RIT_HOME_BUTTON);

    public SimpleHoursAndLocationsPage() {
        super("hours-and-locations");
    }

    public enum HALSideNav {
        CLASS_SEARCH,
        HOURS_AND_LOCATIONS,
        GPA_CALCULATOR;
    }

    public enum HALTopNav {
        FEEDBACK,
        SUPPORT,
        DIRECTORY,
        RIT_HOME;
    }

    /**
     * Click on the navbar button provided
     * @param sideNav
     */
    public void clickOnNavbar(HALSideNav sideNav) {
        switch (sideNav) {
            case CLASS_SEARCH:
                classSearchButton.click();
                break;
            case HOURS_AND_LOCATIONS:
                halButton.click();
                break;
            case GPA_CALCULATOR:
                gpaCalculatorButton.click();
                break;
        }
    }

    /**
     * Click on the top navbar button provided
     * @param topNav
     */
    public void clickOnTopNav(HALTopNav topNav) {
        switch (topNav) {
            case FEEDBACK:
                feedbackButton.click();
                break;
            case SUPPORT:
                supportButton.click();
                break;
            case DIRECTORY:
                directoryButton.click();
                break;
            case RIT_HOME:
                ritHomeButton.click();
                break;
        }
    }

}
