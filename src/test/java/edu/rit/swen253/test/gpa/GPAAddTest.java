package edu.rit.swen253.test.gpa;


import static edu.rit.swen253.utils.TimingUtils.sleep;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.rit.swen253.page.gpa.GPACalcPage;
import edu.rit.swen253.page.gpa.GPACourseView;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GPAAddTest extends AbstractWebTest{
    TigerCenterHomePage homePage;
    GPACalcPage calcPage;

    @Test
    @Order(1)
    void homeNavigation(){
        this.homePage = navigateToPage("https://tigercenter.rit.edu", TigerCenterHomePage::new);
        assertNotNull(homePage);
    }

    @Test
    @Order(2)
    void calcNavigation(){
        homePage.selectGPACalculator();
        homePage.waitUntilGone();
        this.calcPage = assertNewPage(GPACalcPage::new);
        assertNotNull(calcPage);
    }

    @Test
    @Order(3)
    void initializeFirstCourses(){
        List<GPACourseView> courses = this.calcPage.getCourses();
        GPACourseView firstCourse = courses.getFirst();
        firstCourse.setName("SWEN-262");
        firstCourse.setCredits("4");
        firstCourse.setGrade("B+");
        assertAll("group assertions"
          , () -> assertEquals("SWEN-262",firstCourse.getName())
          , () -> assertEquals("4",firstCourse.getCredits())
          , () -> assertEquals("3: 3.33", firstCourse.getGrade())
        );
    }

    @Test
    @Order(4)
    void addSecondCourse(){
        calcPage.add();
        List<GPACourseView> courses = this.calcPage.getCourses();
        GPACourseView secondCourse = courses.get(1);
        secondCourse.setName("SWEN-352");
        secondCourse.setCredits("3");
        secondCourse.setGrade("C-");
        assertAll("group assertions"
        , () -> assertEquals("SWEN-352",secondCourse.getName())
        , () -> assertEquals("3",secondCourse.getCredits())
        , () -> assertEquals("8: 1.67", secondCourse.getGrade())
      );
    }

    @Test
    @Order(5)
    void totalCourses(){
        calcPage.add();
        List<GPACourseView> courses = this.calcPage.getCourses();
        assertEquals(3, courses.size()/2);
    }






}
