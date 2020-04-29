package com.stqa;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckingForStickersOnProducts extends TestBase {

    @Test
    public void testAuthorization() {
        driver.get("http://localhost:8888/litecart/en/");
        List<WebElement> products = driver.findElements(By.cssSelector("li.product"));
        for (WebElement product : products) {
            int stickers = product.findElements(By.cssSelector(".sticker")).size();
            Assert.assertTrue("Количество стикеров у товара - "+stickers+" ,должно быть равно 1",stickers == 1 );
        }
    }
}
