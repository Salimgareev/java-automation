package org.example.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends BasePage{
    @FindBy(xpath = "//h5[contains(text(),'Список товаров')]")
    private WebElement title;

    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return InsurancePage - т.е. остаемся на этой странице
     */
    public ProductsPage checkOpenProductsPage() {
        Assertions.assertEquals("Список товаров",
                title.getText(), "Заголовок отсутствует/не соответствует требуемому");
        System.out.println("Заголовок: " + title.getText());
        return this;
    }
}
