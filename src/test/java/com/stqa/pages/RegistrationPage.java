package com.stqa.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends FieldWorkerPage {

    WebElement customer_form = driver.findElement(By.name("customer_form"));
    WebElement firstname = driver.findElement(By.id("//input[@name='firstname']"));
    WebElement lastname = driver.findElement(By.id("//input[@name='lastname']"));
    WebElement address1 = driver.findElement(By.id("//input[@name='address1']"));
    WebElement postcode = driver.findElement(By.id("//input[@name='postcode']"));
    WebElement city = driver.findElement(By.id("//input[@name='city']"));
    WebElement email = driver.findElement(By.id("//input[@name='email']"));
    WebElement phone = driver.findElement(By.id("//input[@name='phone']"));
    WebElement password = driver.findElement(By.id("//input[@name='password']"));
    WebElement confirmedPassword = driver.findElement(By.id("//input[@name='confirmed_password']"));
    WebElement countryCode = driver.findElement(By.xpath("//select[@name='country_code']"));
    WebElement createAccountBTN = driver.findElement(By.xpath("//button[@name='create_account']"));
    WebElement logoutButton = driver.findElement(By.xpath("//div[@id='box-account']//a[.='Logout']"));

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public RegistrationPage setFieldsRegData(String emailVal, String passwordVal, String firstName, String lastName,
                                             String countrycode, String address, String postCode, String cityVal, String phoneVal) {
        wait.until(ExpectedConditions.visibilityOf(customer_form));
        setFieldData(firstname, firstName);
        setFieldData(lastname, lastName);
        setSelect(countryCode, countrycode);
        setFieldData(address1, address);
        setFieldData(postcode, postCode);
        setFieldData(city, cityVal);
        setFieldData(email, emailVal);
        setFieldData(phone, phoneVal);
        setFieldData(password, passwordVal);
        setFieldData(confirmedPassword, passwordVal);
        return this;
    }

    public AccountPage setFieldAndClickCreateAccount(String email, String password, String firstName, String lastName,
                                                     String countryCode, String address, String postCode, String city, String phone) {
        setFieldsRegData(email, password, firstName, lastName, countryCode, address, postCode, city, phone);
        clickElement(createAccountBTN);
        Assert.assertTrue("Пользователь не авторизован",
                logoutButton.isDisplayed());
        return new AccountPage(driver);
    }
}
