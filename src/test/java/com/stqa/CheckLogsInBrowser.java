package com.stqa;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.List;

public class CheckLogsInBrowser extends TestBase{

    @Test
    public void testCheckLogsInBrowser() {
        driver.get("http://localhost:8888/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.get("http://localhost:8888/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        List<WebElement> folders = driver.findElements(By.xpath("//i[@class='fa fa-folder']/../a"));
        for (WebElement folder : folders) {
            if (folders.size()==0) {
                break;
            }
            folder.click();
            folders = driver.findElements(By.xpath("//i[@class='fa fa-folder']/../a"));
        }

        List<WebElement> products = driver.findElements(By.xpath("//*[@class='dataTable']//img/../a"));
        for (int i=0;i < products.size(); i++) {
            products.get(i).click();
            driver.navigate().back();
            List<LogEntry> logs = driver.manage().logs().get(LogType.BROWSER).getAll();
            if (logs.size() > 0) {
                logs.forEach(System.out::println);
            }
            Assert.assertEquals(0, logs.size());
            products = driver.findElements(By.xpath("//*[@class='dataTable']//img/../a"));
        }
    }
}
