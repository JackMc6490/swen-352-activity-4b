package edu.rit.swen253.page.halappnav;

import org.openqa.selenium.By;

import edu.rit.swen253.utils.DomElement;

public class TestfulHoursAndLocationsPage extends SimpleHoursAndLocationsPage {
    By DINING_TAB = By.id("dining");
    By DINING_SATURDAY_TAB = By.id("dining-saturday");
    By BEANZ_ACCORDION = By.id("beanz-accordion");
    By BEANZ_HOURS = By.id("beanz-hours");
    By LABS_TAB = By.id("labs");
    By LABS_SATURDAY_TAB = By.id("labs-saturday");
    By LABS_FILTER_PRINTER = By.id("labs-filter-printer");
    By LABS_LOCATIONS_SHOWN = By.id("labs-locations-shown");
    By _3D_WORLD_ACCORDION = By.id("3d-world-accordion");
    By _3D_WORLD_DESCRIPTION = By.id("3d-world-description");
    By _3D_WORLD_PRINTER_ICON = By.id("3d-world-printer-icon");
    By _3D_WORLD_SOFTWARE_TAB = By.id("3d-world-software");
    By _3D_WORLD_SOFTWARE_SEARCH = By.id("3d-world-software-search");
    By _3D_WORLD_SOFTWARE_LIST = By.id("3d-world-software-list");
    By AFFAIRS_TAB = By.id("affairs");
    By AFFAIRS_LOCATIONS_SHOWN = By.id("affairs-locations-shown");
    
    DomElement diningServicesTab = findOnPage(DINING_TAB);
    DomElement diningSaturdayTab = findOnPage(DINING_SATURDAY_TAB);
    DomElement beanzAccordion = findOnPage(BEANZ_ACCORDION);
    DomElement beanzHours = findOnPage(BEANZ_HOURS);
    DomElement labsTab = findOnPage(LABS_TAB);
    DomElement labsSaturdayTab = findOnPage(LABS_SATURDAY_TAB);
    DomElement labsFilterPrinter = findOnPage(LABS_FILTER_PRINTER);
    DomElement labsLocationsShown = findOnPage(LABS_LOCATIONS_SHOWN);
    DomElement _3dWorldAccordion = findOnPage(_3D_WORLD_ACCORDION);
    DomElement _3dWorldDescription = findOnPage(_3D_WORLD_DESCRIPTION);
    DomElement _3dWorldPrinterIcon = findOnPage(_3D_WORLD_PRINTER_ICON);
    DomElement _3dWorldSoftwareTab = findOnPage(_3D_WORLD_SOFTWARE_TAB);
    DomElement _3dWorldSoftwareSearch = findOnPage(_3D_WORLD_SOFTWARE_SEARCH);
    DomElement _3dWorldSoftwareList = findOnPage(_3D_WORLD_SOFTWARE_LIST);
    DomElement affairsTab = findOnPage(AFFAIRS_TAB);
    DomElement affairsLocationsShown = findOnPage(AFFAIRS_LOCATIONS_SHOWN);

    public TestfulHoursAndLocationsPage() {
        super();
    }

    public enum HALDateTab {
        MON,
        TUE,
        WED,
        THU,
        FRI,
        SAT,
        SUN;
    }

    public enum HALMainTab {
        DINING,
        LABS,
        AFFAIRS;
    }

    public void switchMainTab(HALMainTab mainTab) {
        switch (mainTab) {
            case DINING:
                diningServicesTab.click();
                break;
            case LABS:
                labsTab.click();
                break;
            case AFFAIRS:
                affairsTab.click();
                break;
        }
    }

    public void switchDateTab(HALDateTab dateTab) {
        switch (dateTab) {
            case MON:
                break;
            case TUE:
                break;
            case WED:
                break;
            case THU:
                break;
            case FRI:
                break;
            case SAT:
                // lets hope this isn't tested on any other day
                // okay i thought I was being clever with the 
                // enums but I'm not sure how to test this
                diningSaturdayTab.click();
                labsSaturdayTab.click();
                break;
            case SUN:
                break;
        }
    }

    public void clickOnBeanz() {
        beanzAccordion.click();
    }

    public void filterLabsByPrinter(){
        labsFilterPrinter.click();
    }

    public void clickOn3DWorld() {
        _3dWorldAccordion.click();
    }

    public void clickOn3DWorldSoftwareTab() {
        _3dWorldSoftwareTab.click();
    }

    public void clickOn3DWorldSoftwareTabSearchBar() {
        _3dWorldSoftwareSearch.click();
    }

    public void search3DWorldSoftwareTab(String software) {
        _3dWorldSoftwareSearch.enterText(software);
    }







}
