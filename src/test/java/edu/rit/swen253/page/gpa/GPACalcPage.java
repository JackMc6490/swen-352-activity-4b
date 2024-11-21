package edu.rit.swen253.page.gpa;

import java.util.List;

import org.openqa.selenium.By;

import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.utils.DomElement;

/**
 * The GPA Calculator page of the TigerCenter Angular web application.
 * 
 * @author Jack McCarthy
 */
public class GPACalcPage extends AbstractAngularPage{

    //All xpaths aquired from "copy full xpath" on edge's developer tools

    final static By BUTTON_FINDER = By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/gpa-calc/div/div/div/div[5]");
    //The three buttons on the bottom of the page
    private GPAButtonsView buttonsView;

    final static By RESULTS_FINDER = By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/gpa-calc/div/div/div/div[7]");
    //The results for both term and cumulative GPA
    private GPAResultsView resultsView;

    final static By CREDITS_FINDER = By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/gpa-calc/div/div/div/div[2]/div[1]/div/input");
    //The input element for your earned credits
    private DomElement creditsInput;

    final static By CURRENT_FINDER = By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/gpa-calc/div/div/div/div[2]/div[2]/div/input");
    //The input field for your current GPA
    private DomElement currentGPAInput;

    final static By TOGGLE_FINDER = By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/gpa-calc/div/div/div/div[2]/div[3]/div/mat-checkbox/label/span[1]");
    //The checkbox for grad students
    private DomElement gradToggle;

    /**
     * Uses the xpaths to field all relevent elements
     */
    public GPACalcPage() {
        super("gpa-calc");
        this.buttonsView = new GPAButtonsView(findOnPage(BUTTON_FINDER));
        this.resultsView = new GPAResultsView(findOnPage(RESULTS_FINDER));
        this.creditsInput = findOnPage(CREDITS_FINDER);
        this.currentGPAInput = findOnPage(CURRENT_FINDER);
        this.gradToggle = findOnPage(TOGGLE_FINDER);
    }

    /**
     * Finds all courses on the page and converts them into GPACourseView objects 
     * @return a list of all courses on the page converted into GPACourseView objects
     */
    public List<GPACourseView> getCourses(){
      List<DomElement> elementList = findAllOnPage(By.cssSelector("div.courseRow"));
      List<GPACourseView> courseList = elementList.stream().map(GPACourseView::new).toList();
      return courseList;
    }

    /**
     * Clicks the add button
     */
    public void add(){
      this.buttonsView.clickAdd();
    }

    /**
     * Clicks the calculate button
     */
    public void calculate(){
      this.buttonsView.clickCalculate();
    }

    /**
     * Clicks the reset button
     */
    public void reset(){
      this.buttonsView.clickReset();
    }

    /**
     * Clicks the toggle button
     */
    public void toggle(){
      this.gradToggle.click();
    }

    /**
     * Inputs the specifed credit value into the correct input field
     * @param credits the credite value to be inputted
     */
    public void setCredits(String credits){
        this.creditsInput.clear();
        this.creditsInput.enterText(credits);
    }
    /**
     * Inputs the specifed GPA value into the correct input field
     * @param gpa the gpa value to be inputted
     */
    public void setCurrentGPA(String gpa){
      this.currentGPAInput.clear();
      this.currentGPAInput.enterText(gpa);
    }

    /**
     * Gets the inputted credit value
     * @return the credits value as a string
     */
    public String getCredits(){
      return this.creditsInput.getInputValue();
    }

    /**
     * Gets the inputted GPA value
     * @return the GPA value as a string
     */
    public String getCurrentGPA(){
      return this.currentGPAInput.getInputValue();
    }

    /**
     * Returns the calculated Term GPA
     * @return the term GPA as a string
     */
    public String getTermGPA(){
      return this.resultsView.getTerm();
    }
    
    /**
     * Returns the calculated cumulative GPA
     * @return the cumulative GPA as a string
     */
    public String getCumulativeGPA(){
      return this.resultsView.getCumulative();
    }

}
