package edu.rit.swen253.page.gpa;

import java.util.List;

import org.openqa.selenium.By;

import edu.rit.swen253.utils.DomElement;

public class GPAButtonsView {

    private DomElement resetButton;
    private DomElement calculateButton;
    private DomElement addButton;

    public GPAButtonsView(DomElement viewContainer){
        this.calculateButton = viewContainer.findChildBy(By.cssSelector("button.primaryButton"));
        List<DomElement> secondaryButtons = viewContainer.findChildrenBy(By.cssSelector("button.secondaryButton"));
        this.resetButton = secondaryButtons.get(0);
        this.addButton = secondaryButtons.get(1);
    }

    public void clickAdd(){
        this.addButton.click();
    }

    public void clickCalculate(){
        this.calculateButton.click();
    }

    public void clickReset(){
        this.resetButton.click();
    }
}
