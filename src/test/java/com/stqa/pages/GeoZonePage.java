package com.stqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GeoZonePage extends AdminPanelPage {

    By tabGeoZones = By.xpath("//*[text()=' Geo Zones']");
    List<WebElement> selectedOptions = driver.findElements(By.xpath("//form//td[3]//option[@selected='selected']"));
    WebElement countriesTable = driver.findElement(By.cssSelector("form .dataTable td:nth-child(3)>a"));
    WebElement cancelButton = driver.findElement(By.xpath("//button[@name='cancel']"));

    public GeoZonePage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(tabGeoZones));
    }

    public List<String> getCountriesList(List<WebElement> countries) {
        List<String> countriesList = new ArrayList<String>();
        for (WebElement name : countries) {
            countriesList.add("//a[.='"+name.getText()+"']");
        }
        return countriesList;
    }

    public void checkSortGeoZone(List<String> countriesList) throws InterruptedException {
        for (String name : countriesList) {

            Thread.sleep(500);
            driver.findElement(By.xpath(name)).click();
            List<WebElement> zonesList = selectedOptions;
            List<String> zonesSortList = new ArrayList<String>();
            List<String> zonesUnSortList = new ArrayList<String>();
            for (WebElement zone : zonesList) {
                zonesUnSortList.add(zone.getText());
                zonesSortList.add(zone.getText());
            }
            Collections.sort(zonesSortList);
            for (int i=0; i<zonesList.size();i++) {
                assertEquals("Неправильная сортировка зон", zonesUnSortList.get(i), zonesSortList.get(i));
            }
            Thread.sleep(500);
            clickElement(cancelButton);
        }
    }

    public List<WebElement> getCountriesElements() {
        return getListElements(countriesTable);
    }
}
