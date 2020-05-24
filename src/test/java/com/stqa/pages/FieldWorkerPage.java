package com.stqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class FieldWorkerPage extends Page {

    public FieldWorkerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void setFieldData(WebElement element, String value) {
        element.sendKeys(value);
    }

    public void clickElement(WebElement element) {
        wait.until(elementToBeClickable(element));
        element.click();
    }

    public List<WebElement> getWebElements(WebElement element) {
        return Collections.singletonList(element);
    }

    public List<WebElement> getListElements(WebElement element) {
        return Collections.singletonList(element);
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    public boolean checkDisplayElement(By element) {
        return driver.findElement(element).isDisplayed();
    }

    public String getFontSize(WebElement element) {
        return element.getCssValue("font-size").replace("px", "");
    }

    public List<String> getListColor(WebElement element) {
        return Arrays.asList(element.getCssValue("color")
                .replace("rgba(", "")
                .replace(", 1)", "")
                .replace(" ", "")
                .split(","));
    }

    public void setSelect(WebElement element, String text) {
        Select manufacturerSelector = new Select(element);
        manufacturerSelector.selectByVisibleText(text);
    }
}
