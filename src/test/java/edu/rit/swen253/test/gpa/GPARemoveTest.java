package edu.rit.swen253.test.gpa;

import static edu.rit.swen253.utils.TimingUtils.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.rit.swen253.page.gpa.GPACalcPage;
import edu.rit.swen253.page.gpa.GPACourseView;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GPARemoveTest extends AbstractWebTest{
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
    @DisplayName("Third, add two courses to the page for a total of three")
    void addTwoCourses(){
        this.calcPage.add();
        this.calcPage.add();
        this.courses = this.calcPage.getCourses();
        assertEquals(3, this.courses.size()/2);
        //Here, and in all other places we do so, we divide by
        //three to get the correct number of classes b/c every
        //course gets two elements on the page
        //one visable and one hidden
    }

    @Test
    @Order(4)
    @DisplayName("Then, remove the third course and validate it has been removed correctly")
    void removeThirdCourse(){
        GPACourseView thirdCourse = this.courses.get(2);
        thirdCourse.remove();
        this.courses = this.calcPage.getCourses();
        assertEquals(2, this.courses.size()/2);
    }

    @Test
    @Order(5)
    @DisplayName("Do the same with the second course")
    void removeSecondCourse(){
        GPACourseView secondCourse = this.courses.get(1);
        secondCourse.remove();
        this.courses = this.calcPage.getCourses();
        assertEquals(1, this.courses.size()/2);
    }

    @Test
    @Order(6)
    @DisplayName("Do the same with last course remaing, bringing our total to 0")
    void removeFirst(){
        GPACourseView firstCourse = this.courses.get(0);
        firstCourse.remove();
        this.courses = this.calcPage.getCourses();
        assertEquals(0, this.courses.size());
        //we don't have to divide by zero here b/c no hidden elements are present
    }



}
