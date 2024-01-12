package org.example.tests;

import org.example.basetestsclass.BaseTests;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FirstTest extends BaseTests {
    @ParameterizedTest
    @CsvSource({
            "false, йцю123qw!@#$%^&*()_+/\\|, Овощ, VEGETABLE",
            "true, Ананас, Фрукт, FRUIT"
    })
    public void startTest(boolean checkboxIsSelected, String text, String nameMenuWindow, String selectedValue) {
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
                        "Экзотический", checkboxIsSelected)
                .checkField(text)
                .selectSubMenuSelect(nameMenuWindow)
                .checkTypeSelected(selectedValue)
                .checkBtn("Сохранить")
                .clickBtnSave()
                .checkWindowClosed()
                .checkNewAddToTable(text, nameMenuWindow, checkboxIsSelected);
    }
}
