package com.stqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CardProductPage extends FieldWorkerPage{

    WebElement title = driver.findElement(By.cssSelector("#box-product h1"));
    WebElement regularPrice = driver.findElement(By.cssSelector("#box-product .regular-price"));
    WebElement campaignPrice = driver.findElement(By.cssSelector("#box-product .campaign-price"));
    WebElement campaignRegularPrice = driver.findElement(By.cssSelector("#box-campaigns .regular-price"));

    public CardProductPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return getText(title);
    }

    public String getRegularPrice() {
        return getText(regularPrice);
    }

    public String getCampaignPrice() {
        return getText(campaignPrice);
    }

    public List<String> getListColor() {
        return getListColor(regularPrice);
    }

    public void checkCampaignPriceRGB() {
        List<String> colorCampaignPriceRGB = getListColor();
        assertEquals("Цвет шрифта не совпадает у акционной цены", colorCampaignPriceRGB.get(1).equals(colorCampaignPriceRGB.get(2)),
                colorCampaignPriceRGB.get(2).equals("0"));
    }

    public void checkColorRegularPriceRGB() {
        List<String> colorRegularPriceRGB = getListColor();
        assertEquals("Цвет шрифта не совпадает ", colorRegularPriceRGB.get(0).equals(colorRegularPriceRGB.get(1)),
                colorRegularPriceRGB.get(2).equals(colorRegularPriceRGB.get(1)));
    }

    public String getFontSizeCampaignPrice() {
        return getFontSize(campaignPrice);
    }

    public String getFontSizeRegularPrice() {
        return getFontSize(campaignRegularPrice);
    }
}
