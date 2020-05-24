package com.stqa.tests;

import com.stqa.pages.Navigation;
import org.junit.Test;

public class CheckingForStickersOnProducts extends TestBase {

    Navigation navigation;

    @Test
    public void testCheckingForStickersOnProducts() {
        navigation = new Navigation(driver);
        navigation.openMainPage()
                .checkStickersOnProducts();
    }
}
