package com.stqa;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class AddingProduct extends TestBase {

    @Test
    public void testAddingProduct() {
        driver.get("http://localhost:8888/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.xpath("//*[.='Catalog']/..")).click();
        driver.findElement(By.xpath("//a[.=' Add New Product']")).click();
        driver.findElement(By.xpath("//*[@name='status']")).click();
        String randomNumber = String.valueOf(1 + (int) (Math.random() * 1000));
        driver.findElement(By.xpath("//input[@name='name[en]']")).sendKeys("testProduct" + randomNumber);
        driver.findElement(By.xpath("//input[@name='code']")).sendKeys(randomNumber);
        driver.findElement(By.xpath("//input[@data-name='[Root]']")).click();
        driver.findElement(By.xpath("//input[@value='1-3']")).click();
        driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys("100");
        File file = new File("src/test/resources/MacBookPro.jpg");
        String absolutepath = String.valueOf(file.getAbsoluteFile());
        driver.findElement(By.xpath("//input[@name='new_images[]']")).sendKeys(absolutepath);
        driver.findElement(By.xpath("//input[@name='date_valid_from']")).sendKeys("05.05.2020");
        driver.findElement(By.xpath("//input[@name='date_valid_to']")).sendKeys("05.05.2021");

        driver.findElement(By.xpath("//a[.='Information']")).click();
        Select manufacturerSelector = new Select(driver.findElement(By.xpath("//select[@name='manufacturer_id']")));
        manufacturerSelector.selectByVisibleText("ACME Corp.");
        driver.findElement(By.xpath("//input[@name='keywords']")).sendKeys("Apple");
        driver.findElement(By.xpath("//input[@name='short_description[en]']")).sendKeys("Apple MacBookPro");
        driver.findElement(By.xpath("//input[@name='head_title[en]']")).sendKeys("Apple");
        driver.findElement(By.xpath("//input[@name='meta_description[en]']")).sendKeys("description");
        driver.findElement(By.xpath("//input[@name='meta_keywords[en]']")).sendKeys("keywords");

        driver.findElement(By.xpath("//a[.='Prices']")).click();
        driver.findElement(By.xpath("//input[@name='purchase_price']")).sendKeys("50");
        driver.findElement(By.xpath("//input[@name='prices[USD]']")).sendKeys("50");
        driver.findElement(By.xpath("//input[@name='prices[EUR]']")).sendKeys("45");

        driver.findElement(By.xpath("//button[@name='save']")).click();

        Assert.assertTrue("Продукт не добавлен",
                driver.findElement(By.xpath("//form[@name='catalog_form']//a[.='testProduct"+randomNumber+"']")).isDisplayed());
    }
}
