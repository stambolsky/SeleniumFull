package com.stqa.tests;

import com.stqa.pages.CatalogPage;
import com.stqa.pages.Navigation;
import org.junit.Test;

public class CheckLogsInBrowser extends TestBase {

    Navigation navigation;
    CatalogPage catalogPage;

    @Test
    public void testCheckLogsInBrowser() {
        navigation = new Navigation(driver);
        catalogPage = navigation.openPageCatalog();
        catalogPage.openFolders();
        catalogPage.openProductsAndCheckLogsBrowser();
    }
}
