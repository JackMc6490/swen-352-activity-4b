package edu.rit.swen253.page.gpa;

import java.util.List;

import org.openqa.selenium.By;

import edu.rit.swen253.utils.DomElement;

public class GPAResultsView {
    
    private DomElement viewContainer;
    private DomElement termGPA;
    private DomElement cumulativeGPA;

    public GPAResultsView(final DomElement viewContainer){
        this.viewContainer = viewContainer;
        setElements(viewContainer);
    }

    private void setElements(DomElement viewContainer){
        List<DomElement> spans = viewContainer.findChildrenBy(By.cssSelector("span.results"));
        this.termGPA = spans.get(0);
        this.cumulativeGPA = spans.get(1);
    }

    public String getTerm(){
        this.termGPA.scrollIntoView();
        setElements(viewContainer);
        return this.termGPA.getText();
    }

    public String getCumulative(){
        setElements(viewContainer);
        this.termGPA.scrollIntoView();
        return this.cumulativeGPA.getText();
    }
}
