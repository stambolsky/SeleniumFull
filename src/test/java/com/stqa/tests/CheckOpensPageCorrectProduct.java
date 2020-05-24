package com.stqa.tests;

import com.stqa.pages.CardProductPage;
import com.stqa.pages.MainPage;
import com.stqa.pages.Navigation;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class CheckOpensPageCorrectProduct extends TestBase {

    Navigation navigation;
    MainPage mainPage;
    CardProductPage cardProductPage;

    @Test
    public void testCheckOpensPageCorrectProduct() {
        navigation = new Navigation(driver);
        mainPage = navigation.openMainPage();
        String nameProduct = mainPage.getCompanyProduct();
        String regularPrice = mainPage.getRegularPrice();
        double fontSizeRegularPrice = Double.parseDouble(mainPage.getFontSizeRegularPrice());
        String campaignPrice = mainPage.getCampaignPrice();
        double fontSizeCampaignPrice = Double.parseDouble(mainPage.getFontSizeCampaignPrice());
        assertTrue(fontSizeRegularPrice < fontSizeCampaignPrice);

        cardProductPage = mainPage.checkColorRegularPriceRGB()
                .checkCampaignPriceRGB()
                .openCardFirstProduct();

        double fontSizeRegularPriceView = Double.parseDouble(cardProductPage.getFontSizeRegularPrice());
        double fontSizeCampaignPriceView = Double.parseDouble(cardProductPage.getFontSizeCampaignPrice());
        assertTrue(fontSizeRegularPriceView < fontSizeCampaignPriceView);

        assertTrue(nameProduct.equals(cardProductPage.getTitle()));
        assertTrue(regularPrice.equals(cardProductPage.getRegularPrice()));
        assertTrue(campaignPrice.equals(cardProductPage.getCampaignPrice()));

        cardProductPage.checkColorRegularPriceRGB();
        cardProductPage.checkCampaignPriceRGB();
    }
}
