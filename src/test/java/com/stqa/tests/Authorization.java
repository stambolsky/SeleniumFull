package com.stqa.tests;

import com.stqa.pages.Navigation;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class Authorization extends TestBase {

    Navigation navigation = new Navigation(driver);

    @Test
    public void testAuthorization() {
        navigation.logInAsAdmin();
        Assert.assertTrue("Авторизация не выполнена",driver.findElement(By.cssSelector("a[title='Logout']")).isDisplayed());
    }
}
