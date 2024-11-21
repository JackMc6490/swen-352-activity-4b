package edu.rit.swen253.page.search;

import org.openqa.selenium.By;

import edu.rit.swen253.utils.DomElement;

public class SearchResultView {

    // Uses xpath relative to the element passed in constructor
    private static final By NAME_FINDER = By.xpath("./div/div[1]/div/div[1]/p/span[1]");
    private static final By STATUS_FINDER = By.xpath("./div/div[1]/div/div[2]/p/span[1]");
    private static final By COUNT_FINDER = By.xpath("./div/div[1]/div/div[2]/p/span[2]");
    private static final By INSTRUCTOR_FINDER = By.xpath("./div/div[1]/div/div[9]/div/div/div/a/p");

    private String name;
    private String instructor;
    private String status;
    private int filledSeats;
    private int totalSeats;

    public SearchResultView(DomElement element) {
        name = element.findChildBy(NAME_FINDER).getText();
        instructor = element.findChildBy(INSTRUCTOR_FINDER).getText();
        status = element.findChildBy(STATUS_FINDER).getText();
        String[] seats = element.findChildBy(COUNT_FINDER).getText().split("/");
        filledSeats = Integer.parseInt(seats[0]);
        totalSeats = Integer.parseInt(seats[1]);
    }

    public String getName() {
        return name;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getStatus() {
        return status;
    }

    public int getFilledSeats() {
        return filledSeats;
    }
    
    public int getSeatCount() {
        return totalSeats;
    }


}


