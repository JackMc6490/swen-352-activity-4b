package edu.rit.swen253.page.gpa;

import java.util.List;

import org.openqa.selenium.By;

import edu.rit.swen253.utils.DomElement;
/**
 * A View Object class that represents the <div> containing the three buttons at the bottom of the calulator
 * 
 * @author Jack McCarthy
 */
public class GPAButtonsView {
    //Three dom elements, one for each button
    private DomElement resetButton;
    private DomElement calculateButton;
    private DomElement addButton;

    /**
     * Uses the passed in domElement to find all three buttons on the page
     * @param viewContainer
     */
    public GPAButtonsView(DomElement viewContainer){
        this.calculateButton = viewContainer.findChildBy(By.cssSelector("button.primaryButton"));
        List<DomElement> secondaryButtons = viewContainer.findChildrenBy(By.cssSelector("button.secondaryButton"));
        this.resetButton = secondaryButtons.get(0);
        this.addButton = secondaryButtons.get(1);
    }
    
    /**
     * Clicks the add button
     */
    public void clickAdd(){
        this.addButton.click();
    }

    /**
     * Clicks the calculate button
     */
    public void clickCalculate(){
        this.calculateButton.click();
    }

    /**
     * Clicks the reset button
     */
    public void clickReset(){
        this.resetButton.click();
    }
}
