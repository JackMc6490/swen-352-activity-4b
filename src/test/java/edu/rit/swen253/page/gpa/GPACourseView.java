package edu.rit.swen253.page.gpa;


import java.util.List;

import org.openqa.selenium.By;

import edu.rit.swen253.utils.DomElement;

public class GPACourseView {

    private DomElement courseName;
    private DomElement credits;
    private DomElement gradeDropdown;
    private DomElement pastGradeDropDown;
    private DomElement removeButton;
    

    public GPACourseView(final DomElement viewContainer){  
        if(viewContainer.hasChild(By.cssSelector("input.courseInputWidth"))){
            courseName = viewContainer.findChildBy(By.cssSelector("input.courseInputWidth"));
        }
        else{courseName=null;}

        if(viewContainer.hasChild(By.cssSelector("input.ng-valid"))){
            credits = viewContainer.findChildBy(By.cssSelector("input.ng-valid"));
        }else{credits=null;}

        if(viewContainer.hasChild(By.cssSelector("select.gradeDropDown"))){
            List<DomElement> dropdowns = viewContainer.findChildrenBy(By.cssSelector("select.gradeDropDown"));
            this.gradeDropdown = dropdowns.get(0);
            this.pastGradeDropDown = dropdowns.get(1);
        }else{gradeDropdown=null; pastGradeDropDown = null;}

        if(viewContainer.hasChild(By.cssSelector("span.hoverPointer"))){
            removeButton = viewContainer.findChildBy(By.cssSelector("span.hoverPointer"));
        }else{removeButton=null;}
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

    public void remove(){
        if(this.removeButton != null){
            this.removeButton.click();
        }
    }

    public void setGrade(String grade){
        if(this.gradeDropdown != null){
            changeDropdown(grade, this.gradeDropdown);
        }
    }

    public void setPastGrade(String grade){
        if(this.pastGradeDropDown != null){
            changeDropdown(grade, this.pastGradeDropDown);
        }
    }

    private void changeDropdown(String grade, DomElement element){
        List<DomElement> options = element.findChildrenBy(By.cssSelector("option"));
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
            case "D+":
                index = 9;
                break;
            case "D":
                index = 10;
                break;
            case "D-":
                index = 11;
                break;
            case "F":
                index = 12;
                break;
            case "S":
                index = 13;
                break;
            case "I":
                index = 14;
                break;
            case "W":
                index = 15;
                break;
            default:
                index = 1; 
                break;
            }
            options.get(index).click();
    }
    public String getName(){
        if(this.courseName != null){
            return this.courseName.getInputValue();
        }
        return null;
    }

    public String getCredits(){
        if(this.credits != null){
            return this.credits.getInputValue();
        }
        return null;
    }

    public String getGrade(){
        if(this.gradeDropdown != null){
            return this.gradeDropdown.getInputValue();
        }
        return null;
    }

    public String getPastGrade(){
        if(this.pastGradeDropDown != null){
            return this.pastGradeDropDown.getInputValue();
        }
        return null;
    }
}
