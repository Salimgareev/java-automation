package org.example.tests;

import org.example.basetestsclass.BaseTests;
import org.junit.jupiter.api.Test;

public class FirstTest extends BaseTests {
    @Test
    public void startTest() {
        app.getHomePage()
                .selectBaseMenu("Песочница")
                .selectSubMenu("Товары")
                .checkOpenProductsPage("Список товаров")
                .checkAvailabilityHeadings("Наименование")
                .checkAvailabilityHeadings("Тип")
                .checkAvailabilityHeadings("Экзотический")
                .checkBtnColor("rgba(0, 123, 255, 1)")
                .clickBtnAdd()
                .checkDialog("Добавление товара", "exotic", "checkbox",
                        "Экзотический", false)
                .checkField("йцю123qw!@#$%^&*()_+/\\|")
                .selectSubMenuSelect("Овощ")
                .checkTypeSelected("VEGETABLE")
                .checkBtn("Сохранить")
                .clickBtnSave()
                .checkWindowClosed()
                .checkNewAddToTable("йцю123qw!@#$%^&*()_+/\\|", "Овощ", false);
    }
}
