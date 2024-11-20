package edu.rit.swen253.page.gpa;

import java.util.List;

import org.openqa.selenium.By;

import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.utils.DomElement;

public class GPACalcPage extends AbstractAngularPage{

    final static By BUTTON_FINDER = By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/gpa-calc/div/div/div/div[5]");
    private GPAButtonsView buttonsView;

    final static By RESULTS_FINDER = By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/gpa-calc/div/div/div/div[7]");
    private GPAResultsView resultsView;

    final static By CREDITS_FINDER = By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/gpa-calc/div/div/div/div[2]/div[1]/div/input");
    private DomElement creditsInput;

    final static By CURRENT_FINDER = By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/gpa-calc/div/div/div/div[2]/div[2]/div/input");
    private DomElement currentGPAInput;

    final static By TOGGLE_FINDER = By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/gpa-calc/div/div/div/div[2]/div[3]/div/mat-checkbox/label/span[1]");
    private DomElement gradToggle;

    public GPACalcPage() {
        super("gpa-calc");
        this.buttonsView = new GPAButtonsView(findOnPage(BUTTON_FINDER));
        this.resultsView = new GPAResultsView(findOnPage(RESULTS_FINDER));
        this.creditsInput = findOnPage(CREDITS_FINDER);
        this.currentGPAInput = findOnPage(CURRENT_FINDER);
        this.gradToggle = findOnPage(TOGGLE_FINDER);
    }

    public List<GPACourseView> getCourses(){
      List<DomElement> elementList = findAllOnPage(By.cssSelector("div.courseRow"));
      List<GPACourseView> courseList = elementList.stream().map(GPACourseView::new).toList();
      return courseList;
    }

    public void add(){
      this.buttonsView.clickAdd();
    }

    public void calculate(){
      this.buttonsView.clickCalculate();
    }

    public void toggle(){
      this.gradToggle.click();
    }

    public void setCredits(String credits){
        this.creditsInput.clear();
        this.creditsInput.enterText(credits);
    }

    public void setCurrentGPA(String gpa){
      this.currentGPAInput.clear();
      this.currentGPAInput.enterText(gpa);
    }

    public String getCredits(){
      return this.creditsInput.getInputValue();
    }

    public String getCurrentGPA(){
      return this.currentGPAInput.getInputValue();
    }

    public String getTermGPA(){
      return this.resultsView.getTerm();
  }

  public String getCumulativeGPA(){
      return this.resultsView.getCumulative();
  }




}
