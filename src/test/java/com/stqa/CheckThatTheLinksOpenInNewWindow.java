package com.stqa;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CheckThatTheLinksOpenInNewWindow extends TestBase {

    @Test
    public void testCheckThatTheLinksOpenInNewWindow() {
        driver.get("http://localhost:8888/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(elementToBeClickable(driver.findElement(By.cssSelector(".widget"))));
        driver.findElement(By.xpath("//span[.='Countries']")).click();
        wait.until(elementToBeClickable(driver.findElement(By.xpath("//a[.='Belarus']"))));
        driver.findElement(By.xpath("//a[.='Belarus']")).click();

        String homeWindow = driver.getWindowHandle();
        String newWindow = "";
        wait.until(elementToBeClickable(driver.findElement(By.xpath("//input[@name='iso_code_2']/../a"))));

        List<WebElement> elements = driver.findElements(By.xpath("//td/a[not(contains(@href, '#'))]"));
        for (WebElement el : elements) {
            el.click();
            Set<String> handles = driver.getWindowHandles();
            for (String handle : handles) {
                if (!handle.equals(homeWindow)) {
                    driver.switchTo().window(handle);
                    newWindow = driver.getWindowHandle();
                }
            }
            if (driver.findElements(By.className("mw-wiki-logo")).size()>0 ||
                driver.findElements(By.className("default-logo")).size()>0) {
                Assert.assertTrue(driver.getWindowHandles().size()==2);
                driver.close();
                driver.switchTo().window(homeWindow);
            }
            Assert.assertTrue(driver.getWindowHandles().size()==1);
        }
    }
}
