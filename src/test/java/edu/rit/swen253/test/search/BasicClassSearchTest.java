package edu.rit.swen253.test.search;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.rit.swen253.page.search.SearchResultView;
import edu.rit.swen253.page.search.TigerCenterClassSearchPage;
import edu.rit.swen253.test.AbstractWebTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BasicClassSearchTest extends AbstractWebTest{

    private TigerCenterClassSearchPage searchPage;

    @Test
    @Order(1)
    @DisplayName("Navigate to search page")
    void navigateToSearchPage() {
        searchPage = navigateToPage("https://tigercenter.rit.edu/tigerCenterApp/api/class-search", TigerCenterClassSearchPage::new);
        assertNotNull(searchPage);
    }


    @Test
    @Order(3)
    @DisplayName("Search for 'SWEN 352' in search bar")
    void searchSWEN352() {
        searchPage.search("SWEN 352");
        SearchResultView result = searchPage.getResults().get(0);
        assertAll(() -> {
            assertEquals("Software Testing", result.getName());
            assertEquals("Bryan Basham", result.getInstructor());
            assertEquals("Open", result.getStatus());
            assertEquals(38, result.getFilledSeats());
            assertEquals(40, result.getSeatCount());
        });
    }

    @Test
    @Order(4)
    @DisplayName("Clears and then searches for 'SWEN 261'")
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
    @Order(5)
    @DisplayName("Clear and then search for 'wadawdaw'. There should be no result")
    void searchInvalid() {
        searchPage.clear();
        searchPage.search("wadawdaw");
        List<SearchResultView> results = searchPage.getResults();
        assertEquals(0, results.size());
    }
}
