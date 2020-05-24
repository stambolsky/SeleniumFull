package com.stqa.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CountriesPage extends AdminPanelPage {

    By country = By.xpath("//a[.='Belarus']");
    WebElement linkInTable = driver.findElement(By.cssSelector("form .dataTable td:nth-child(4)>a"));
    WebElement isoCodeLink = driver.findElement(By.xpath("//input[@name='iso_code_2']/../a"));
    List<WebElement> notLink = driver.findElements(By.xpath("//td/a[not(contains(@href, '#'))]"));
    List<WebElement> wikiLogo = driver.findElements(By.className("mw-wiki-logo"));
    List<WebElement> defaultLogo = driver.findElements(By.className("default-logo"));
    WebElement cancelButton = driver.findElement(By.xpath("//button[@name='cancel']"));
    List<WebElement> tableRow3 = driver.findElements(By.xpath("//table[@class='dataTable']//td[3]"));

    public CountriesPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(country));
    }

    public void checkThatLinks() {
        driver.findElement(country).click();

        String homeWindow = driver.getWindowHandle();
        String newWindow = "";
        wait.until(ExpectedConditions.elementToBeClickable(isoCodeLink));

        List<WebElement> elements = notLink;
        for (WebElement el : elements) {
            el.click();
            Set<String> handles = driver.getWindowHandles();
            for (String handle : handles) {
                if (!handle.equals(homeWindow)) {
                    driver.switchTo().window(handle);
                    newWindow = driver.getWindowHandle();
                }
            }
            if (wikiLogo.size()>0 ||
                    defaultLogo.size()>0) {
                Assert.assertTrue(driver.getWindowHandles().size()==2);
                driver.close();
                driver.switchTo().window(homeWindow);
            }
            Assert.assertTrue(driver.getWindowHandles().size()==1);
        }
    }

    public List<WebElement> getCountriesElements() {
        return getListElements(linkInTable);
    }

    public void checkSortOfCountriesAndGeoFences(List<WebElement> countries) {
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
            List<WebElement> zonesList = tableRow3;
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
            clickElement(cancelButton);
        }

        Collections.sort(countriesSortList);
        for (int i=0; i<countries.size();i++) {
            assertEquals("Неправильная сортировка стран", countriesUnSortList.get(i), countriesSortList.get(i));
        }
    }
}
