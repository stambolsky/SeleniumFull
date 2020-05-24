package com.stqa.tests;

import com.stqa.pages.AdminPanelPage;
import com.stqa.pages.Navigation;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PassingAllSectionsOfAdminPanel extends TestBase {

    Navigation navigation;
    AdminPanelPage adminPanel;

    @Test
    public void testPassingAllSectionsOfAdminPanel() {
        navigation = new Navigation(driver);
        adminPanel = navigation.logInAsAdmin();
        List<WebElement> menuTabs = adminPanel.getAllTabs();
        adminPanel.checkAllTabMenu(menuTabs);
    }
}
