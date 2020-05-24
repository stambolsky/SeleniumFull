package com.stqa.tests;

import com.stqa.pages.MainPage;
import com.stqa.pages.Navigation;
import org.junit.Test;

public class UserRegistration extends TestBase {

    Navigation navigation;
    MainPage mainPage;

    @Test
    public void testUserRegistration() {

        String email = "test"+1 + (int) (Math.random() * 100000)+"@test.com";
        String password = "123456";
        navigation = new Navigation(driver);
        mainPage = navigation.openMainPage();
        mainPage.pressButtonRegNewUser()
                .setFieldAndClickCreateAccount(email, password, "Sergey", "Tambolsky",
                        "United States", "TestAddress", "12345", "New York", "43674537587")
                .logout();
        mainPage.login(email, password)
                .logout();
    }
}
