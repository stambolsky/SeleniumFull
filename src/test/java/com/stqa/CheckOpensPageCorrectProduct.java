package com.stqa;

import org.junit.Test;
import org.openqa.selenium.By;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class CheckOpensPageCorrectProduct extends TestBase {

    @Test
    public void testCheckOpensPageCorrectProduct() {

        driver.get("http://localhost:8888/litecart/en/");
        String nameProduct = driver.findElement(By.cssSelector("#box-campaigns .name")).getText();
        String regularPrice = driver.findElement(By.cssSelector("#box-campaigns .regular-price")).getText();
        double fontSizeRegularPrice = Double.parseDouble(driver.findElement(By.cssSelector("#box-campaigns .regular-price")).getCssValue("font-size").replace("px", ""));
        String campaignPrice = driver.findElement(By.cssSelector("#box-campaigns .campaign-price")).getText();
        double fontSizeCampaignPrice = Double.parseDouble(driver.findElement(By.cssSelector("#box-campaigns .campaign-price")).getCssValue("font-size").replace("px", ""));

        assertTrue(fontSizeRegularPrice < fontSizeCampaignPrice);
        List<String> colorRegularPriceRGB = getListColor("#box-campaigns .regular-price");
        assertEquals("Цвет шрифта не совпадает ", colorRegularPriceRGB.get(0).equals(colorRegularPriceRGB.get(1)),
                colorRegularPriceRGB.get(2).equals(colorRegularPriceRGB.get(1)));

        List<String> colorCampaignPriceRGB = getListColor("#box-campaigns .campaign-price");
        assertEquals("Цвет шрифта не совпадает у акционной цены", colorCampaignPriceRGB.get(1).equals(colorCampaignPriceRGB.get(2)),
                colorCampaignPriceRGB.get(2).equals("0"));


        driver.findElement(By.cssSelector("#box-campaigns li>a.link")).click();

        double fontSizeRegularPriceView = Double.parseDouble(driver.findElement(By.cssSelector("#box-product .regular-price")).getCssValue("font-size").replace("px", ""));
        double fontSizeCampaignPriceView = Double.parseDouble(driver.findElement(By.cssSelector("#box-product .campaign-price")).getCssValue("font-size").replace("px", ""));
        assertTrue(fontSizeRegularPriceView < fontSizeCampaignPriceView);

        assertTrue(nameProduct.equals(driver.findElement(By.cssSelector("#box-product h1")).getText()));
        assertTrue(regularPrice.equals(driver.findElement(By.cssSelector("#box-product .regular-price")).getText()));
        assertTrue(campaignPrice.equals(driver.findElement(By.cssSelector("#box-product .campaign-price")).getText()));


        List<String> colorRegularPriceRGBView = getListColor("#box-product .regular-price");
        assertEquals("Цвет шрифта не совпадает ", colorRegularPriceRGBView.get(0).equals(colorRegularPriceRGBView.get(1)),
                colorRegularPriceRGBView.get(2).equals(colorRegularPriceRGBView.get(1)));

        List<String> colorCampaignPriceRGBView = getListColor("#box-product .campaign-price");
        assertEquals("Цвет шрифта не совпадает у акционной цены", colorCampaignPriceRGBView.get(1).equals(colorCampaignPriceRGBView.get(2)),
                colorCampaignPriceRGBView.get(2).equals("0"));
    }

    private List<String> getListColor(String locator) {
        return Arrays.asList(driver.findElement(By.cssSelector(locator)).getCssValue("color")
                .replace("rgba(", "")
                .replace(", 1)", "")
                .replace(" ", "")
                .split(","));
    }
}
