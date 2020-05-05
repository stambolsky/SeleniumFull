package com.stqa;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class CheckSortingOfCountriesAndGeoFencesInAdminPanel extends TestBase {

    @Test
    public void testCheckSorting() {
        driver.get("http://localhost:8888/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        List<WebElement> countries = driver.findElements(By.cssSelector("form .dataTable td:nth-child(4)>a"));
        List<String> countriesSortList = new ArrayList<String>();
        List<String> countriesUnSortList = new ArrayList<String>();

        for (WebElement name : countries) {
            countriesUnSortList.add(name.getText());
            countriesSortList.add(name.getText());
        }

        List<WebElement> zones = driver.findElements(By.xpath("//form//td[5]"));
        List<String> locator = new ArrayList<String>();
        int number = 2;
        for (WebElement zone : zones) {
            if (Integer.parseInt(zone.getText()) > 0) {
                locator.add("//form//tr[" + number + "]//a");
            }
            number++;
        }

        for (String loc : locator) {
            driver.findElement(By.xpath(loc)).click();
            List<WebElement> zonesList = driver.findElements(By.xpath("//table[@class='dataTable']//td[3]"));
            zonesList.remove(zonesList.size()-1);
            List<String> zonesSortList = new ArrayList<String>();
            List<String> zonesUnSortList = new ArrayList<String>();
            for (WebElement name : zonesList) {
                zonesUnSortList.add(name.getText());
                zonesSortList.add(name.getText());
            }
            Collections.sort(zonesSortList);
            for (int i=0; i<zonesList.size();i++) {
                assertEquals("Неправильная сортировка зон", countriesUnSortList.get(i), countriesSortList.get(i));
            }
            driver.findElement(By.xpath("//button[@name='cancel']")).click();
        }

        Collections.sort(countriesSortList);
        for (int i=0; i<countries.size();i++) {
            assertEquals("Неправильная сортировка стран", countriesUnSortList.get(i), countriesSortList.get(i));
        }
    }

    @Test
    public void checkSortGeoZones() {
        driver.get("http://localhost:8888/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        List<WebElement> geoZone = driver.findElements(By.cssSelector("form .dataTable td:nth-child(3)>a"));
        List<String> geoZoneSortList = new ArrayList<String>();
        List<String> geoZoneUnSortList = new ArrayList<String>();
        for (WebElement name : geoZone) {
            geoZoneUnSortList.add(name.getText());
            geoZoneSortList.add(name.getText());
        }

        List<WebElement> zones = driver.findElements(By.xpath("//form[@name='geo_zones_form']//tr[@class='odd' or @class='even']/td[4]"));
        List<String> locator = new ArrayList<String>();
        int number = 0;
        for (WebElement zone : zones) {
            if (Integer.parseInt(zone.getText()) > 0) {
                locator.add("//form//tr[" + number + "]//a");
            }
            number++;
        }

        for (String loc : locator) {
            driver.findElement(By.xpath(loc)).click();
            List<WebElement> zonesList = driver.findElements(By.xpath("//table[@class='dataTable']//td[2]"));
            List<String> zonesSortList = new ArrayList<String>();
            List<String> zonesUnSortList = new ArrayList<String>();
            for (WebElement name : zonesList) {
                zonesUnSortList.add(name.getText());
                zonesSortList.add(name.getText());
            }
            Collections.sort(zonesSortList);
            for (int i=0; i<zonesList.size();i++) {
                assertEquals("Неправильная сортировка зон", zonesUnSortList.get(i), zonesSortList.get(i));
            }
            driver.findElement(By.xpath("//button[@name='cancel']")).click();
        }

        Collections.sort(geoZoneSortList);
        for (int i=0; i<geoZone.size();i++) {
            assertEquals("Неправильная сортировка зон", geoZoneUnSortList.get(i), geoZoneSortList.get(i));
        }
    }
}
