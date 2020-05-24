package com.stqa.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountPage extends FieldWorkerPage {

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    WebElement buttonLogout = driver.findElement(By.xpath("//div[@id='box-account']//a[.='Logout']"));
    WebElement inputEmail = driver.findElement(By.xpath("//input[@name='email']"));

    public MainPage logout() {
        clickElement(buttonLogout);
        Assert.assertTrue("Пользователь не вышел", inputEmail.isDisplayed());
        return new MainPage(driver);
    }
}
