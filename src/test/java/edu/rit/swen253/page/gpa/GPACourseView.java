package edu.rit.swen253.page.gpa;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import edu.rit.swen253.utils.DomElement;

public class GPACourseView {

    DomElement courseName;
    DomElement credits;
    DomElement gradeDropdown;

    public GPACourseView(final DomElement viewContainer){  
        if(viewContainer.hasChild(By.cssSelector("input.courseInputWidth"))){
            courseName = viewContainer.findChildBy(By.cssSelector("input.courseInputWidth"));
        }
        else{courseName=null;}

        if(viewContainer.hasChild(By.cssSelector("input.ng-valid"))){
            credits = viewContainer.findChildBy(By.cssSelector("input.ng-valid"));
        }else{credits=null;}

        if(viewContainer.hasChild(By.cssSelector("select.gradeDropDown"))){
            gradeDropdown = viewContainer.findChildBy(By.cssSelector("select.gradeDropDown"));
        }else{gradeDropdown=null;}
    }

    public void setName(String name){
        if(courseName != null){
            courseName.enterText(name);
        }
    }

    public void setCredits(String newCredits){
        if(this.credits != null){
            this.credits.clear();
            this.credits.enterText(newCredits);
        }
    }

    public void setGrade(String grade){
        List<DomElement> options = this.gradeDropdown.findChildrenBy(By.cssSelector("option"));
        int index;
        switch (grade) {
            case "A":
                index = 1;
                break;
            case "A-":
                index = 2;
                break;
            case "B+":
                index = 3;
                break;
            case "B":
                index = 4;
                break;
            case "B-":
                index = 5;
                break;
            case "C+":
                index = 6;
                break;
            case "C":
                index = 7;
                break;
            case "C-":
                index = 8;
                break;
        
            default:
                index = 1; 
                break;
        }
        options.get(index).click();

    }
}
