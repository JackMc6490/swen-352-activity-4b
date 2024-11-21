package edu.rit.swen253.page.search;

import org.openqa.selenium.By;

import edu.rit.swen253.utils.DomElement;

public class FilterSearchView {

    private static final By FILTER_FINDER = By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/class-search/div/div[2]/div[4]/div[3]/div/form");
    private static final By INSTRUCTOR_FILTER = By.id("mat-input-0");

    private DomElement element;

    public FilterSearchView() {
        element = DomElement.findBy(FILTER_FINDER);
    }

    public void setInstructor(String instructor) {
        element.findChildBy(INSTRUCTOR_FILTER).enterText(instructor);
    }

    public void selectOpen() {
        element.findChildBy(By.id("mat-checkbox-44")).click();
    }

    public void selectUndergrad() {
        element.findChildBy(By.id("mat-checkbox-55")).click();
    }
    
    public void selectGrad() {
        element.findChildBy(By.id("mat-checkbox-56")).click();
    }

}
