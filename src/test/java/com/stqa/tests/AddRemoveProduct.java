package com.stqa.tests;

import com.stqa.pages.MainPage;
import com.stqa.pages.Navigation;
import org.junit.Assert;
import org.junit.Test;

public class AddRemoveProduct extends TestBase {

    Navigation navigation;
    MainPage mainPage;

    @Test
    public void testAddRemoveProduct() throws InterruptedException {
        navigation = new Navigation(driver);
        navigation.openMainPage();
        int products = 3;
        mainPage.addProductAndReturnHome(products);
        Assert.assertTrue("Количество товаров не совпадает",
                Integer.parseInt(mainPage.getCountCart()) == products);
        mainPage.removeProductInCart(products);
        Assert.assertTrue(mainPage.checkVisibleTextNoProductInCart());
    }
}
