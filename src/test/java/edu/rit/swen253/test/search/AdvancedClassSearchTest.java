package edu.rit.swen253.test.search;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.rit.swen253.page.search.AdvancedSearchView;
import edu.rit.swen253.page.search.SearchResultView;
import edu.rit.swen253.page.search.TigerCenterClassSearchPage;
import edu.rit.swen253.test.AbstractWebTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdvancedClassSearchTest extends AbstractWebTest{

    private TigerCenterClassSearchPage searchPage;
    private AdvancedSearchView advancedView;

    @Test
    @Order(1)
    @DisplayName("Navigate to search page")
    void navigateToSearchPage() {
        searchPage = navigateToPage("https://tigercenter.rit.edu/tigerCenterApp/api/class-search", TigerCenterClassSearchPage::new);
        assertNotNull(searchPage);
    }

    @Test
    @Order(2)
    @DisplayName("Open Advanced search modal")
    void openAdvancedSearch() {
        advancedView = searchPage.openAdvancedSearch();
        assertNotNull(advancedView);
    }

    @Test
    @Order(3)
    @DisplayName("Set instructor name to 'Bryan', then save and search 'SWEN', should have 4 results") 
    void instructorSearch() {
        advancedView.setInstructor("Bryan");
        advancedView.saveOptions();
        advancedView.waitUntilGone();
        searchPage.search("SWEN");
        List<SearchResultView> results = searchPage.getResults();
        assertEquals(4, results.size());
        for (SearchResultView result : results) {
            assertEquals("Bryan Basham", result.getInstructor());
        }
    }

    @Test
    @Order(4)
    @DisplayName("Open Advanced search modal again")
    void reopenAdvancedSearch() {
        advancedView = searchPage.openAdvancedSearch();
        assertNotNull(advancedView);
    }

    @Test
    @Order(5)
    @DisplayName("Reset and search Ethics under GCCIS")
    void collegeSearch() {
        advancedView.reset();
        advancedView.setCollege("Golisano Col Comp&Info Science");
        advancedView.saveOptions();
        advancedView.waitUntilGone();
        searchPage.clear();
        searchPage.search("Ethics");
        List<SearchResultView> results = searchPage.getResults();
        assertEquals(3, results.size());
    }

}
