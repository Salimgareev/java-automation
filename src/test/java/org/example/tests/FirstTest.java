package org.example.tests;

import org.example.basetestsclass.BaseTests;
import org.junit.jupiter.api.Test;

public class FirstTest extends BaseTests {
    @Test
    public void startTest() {
        app.getHomePage()
                .selectBaseMenu("Песочница")
                .selectSubMenu("Товары")
                .checkOpenProductsPage();  // Проверяем, что страница с товарами открылась
    }
}
