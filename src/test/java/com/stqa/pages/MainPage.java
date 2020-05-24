package com.stqa.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class MainPage extends FieldWorkerPage {

    WebElement newCustomersButton = driver.findElement(By.xpath("//a[.='New customers click here']"));
    WebElement email = driver.findElement(By.id("//input[@name='email']"));
    WebElement password = driver.findElement(By.id("//input[@name='password']"));
    WebElement loginButton = driver.findElement(By.xpath("//button[@name='login']"));
    WebElement logoutButton = driver.findElement(By.xpath("//div[@id='box-account']//a[.='Logout']"));
    WebElement productList = driver.findElement(By.cssSelector("li.product"));
    By sticker = By.cssSelector(".sticker");
    By slider = By.id("slider");

    WebElement mostPopularProduct = driver.findElement(By.cssSelector("#box-most-popular .product"));
    List<WebElement> quantityButton = driver.findElements(By.cssSelector(".quantity > button"));
    WebElement quantityBtn = driver.findElement(By.cssSelector(".quantity > button"));
    List<WebElement> options = driver.findElements(By.className("options"));
    WebElement optionsSelect = driver.findElement(By.cssSelector(".options select"));
    WebElement siteMenuFolders = driver.findElement(By.cssSelector("#site-menu .fa"));
    WebElement breadcrumbs = driver.findElement(By.id("breadcrumbs"));
    WebElement sliderWeb = driver.findElement(By.id("sliderWeb"));
    WebElement headerLink = driver.findElement(By.cssSelector("#header .link"));
    List<WebElement> footers = driver.findElements(By.cssSelector(".footer td:nth-child(2)"));
    WebElement headerContent = driver.findElement(By.cssSelector("#header .content"));
    WebElement footer = driver.findElement(By.cssSelector(".footer td:nth-child(2)"));
    List<WebElement> products = driver.findElements(By.xpath("//*[@id='order_confirmation-wrapper']//td[2]"));
    WebElement removeButton = driver.findElement(By.xpath("(//button[.='Remove'])[1]"));
    List<WebElement> noItemCartText = driver.findElements(By.xpath("//em[.='There are no items in your cart.']"));
    WebElement siteMenuFolder = driver.findElement(By.cssSelector("#site-menu .fa"));
    WebElement headerQuantity = driver.findElement(By.cssSelector("#header .quantity"));
    WebElement campaignsName = driver.findElement(By.cssSelector("#box-campaigns .name"));
    WebElement campaignsRegularPrice = driver.findElement(By.cssSelector("#box-campaigns .regular-price"));
    WebElement campaignPrice = driver.findElement(By.cssSelector("#box-campaigns .campaign-price"));
    WebElement productRegularPrice = driver.findElement(By.cssSelector("#box-product .regular-price"));
    WebElement campaignsLink = driver.findElement(By.cssSelector("#box-campaigns li>a.link"));

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public RegistrationPage pressButtonRegNewUser() {
        clickElement(newCustomersButton);
        return new RegistrationPage(driver);
    }

    public AccountPage login(String emailVal, String passwordVal) {
        wait.until(elementToBeClickable(driver.findElement(By.xpath("//input[@name='email']"))));
        setFieldData(email, emailVal);
        setFieldData(password, passwordVal);
        clickElement(loginButton);
        Assert.assertTrue("Пользователь не авторизован",
                logoutButton.isDisplayed());
        return new AccountPage(driver);
    }

    public List<WebElement> getProducts() {
        return getListElements(productList);
    }

    public void checkStickersOnProducts() {
        List<WebElement> products = getProducts();
        for (WebElement product : products) {
            int stickers = product.findElements(sticker).size();
            Assert.assertTrue("Количество стикеров у товара - "+stickers+" ,должно быть равно 1",stickers == 1 );
        }
    }

    public void addProductAndReturnHome(int quantity) throws InterruptedException {
        for (int i=0; quantity > i; i++) {
            wait.until(visibilityOfElementLocated(slider));
            String cartLoc = "#header .quantity";

            for (int i1=0;i1<5;i1++) {
                mostPopularProduct.click();
                if (quantityButton.size() > 0) {
                    break;
                }
            }

            wait.until(visibilityOf(driver.findElement(By.cssSelector(cartLoc))));
            String cartOld = driver.findElement(By.cssSelector(cartLoc)).getText();
            wait.until(visibilityOf(quantityBtn));

            if (options.size() > 0) {
                setSelect(optionsSelect, "Small");
                Thread.sleep(200);
            }
            wait.until(elementToBeClickable(quantityBtn));
            Thread.sleep(200);
            quantityBtn.click();

            for (int i2=0;i2<10;i2++) {
                String cartNew = driver.findElement(By.cssSelector(cartLoc)).getText();
                if (cartOld.equals(cartNew)) {
                    Thread.sleep(100);
                    cartNew = driver.findElement(By.cssSelector(cartLoc)).getText();
                } else {
                    break;
                }
            }
            clickElement(siteMenuFolders);
            wait.until(visibilityOf(sliderWeb));
        }
    }

    public void removeProductInCart(int quantity) throws InterruptedException {
        for (int i=0; quantity > i; i++) {
            wait.until(visibilityOf(driver.findElement(slider)));
            if (!getText(breadcrumbs).equals("Home")) {
                //Thread.sleep(500);
            }
            wait.until(visibilityOf(sliderWeb));
            clickElement(headerLink);

            for (int i1=0; i1<3;i1++) {
                //Thread.sleep(100);
                if (footers.size() == 0) {
                    clickElement(headerContent);
                } else {
                    break;
                }
                System.out.println("Попытка i1 №"+i1);
            }
            wait.until(visibilityOf(footer));
            int productsOld = products.size();

            List<WebElement> list = driver.findElements(By.cssSelector(".shortcut a"));
            if (list.size() > 1) {
                list.get(0).click();

                for (int i2=0;i2<10;i2++ ) {
                    if (!list.get(0).getAttribute("class").equals("inact act")) {
                        //Thread.sleep(200);
                        list.get(0).click();
                    } else {
                        break;
                    }
                    System.out.println("Попытка i2 №"+i2);
                }
            }

            wait.until(visibilityOf(removeButton));
            removeButton.click();

            //Thread.sleep(300);
            if (noItemCartText.size() == 1) {
                break;
            }

            for (int i3=0;i3<10;i3++) {
                int productsNew = products.size();
                if (productsNew==productsOld) {
                    Thread.sleep(200);
                    removeButton.click();
                    wait.until(visibilityOf(removeButton));
                } else {
                    break;
                }
                System.out.println("Попытка i3 №"+i3);
            }

            siteMenuFolder.click();
            wait.until(visibilityOf(sliderWeb));
        }
    }

    public String getCountCart() {
        return getText(headerQuantity);
    }

    public boolean checkVisibleTextNoProductInCart() {
        return checkDisplayElement(By.xpath("//em[.='There are no items in your cart.']"));
    }

    public String getCompanyProduct() {
        return getText(campaignsName);
    }

    public String getRegularPrice() {
        return getText(campaignsRegularPrice);
    }

    public String getFontSizeRegularPrice() {
        return getFontSize(campaignsRegularPrice);
    }

    public String getCampaignPrice() {
        return getText(campaignPrice);
    }

    public String getFontSizeCampaignPrice() {
        return getFontSize(campaignPrice);
    }

    public CardProductPage openCardFirstProduct() {
        clickElement(campaignsLink);
        return new CardProductPage(driver);
    }

    public List<String> getListColor() {
        return getListColor(productRegularPrice);
    }

    public MainPage checkCampaignPriceRGB() {
        List<String> colorCampaignPriceRGB = getListColor();
        assertEquals("Цвет шрифта не совпадает у акционной цены", colorCampaignPriceRGB.get(1).equals(colorCampaignPriceRGB.get(2)),
                colorCampaignPriceRGB.get(2).equals("0"));
        return this;
    }

    public MainPage checkColorRegularPriceRGB() {
        List<String> colorRegularPriceRGB = getListColor();
        assertEquals("Цвет шрифта не совпадает ", colorRegularPriceRGB.get(0).equals(colorRegularPriceRGB.get(1)),
                colorRegularPriceRGB.get(2).equals(colorRegularPriceRGB.get(1)));
        return this;
    }
}
