package edu.rit.swen253.test.gpa;

import static edu.rit.swen253.utils.TimingUtils.sleep;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.rit.swen253.page.gpa.GPACalcPage;
import edu.rit.swen253.page.gpa.GPACourseView;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;
/**
 * A test class that test the various ways you can calculate GPA using the calculator
 *
 * @author Jack McCarthy
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GPACalculateTest extends AbstractWebTest{
    TigerCenterHomePage homePage;
    GPACalcPage calcPage;
    List<GPACourseView> courses;


    //
    // Test sequence
    //

    @Test
    @Order(1)
    @DisplayName("First, navigate to the Tiger Center Home page.")
    void homeNavigation(){
        this.homePage = navigateToPage("https://tigercenter.rit.edu", TigerCenterHomePage::new);
        assertNotNull(homePage);
    }   

    @Test
    @Order(2)
    @DisplayName("Second, click on the the GPA Calculator button")
    void calcNavigation(){
        homePage.selectGPACalculator();
        homePage.waitUntilGone();
        this.calcPage = assertNewPage(GPACalcPage::new);
        assertNotNull(calcPage);
    }

    @Test
    @Order(3)
    @DisplayName("Third, set the current RIT credits input to 18")
    void settingCredits(){
        this.calcPage.setCredits("18");
        assertEquals("18", this.calcPage.getCredits());
    }

    @Test
    @Order(4)
    @DisplayName("Then set the current GPA field to 3.50")
    void settingCurrentGPA(){
        this.calcPage.setCurrentGPA("3.50");
        assertEquals("3.50", this.calcPage.getCurrentGPA());
    }

    @Test
    @Order(5)
    @DisplayName("Add one course with a grade of A")
    void setFirstCourse(){
        this.calcPage.add();
        this.calcPage.add();
        this.courses = this.calcPage.getCourses();
        GPACourseView firstCourse = courses.get(0);
        firstCourse.setGrade("A");
        assertEquals("1: 4.00", firstCourse.getGrade());
    }

    @Test
    @Order(6)
    @DisplayName("Add a second course with a grade of B")
    void setSecondCourse(){
        GPACourseView secondCourse = courses.get(1);
        secondCourse.setGrade("B");
        assertEquals("4: 3.00", secondCourse.getGrade());
    }

    @Test
    @Order(7)
    @DisplayName("A a third course with no grade")
    void setThirdCourseWithNoGrade(){
        GPACourseView thirdCourse = courses.get(2);
        assertEquals("0: ", thirdCourse.getGrade());
    }

    @Test
    @Order(8)
    @DisplayName("Validate GPA does not properly calculate without a grade for every course")
    void caculateFirstTime(){
        this.calcPage.calculate();
        assertAll("group assertions"
        , () -> assertEquals("-",this.calcPage.getTermGPA())
        , () -> assertEquals("-",this.calcPage.getCumulativeGPA())
        );
    }

    @Test
    @Order(9)
    @DisplayName("Add a grade to the course that is missing one")
    void addGradeToThirdCourse(){
        GPACourseView thirdCourse = courses.get(2);
        thirdCourse.setGrade("C");
        assertEquals("7: 2.00", thirdCourse.getGrade());
    }

    @Test
    @Order(10)
    @DisplayName("Recalculate GPA and validate it properly calculates this time")
    void caculateSecondTime(){
        this.calcPage.calculate();
        assertAll("group assertions"
        , () -> assertEquals("3.00",this.calcPage.getTermGPA())
        , () -> assertEquals("3.33",this.calcPage.getCumulativeGPA())
        );
    }

    @Test
    @Order(11)
    @DisplayName("Add a past grade of B to the first course")
    void addPastGrade(){
        GPACourseView firstCourse = courses.get(0);
        firstCourse.setPastGrade("B");
        assertEquals("4: 3.00", firstCourse.getPastGrade());
    }

    @Test
    @Order(12)
    @DisplayName("Recalculate GPA and validate cumulative GPA has increased")
    void caculateThirdTime(){
        this.calcPage.calculate();
        assertAll("group assertions"
        , () -> assertEquals("3.00",this.calcPage.getTermGPA())
        , () -> assertEquals("3.38",this.calcPage.getCumulativeGPA())
        );
    }

    @Test
    @Order(13)
    @DisplayName("Set the grad student toggle to on and validate this causes GPA to decrease back to normal")
    void toggleAndRecalculate(){
        this.calcPage.toggle();
        this.calcPage.calculate();
        assertAll("group assertions"
        , () -> assertEquals("3.00",this.calcPage.getTermGPA())
        , () -> assertEquals("3.33",this.calcPage.getCumulativeGPA())
        );
    }

    @Test
    @Order(14)
    @DisplayName("Delete the third course and validate that causes GPA to increase")
    void deleteAndRecalcuate(){
        GPACourseView thirdCourse = courses.get(2);
        thirdCourse.remove();
        this.calcPage.calculate();
        assertAll("group assertions"
        , () -> assertEquals("3.50",this.calcPage.getTermGPA())
        , () -> assertEquals("3.50",this.calcPage.getCumulativeGPA())
        );
    }

    @Test
    @Order(15)
    @DisplayName("Reset and validate")
    void testingReset(){
        this.calcPage.reset();
        this.courses = this.calcPage.getCourses();
        GPACourseView firstCourse = courses.get(0);
        firstCourse.setGrade("A");;
        this.calcPage.calculate();
        assertAll("group assertions"
        , () -> assertEquals("4.00",this.calcPage.getTermGPA())
        , () -> assertEquals("4.00",this.calcPage.getCumulativeGPA())
        );
    }


}
