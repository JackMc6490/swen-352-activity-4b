package edu.rit.swen253.test.gpa;


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
 * A test class that tests adding classes to the calculator as well as setting the information
 * (Grade, Credits, Name, and Past Grade) of those courses
 *
 * @author Jack McCarthy
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GPAAddTest extends AbstractWebTest{
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
    @DisplayName("Third, set the information of the first course")
    void initializeFirstCourse(){
        this.courses = this.calcPage.getCourses();
        GPACourseView firstCourse = this.courses.get(0);
        firstCourse.setName("SWEN-262");
        firstCourse.setCredits("3");
        firstCourse.setGrade("B+");
        assertAll("group assertions"
          , () -> assertEquals("SWEN-262",firstCourse.getName())
          , () -> assertEquals("3",firstCourse.getCredits())
          , () -> assertEquals("3: 3.33", firstCourse.getGrade())
        );
    }

    @Test
    @Order(4)
    @DisplayName("Then, add a second course and set it's information")
    void addSecondCourse(){
        calcPage.add();
        this.courses = this.calcPage.getCourses();
        GPACourseView secondCourse = this.courses.get(1);
        secondCourse.setName("PHYS-211");
        secondCourse.setCredits("4");
        secondCourse.setGrade("C+");
        secondCourse.setPastGrade("D+");
        assertAll("group assertions"
        , () -> assertEquals("PHYS-211",secondCourse.getName())
        , () -> assertEquals("4",secondCourse.getCredits())
        , () -> assertEquals("6: 2.33", secondCourse.getGrade())
        , () -> assertEquals("9: 1.33", secondCourse.getPastGrade())
      );
    }

    @Test
    @Order(5)
    @DisplayName("Finally, add a third course and validate the page contains the correct number of courses")
    void threeTotalCourses(){
        calcPage.add();
        this.courses = this.calcPage.getCourses();
        //We divide by two here b/c every course has two elemenst associeted with it
        //One visible and one hidden
        assertEquals(3, this.courses.size()/2);
    }








}
