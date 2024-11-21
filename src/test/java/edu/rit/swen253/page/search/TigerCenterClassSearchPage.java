package edu.rit.swen253.page.search;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.utils.DomElement;

public class TigerCenterClassSearchPage extends AbstractAngularPage {
    private static final By SEARCH_FINDER = By.className("completer-input");
    private static final By SUBMIT_FINDER = By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/class-search/div/div[2]/form/div/button");
    private static final By CLEAR_FINDER = By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/class-search/div/div[2]/form/div/div[2]/button");
    private static final By ADV_OPEN_FINDER = By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/class-search/div/div[2]/div[3]/div[1]/a");
    private static final By FIL_OPEN_FINDER = By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/class-search/div/div[2]/div[4]/div[3]");
    private static final By RESULT_FINDER = By.tagName("app-class-search-row");

    private DomElement searchInput;
    private DomElement submit;
    private DomElement clear;

    private List<SearchResultView> results;

    public TigerCenterClassSearchPage() {
        super("class-search");
        searchInput = findOnPage(SEARCH_FINDER);
        clear = findOnPage(CLEAR_FINDER);
        new Select(findOnPage(By.className("bigTermPickerWidthFix"))
            .getWebElement())
            .selectByIndex(1);
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
        // Lazily loads submit button because its only available when theres text in the search bar
        if (submit == null) submit = findOnPage(SUBMIT_FINDER);
        submit.click();
        regenResults();
    }

    public void clear() {
        clear.click();
        submit = null;
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
