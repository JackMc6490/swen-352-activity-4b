package edu.rit.swen253.page.gpa;

import java.util.List;

import org.openqa.selenium.By;

import edu.rit.swen253.utils.DomElement;

public class GPAResultsView {
    
    private DomElement termGPA;
    private DomElement cumulativeGPA;

    public GPAResultsView(final DomElement viewContainer){
        List<DomElement> spans = viewContainer.findChildrenBy(By.cssSelector("span.results"));
        this.termGPA = spans.get(0);
        this.cumulativeGPA = spans.get(1);
    }

    public String getTerm(){
        return this.termGPA.getText();
    }

    public String getCumulative(){
        return this.cumulativeGPA.getText();
    }
}
