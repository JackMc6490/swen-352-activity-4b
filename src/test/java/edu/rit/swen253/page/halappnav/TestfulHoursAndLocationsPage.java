package edu.rit.swen253.page.halappnav;

import java.util.List;

import org.openqa.selenium.By;

import edu.rit.swen253.utils.DomElement;

public class TestfulHoursAndLocationsPage extends SimpleHoursAndLocationsPage {
    // Locators
    private By DINING_TAB = By.xpath("//div[@role='tab']//h4[text()='Dining Services']/ancestor::div[@role='tab']");
    private By DINING_DAY_TABS = By.cssSelector("span.diningTabDateText");
    private By BEANZ_ACCORDION = By.xpath("//span[text()='Beanz']/ancestor::div[contains(@class, 'col-xs-12')]");
    private By BEANZ_HOURS = By.xpath(
            "//div[@class='diningTabEateryName']/span[text()='Beanz']/ancestor::div[@class='col-xs-12']/span[@class='greenText']");
    private By LABS_TAB = By.xpath("//div[@role='tab']//h4[text()='Computer Labs']/ancestor::div[@role='tab']");
    private By LABS_FILTER_PRINTER = By
            .xpath("//span[text()='Printer']/ancestor::mat-checkbox//input[@type='checkbox']");
    private By LABS_LOCATIONS_SHOWN = By.cssSelector("span.student-labsTabLocationsShownText");
    private By _3D_WORLD_ACCORDION = By
            .xpath("//div[@class='labsTabEateryName']/span[text()='3D World']/ancestor::div[@class='clickHelper']");
    private By _3D_WORLD_DESCRIPTION = By.xpath(
            "//span[text()='3D World']/ancestor::div[contains(@class, 'clickHelper')]/following-sibling::div//p[contains(text(), 'A Macintosh and Windows computer lab')]");
    private By _3D_WORLD_PRINTER_ICON = By.xpath(
            "//span[text()='3D World']/ancestor::div[contains(@class, 'clickHelper')]//img[contains(@src, 'printer_orange.svg')]");
    private By _3D_WORLD_SOFTWARE_TAB = By.xpath(
            "//span[text()='3D World']/ancestor::div[contains(@class, 'clickHelper')]/following-sibling::div//mat-tab-header//div[contains(@class, 'mat-tab-label-content') and text()='Software']");
    private By _3D_WORLD_SOFTWARE_SEARCH = By.xpath(
            "//span[text()='3D World']/ancestor::div[contains(@class, 'clickHelper')]/following-sibling::div//input[contains(@name, 'labsTabSearch')]");
    private By _3D_WORLD_SOFTWARE_LIST = By.xpath(
            "//span[text()='3D World']/ancestor::div[contains(@class, 'clickHelper')]/following-sibling::div//mat-tab-body[@id='mat-tab-content-35-1']//div[@class='software-list-class']");
    private By AFFAIRS_TAB = By.xpath("//div[@role='tab']//h4[text()='Student Affairs']/ancestor::div[@role='tab']");
    private By AFFAIRS_LOCATIONS_SHOWN = By.cssSelector("span.student-affairsTabLocationsShownText");

    // Constructor
    public TestfulHoursAndLocationsPage() {
        super();
    }

    public enum HALMainTab {
        DINING,
        LABS,
        AFFAIRS;
    }

    // Main Tab Switch
    public void switchMainTab(HALMainTab mainTab) {
        switch (mainTab) {
            case DINING:
                findOnPage(DINING_TAB).click();
                break;
            case LABS:
                findOnPage(LABS_TAB).click();
                break;
            case AFFAIRS:
                findOnPage(AFFAIRS_TAB).click();
                break;
        }
    }

    public void switchDateTab(Integer tabIndex) {
        // Find all the date tabs
        List<DomElement> dateTabs = findAllOnPage(DINING_DAY_TABS);

        // Ensure the tab index is within range
        if (tabIndex >= 0 && tabIndex < dateTabs.size()) {
            // Click on the tab at the specified index
            dateTabs.get(tabIndex).click();
        }
    }

    // Beanz Interaction
    public void clickOnBeanz() {
        findOnPage(BEANZ_ACCORDION).click();
    }

    public String getBeanzHours() {
        DomElement hours = findOnPage(BEANZ_HOURS);
        return hours.getText();
    }

    // Labs Interaction
    public void getLabsLocationsShown() {
        findOnPage(LABS_LOCATIONS_SHOWN).getText();
    }

    public void filterLabsByPrinter() {
        findOnPage(LABS_FILTER_PRINTER).click();
    }

    public void clickOn3DWorld() {
        findOnPage(_3D_WORLD_ACCORDION).click();
    }

    public void is3DWorldPrinterIconIlluminated() {
        findOnPage(_3D_WORLD_PRINTER_ICON);
    }

    public String get3DWorldDescription() {
        return findOnPage(_3D_WORLD_DESCRIPTION).getText();
    }

    public void clickOn3DWorldSoftwareTab() {
        findOnPage(_3D_WORLD_SOFTWARE_TAB).click();
    }

    public void clickOn3DWorldSoftwareTabSearchBar() {
        findOnPage(_3D_WORLD_SOFTWARE_SEARCH).click();
    }

    public void search3DWorldSoftwareTab(String software) {
        DomElement searchBar = findOnPage(_3D_WORLD_SOFTWARE_SEARCH);
        searchBar.click();
        searchBar.enterText(software);
    }

    public String get3DWorldSoftwareList() {
        return findOnPage(_3D_WORLD_SOFTWARE_LIST).getText();
    }

    // Affairs Interaction
    public String getAffairsLocationsShown() {
        return findOnPage(AFFAIRS_LOCATIONS_SHOWN).getText();
    }

}
