package com.stqa;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class AddRemoveProduct extends TestBase {

    @Test
    public void testAddRemoveProduct() throws InterruptedException {
        driver.get("http://localhost:8888/litecart/en/");
        int products = 10;
        addProductAndReturnHome(products);
        Assert.assertTrue("Количество товаров не совпадает",
                Integer.parseInt(driver.findElement(By.cssSelector("#header .quantity")).getText()) == products);
        removeProductInCart(products);
        Assert.assertTrue(driver.findElement(By.xpath("//em[.='There are no items in your cart.']")).isDisplayed());
    }

    public void addProductAndReturnHome(int quantity) throws InterruptedException {
        for (int i=0; quantity > i; i++) {
            wait.until(visibilityOfElementLocated(By.id("slider")));
            String cartLoc = "#header .quantity";

            for (int i1=0;i1<5;i1++) {
                driver.findElement(By.cssSelector("#box-most-popular .product")).click();
                if (driver.findElements(By.cssSelector(".quantity > button")).size() > 0) {
                    break;
                }
            }

            wait.until(visibilityOf(driver.findElement(By.cssSelector(cartLoc))));
            String cartOld = driver.findElement(By.cssSelector(cartLoc)).getText();
            wait.until(visibilityOf(driver.findElement(By.cssSelector(".quantity > button"))));

            if (driver.findElements(By.className("options")).size() > 0) {
                Select sizeSelector = new Select(driver.findElement(By.cssSelector(".options select")));
                sizeSelector.selectByVisibleText("Small");
                Thread.sleep(200);
            }
            wait.until(elementToBeClickable(driver.findElement(By.cssSelector(".quantity > button"))));
            Thread.sleep(200);
            driver.findElement(By.cssSelector(".quantity > button")).click();

            for (int i2=0;i2<10;i2++) {
                String cartNew = driver.findElement(By.cssSelector(cartLoc)).getText();
                if (cartOld.equals(cartNew)) {
                    Thread.sleep(100);
                    cartNew = driver.findElement(By.cssSelector(cartLoc)).getText();
                } else {
                    break;
                }
            }
            driver.findElement(By.cssSelector("#site-menu .fa")).click();
            wait.until(visibilityOf(driver.findElement(By.id("slider"))));
        }
    }

    public void removeProductInCart(int quantity) throws InterruptedException {
        for (int i=0; quantity > i; i++) {
            wait.until(visibilityOf(driver.findElement(By.id("slider"))));
            if (!driver.findElement(By.id("breadcrumbs")).getText().equals("Home")) {
                //Thread.sleep(500);
            }
            wait.until(visibilityOf(driver.findElement(By.id("slider"))));
            driver.findElement(By.cssSelector("#header .link")).click();

            for (int i1=0; i1<3;i1++) {
                //Thread.sleep(100);
                if (driver.findElements(By.cssSelector(".footer td:nth-child(2)")).size() == 0) {
                    driver.findElement(By.cssSelector("#header .content")).click();
                } else {
                    break;
                }
                System.out.println("Попытка i1 №"+i1);
            }

            wait.until(visibilityOf(driver.findElement(By.cssSelector(".footer td:nth-child(2)"))));
            int productsOld = driver.findElements(By.xpath("//*[@id='order_confirmation-wrapper']//td[2]")).size();

            List<WebElement> list = driver.findElements(By.cssSelector(".shortcut a"));
            if (list.size() > 1) {
                list.get(0).click();

                for (int i2=0;i2<10;i2++ ) {
                    if (!list.get(0).getAttribute("class").equals("inact act")) {
                        //Thread.sleep(200);
                        list.get(0).click();
                    } else {
                        break;
                    }
                    System.out.println("Попытка i2 №"+i2);
                }
            }

            wait.until(visibilityOf(driver.findElement(By.xpath("(//button[.='Remove'])[1]"))));
            driver.findElement(By.xpath("(//button[.='Remove'])[1]")).click();

            //Thread.sleep(300);
            if (driver.findElements(By.xpath("//em[.='There are no items in your cart.']")).size() == 1) {
                break;
            }

            for (int i3=0;i3<10;i3++) {
                int productsNew = driver.findElements(By.xpath("//*[@id='order_confirmation-wrapper']//td[2]")).size();
                if (productsNew==productsOld) {
                    Thread.sleep(200);
                    driver.findElement(By.xpath("(//button[.='Remove'])[1]")).click();
                    wait.until(visibilityOf(driver.findElement(By.xpath("(//button[.='Remove'])[1]"))));
                } else {
                    break;
                }
                System.out.println("Попытка i3 №"+i3);
            }

            driver.findElement(By.cssSelector("#site-menu .fa")).click();
            wait.until(visibilityOf(driver.findElement(By.id("slider"))));
        }
    }
}
