package edu.rit.swen253.page.search;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import edu.rit.swen253.utils.DomElement;

public class AdvancedSearchView {

    private static final By ADVANCED_FINDER = By.tagName("advanced-search-dialog");
    private static final By COLLEGE_SELECT_FINDER = By.xpath("./mat-dialog-content/div/div[1]/div[4]/div/select");
    private static final By INSTRUCTOR_TBOX_FINDER = By.id("mat-input-5");
    private static final By SAVE_OPTIONS_FINDER = By.xpath("/html/body/div[3]/div[2]/div/mat-dialog-container/advanced-search-dialog/mat-dialog-actions/button[2]");


    private DomElement element;

    public AdvancedSearchView() {
        element = DomElement.findBy(ADVANCED_FINDER);
    }

    public void setCollege(String college) {
        Select select = new Select(element.findChildBy(COLLEGE_SELECT_FINDER).getWebElement());
        select.selectByVisibleText(college);
    }

    public void setInstructor(String name) {
        DomElement input = element.findChildBy(INSTRUCTOR_TBOX_FINDER);
        input.enterText(name);
    }

    public void saveOptions() {
        element.findChildBy(SAVE_OPTIONS_FINDER).click();
    }

    public void waitUntilGone() {
        element.waitUntilGone();
    }
}
