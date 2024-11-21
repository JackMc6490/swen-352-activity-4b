package edu.rit.swen253.test.search;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.rit.swen253.page.search.FilterSearchView;
import edu.rit.swen253.page.search.SearchResultView;
import edu.rit.swen253.page.search.TigerCenterClassSearchPage;
import edu.rit.swen253.test.AbstractWebTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FilteredClassSearchTest extends AbstractWebTest {

    private TigerCenterClassSearchPage searchPage;
    private FilterSearchView filterView;

    @Test
    @Order(1)
    @DisplayName("Navigate to search page")
    void navigateToSearchPage() {
        searchPage = navigateToPage("https://tigercenter.rit.edu/tigerCenterApp/api/class-search", TigerCenterClassSearchPage::new);
        assertNotNull(searchPage);
    }

    @Test
    @Order(2)
    @DisplayName("Search for 'SWEN 261'")
    void searchSWEN261() {
        searchPage.clear();
        searchPage.search("SWEN 261");
        List<SearchResultView> results = searchPage.getResults();
        assertEquals(9, results.size());
        for (SearchResultView result : results) {
            assertEquals("Introduction to Software Engineering", result.getName());
        }
    }

    @Test
    @Order(3)
    @DisplayName("Open Filter Dropdown menu")
    void openFilter() {
        filterView = searchPage.openFilterSearch();
        assertNotNull(filterView);
    }

    @Test
    @Order(4)
    @DisplayName("Filter by undergrad. should not change")
    void filterUndergrad() {
        filterView.selectUndergrad();
        List<SearchResultView> results = searchPage.getResults();
        assertEquals(9, results.size());
    }

    @Test
    @Order(5)
    @DisplayName("Unselect Undergrad filter and select grad. Should be no results")
    void filterGrad() {
        filterView.selectUndergrad();
        filterView.selectGrad();
        List<SearchResultView> results = searchPage.getResults();
        assertEquals(0, results.size());
    }

    @Test
    @Order(6)
    @DisplayName("Unselect Grad filter and select open. Should only show 6")
    void filterOpen() {
        filterView.selectGrad();
        filterView.selectOpen();
        List<SearchResultView> results = searchPage.getResults();
        assertEquals(6, results.size());
        for (SearchResultView result : results) {
            assertEquals("Open", result.getStatus());
        }
    }

    @Test
    @Order(7)
    @DisplayName("Unselect Open and set Instructor to 'Bryan Basham'")
    void filterInstructor() {
        filterView.selectOpen();
        filterView.setInstructor("Bryan Basham");
        List<SearchResultView> results = searchPage.getResults();
        assertEquals(1, results.size());
        assertEquals("Bryan Basham", results.get(0).getInstructor());
    }

}
