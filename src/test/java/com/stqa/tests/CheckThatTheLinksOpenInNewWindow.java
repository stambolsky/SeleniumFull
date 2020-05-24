package com.stqa.tests;

import com.stqa.pages.Navigation;
import org.junit.Test;

public class CheckThatTheLinksOpenInNewWindow extends TestBase {

    Navigation navigation;

    @Test
    public void testCheckThatTheLinksOpenInNewWindow() {
        navigation = new Navigation(driver);
        navigation.logInAsAdmin()
                .goToCountriesPage()
                .checkThatLinks();
    }
}
