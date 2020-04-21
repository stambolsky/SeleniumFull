package com.stqa;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class Authorization extends TestBase {

    @Test
    public void testAuthorization() {
        driver.get("http://localhost:8888/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        Assert.assertTrue("Авторизация не выполнена",driver.findElement(By.cssSelector("a[title='Logout']")).isDisplayed());
    }
}
