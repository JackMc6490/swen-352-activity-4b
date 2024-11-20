package edu.rit.swen253.page.gpa;

import java.util.List;

import org.openqa.selenium.By;

import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.utils.DomElement;

public class GPACalcPage extends AbstractAngularPage{

    final static By BUTTON_FINDER = By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/gpa-calc/div/div/div/div[5]");
    GPAButtonsView buttonsView;

    public GPACalcPage() {
        super("gpa-calc");
        this.buttonsView = new GPAButtonsView(findOnPage(BUTTON_FINDER));
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




}
