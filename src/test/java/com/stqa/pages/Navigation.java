package com.stqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Navigation extends FieldWorkerPage {

    WebElement username = driver.findElement(By.name("username"));
    WebElement password = driver.findElement(By.name("password"));
    WebElement login = driver.findElement(By.name("login"));

    public Navigation(WebDriver driver) {
        super(driver);
    }

    public MainPage openMainPage() {
        driver.get("http://localhost:8888/litecart/en/");
        return new MainPage(driver);
    }

    public AdminPanelPage logInAsAdmin() {
        driver.get("http://localhost:8888/litecart/admin/");
        setFiledLoginAndPassword();
        return new AdminPanelPage(driver);
    }

    public GeoZonePage openPageGeoZone() {
        driver.get("http://localhost:8888/litecart/admin/?app=geo_zones&doc=geo_zones");
        setFiledLoginAndPassword();
        return new GeoZonePage(driver);
    }

    public CountriesPage openPageCountries() {
        driver.get("http://localhost:8888/litecart/admin/?app=countries&doc=countries");
        setFiledLoginAndPassword();
        return new CountriesPage(driver);
    }

    public CatalogPage openPageCatalog() {
        driver.get("http://localhost:8888/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        setFiledLoginAndPassword();
        return new CatalogPage(driver);
    }



    public void setFiledLoginAndPassword() {
        username.sendKeys("admin");
        password.sendKeys("admin");
        clickButtonLogin();
    }

    public void clickButtonLogin() {
        clickElement(login);
    }



}
