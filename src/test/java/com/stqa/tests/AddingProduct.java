package com.stqa.tests;

import com.stqa.pages.CatalogPage;
import com.stqa.pages.Navigation;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import java.io.File;

public class AddingProduct extends TestBase {

    Navigation navigation;
    CatalogPage catalogPage;

    @Test
    public void testAddingProduct() {
        navigation = new Navigation(driver);
        catalogPage = navigation.logInAsAdmin()
                .goToCatalogPage();
        catalogPage.clickAddNewProduct();
        String randomNumber = String.valueOf(1 + (int) (Math.random() * 1000));
        File file = new File("src/test/resources/MacBookPro.jpg");
        String absolutePath = String.valueOf(file.getAbsoluteFile());
        catalogPage.setFields(randomNumber, absolutePath)
                .pressSave();

        Assert.assertTrue("Продукт не добавлен",
                driver.findElement(By.xpath("//form[@name='catalog_form']//a[.='testProduct"+randomNumber+"']")).isDisplayed());
    }
}
