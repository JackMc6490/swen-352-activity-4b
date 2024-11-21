package edu.rit.swen253.page.gpa;

import java.util.List;

import org.openqa.selenium.By;

import edu.rit.swen253.utils.DomElement;
/**
 * A View Object class that represents the <div> containing the <span> tags for calculated term and cumulative GPA
 * 
 * @author Jack McCarthy
 */
public class GPAResultsView {
    //the <div> tag
    private DomElement viewContainer;
    //the <span> tags
    private DomElement termGPA;
    private DomElement cumulativeGPA;

    /**
     * Uses the viewContainer to find the spans
     * @param viewContainer the <div> tag that contains the spans
     */
    public GPAResultsView(final DomElement viewContainer){
        this.viewContainer = viewContainer;
        setElements(viewContainer);
    }

    /**
     * Helper function to find the <span> tags
     * @param viewContainer
     */
    private void setElements(DomElement viewContainer){
        //to be honest I don't know why I have to do this or why it works
        //But something was going wrong without this
        List<DomElement> spans = viewContainer.findChildrenBy(By.cssSelector("span.results"));
        this.termGPA = spans.get(0);
        this.cumulativeGPA = spans.get(1);
    }

    /**
     * Gets the caulated Term GPA
     * @return the string term GPA
     */
    public String getTerm(){
        this.termGPA.scrollIntoView();
        setElements(viewContainer);
        return this.termGPA.getText();
    }

    /**
     * Gets the cumulative GPA
     * @return the string cumulative GPA
     */
    public String getCumulative(){
        setElements(viewContainer);
        this.termGPA.scrollIntoView();
        return this.cumulativeGPA.getText();
    }
}
