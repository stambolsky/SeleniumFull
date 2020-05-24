package com.stqa.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import java.util.List;

public class CatalogPage extends AdminPanelPage {

    WebElement folder = driver.findElement(By.xpath("//i[@class='fa fa-folder']/../a"));
    WebElement productLink = driver.findElement(By.xpath("//*[@class='dataTable']//img/../a"));
    WebElement buttonNewProduct = driver.findElement(By.xpath("//a[.=' Add New Product']"));
    WebElement buttonSave = driver.findElement(By.xpath("//button[@name='save']"));
    WebElement status = driver.findElement(By.xpath("//*[@name='status']"));
    WebElement name = driver.findElement(By.xpath("//input[@name='name[en]']"));
    WebElement code = driver.findElement(By.xpath("//input[@name='code']"));
    WebElement root = driver.findElement(By.xpath("//input[@data-name='[Root]']"));
    WebElement gender = driver.findElement(By.xpath("//input[@value='1-3']"));
    WebElement quantity = driver.findElement(By.xpath("//input[@name='quantity']"));
    WebElement newImage = driver.findElement(By.xpath("//input[@name='new_images[]']"));
    WebElement dataFrom = driver.findElement(By.xpath("//input[@name='date_valid_from']"));
    WebElement dateTo = driver.findElement(By.xpath("//input[@name='date_valid_to']"));
    WebElement tabInformation = driver.findElement(By.xpath("//a[.='Information']"));
    WebElement keywords = driver.findElement(By.xpath("//input[@name='keywords']"));
    WebElement description = driver.findElement(By.xpath("//input[@name='short_description[en]']"));
    WebElement headTitle = driver.findElement(By.xpath("//input[@name='head_title[en]']"));
    WebElement manufacturerId = driver.findElement(By.xpath("//select[@name='manufacturer_id']"));
    WebElement metaDescription = driver.findElement(By.xpath("//input[@name='meta_description[en]']"));
    WebElement metaKeywords = driver.findElement(By.xpath("//input[@name='meta_keywords[en]']"));
    WebElement purchasePrice = driver.findElement(By.xpath("//input[@name='purchase_price']"));
    WebElement pricesUSD = driver.findElement(By.xpath("//input[@name='prices[USD]']"));
    WebElement pricesEUR = driver.findElement(By.xpath("//input[@name='prices[EUR]']"));
    WebElement prices = driver.findElement(By.xpath("//a[.='Prices']"));


    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getFolders() {
        return getListElements(folder);
    }

    public void openFolders() {
        List<WebElement> folders = getFolders();
        for (WebElement folder : folders) {
            if (folders.size()==0) {
                break;
            }
            folder.click();
            folders = getFolders();
        }
    }

    public List<WebElement> getProducts() {
        return getListElements(productLink);
    }

    public void openProductsAndCheckLogsBrowser() {
        List<WebElement> products = getProducts();
        for (int i=0;i < products.size(); i++) {
            products.get(i).click();
            driver.navigate().back();
            List<LogEntry> logs = driver.manage().logs().get(LogType.BROWSER).getAll();
            if (logs.size() > 0) {
                logs.forEach(System.out::println);
            }
            Assert.assertEquals(0, logs.size());
            products = getProducts();
        }
    }

    public CatalogPage clickAddNewProduct() {
        clickElement(buttonNewProduct);
        return this;
    }

    public CatalogPage setFields(String randomNumber, String absolutePath) {
        clickElement(status);
        clickElement(name);
        setFieldData(code,randomNumber);
        clickElement(root);
        clickElement(gender);
        setFieldData(quantity,"100");
        setFieldData(newImage,absolutePath);
        setFieldData(dataFrom,"05.05.2020");
        setFieldData(dateTo,"05.05.2021");
        clickElement(tabInformation);
        setSelect(manufacturerId, "ACME Corp.");
        setFieldData(keywords,"Apple");
        setFieldData(description,"Apple MacBookPro");
        setFieldData(headTitle,"Apple");
        setFieldData(metaDescription,"description");
        setFieldData(metaKeywords,"keywords");
        clickElement(prices);
        setFieldData(purchasePrice,"50");
        setFieldData(pricesUSD,"50");
        setFieldData(pricesEUR,"45");
        return this;
    }

    public void pressSave() {
        clickElement(buttonSave);
    }
}
