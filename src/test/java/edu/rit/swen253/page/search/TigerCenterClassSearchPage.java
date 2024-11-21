package edu.rit.swen253.page.search;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.utils.DomElement;

public class TigerCenterClassSearchPage extends AbstractAngularPage {
    private static final By SEARCH_FINDER = By.className("completer-input");
    private static final By SUBMIT_FINDER = By.className("classSearchSearchButtonGray");
    private static final By ADV_OPEN_FINDER = By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/class-search/div/div[2]/div[3]/div[1]/a");
    private static final By FIL_OPEN_FINDER = By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/class-search/div/div[2]/div[4]/div[3]");
    private static final By RESULT_FINDER = By.tagName("app-class-search-row");

    private DomElement searchInput;
    private DomElement submit;

    private List<SearchResultView> results;

    public TigerCenterClassSearchPage() {
        super("class-search");
        searchInput = findOnPage(SEARCH_FINDER);
        submit = findOnPage(SUBMIT_FINDER);
        results = new ArrayList<>();
    }

    private void regenResults() {
        results = findAllOnPage(RESULT_FINDER)
                .stream()
                .map(SearchResultView::new)
                .toList();
    }

    public List<SearchResultView> getResults() {
        regenResults();
        return results;
    }

    public void search(String query) {
        if (query != null) searchInput.enterText(query);
        submit.click();
        regenResults();
    }

    public AdvancedSearchView openAdvancedSearch() {
        findOnPage(ADV_OPEN_FINDER).click();
        return new AdvancedSearchView();
    }

    public FilterSearchView openFilterSearch() {
        findOnPage(FIL_OPEN_FINDER).click();
        return new FilterSearchView();
    }

}
