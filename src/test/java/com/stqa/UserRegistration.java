package com.stqa;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class UserRegistration extends TestBase {

    @Test
    public void testUserRegistration() {
        driver.get("http://localhost:8888/litecart/en/");
        driver.findElement(By.xpath("//a[.='New customers click here']")).click();

        String email = "test"+1 + (int) (Math.random() * 100000)+"@test.com";
        String password = "123456";

        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Sergey");
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Tambolsky");

        Select selector = new Select(driver.findElement(By.xpath("//select[@name='country_code']")));
        selector.selectByVisibleText("United States");

        driver.findElement(By.xpath("//input[@name='address1']")).sendKeys("TestAddress");
        driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys("New York");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name='confirmed_password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@name='create_account']")).click();

        Assert.assertTrue("Пользователь не авторизован", driver.findElement(By.xpath("//div[@id='box-account']//a[.='Logout']")).isDisplayed());
        driver.findElement(By.xpath("//div[@id='box-account']//a[.='Logout']")).click();
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@name='login']")).click();
        Assert.assertTrue("Пользователь не авторизован", driver.findElement(By.xpath("//div[@id='box-account']//a[.='Logout']")).isDisplayed());
        driver.findElement(By.xpath("//div[@id='box-account']//a[.='Logout']")).click();
        Assert.assertTrue("Пользователь не вышел", driver.findElement(By.xpath("//input[@name='email']")).isDisplayed());
    }
}
