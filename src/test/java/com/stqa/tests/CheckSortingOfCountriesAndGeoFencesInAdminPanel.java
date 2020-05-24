package com.stqa.tests;

import com.stqa.pages.CountriesPage;
import com.stqa.pages.GeoZonePage;
import com.stqa.pages.Navigation;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckSortingOfCountriesAndGeoFencesInAdminPanel extends TestBase {

    Navigation navigation;
    GeoZonePage geoZonePage;
    CountriesPage countriesPage;

    @Test
    public void testCheckSorting() {
        navigation = new Navigation(driver);
        countriesPage = navigation.openPageCountries();
        List<WebElement> countries = countriesPage.getCountriesElements();
        countriesPage.checkSortOfCountriesAndGeoFences(countries);
    }

    @Test
    public void checkSortGeoZones() throws InterruptedException {
        navigation = new Navigation(driver);
        geoZonePage = navigation.openPageGeoZone();
        List<WebElement> countries = geoZonePage.getCountriesElements();
        List<String> countriesList = geoZonePage.getCountriesList(countries);
        geoZonePage.checkSortGeoZone(countriesList);
    }
}
