package org.example.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends BasePage{
    @FindBy(xpath = "//h5[contains(text(),'Список товаров')]")
    private WebElement title;

    @FindBy(xpath = "//table[@class = 'table']/thead/*/*")
    private List<WebElement> tableHeaders;

    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return ProductsPage - т.е. остаемся на этой странице
     */
    public ProductsPage checkOpenProductsPage() {
        Assertions.assertEquals("Список товаров",
                title.getText(), "Заголовок отсутствует/не соответствует требуемому");
        System.out.println("Заголовок: " + title.getText());
        return this;
    }

    /**
     * Проверка наличия опр заголовка в таблице товаров
     *
     * @return ProductsPage - т.е. остаемся на этой странице
     */
    public ProductsPage checkAvailabilityHeadings(String nameHeader) {
        for (WebElement header: tableHeaders) {
            if (header.getText().trim().equalsIgnoreCase(nameHeader)){
                System.out.println("Заголовок таблицы: " + header.getText().trim());
                return this;
            }
        }

        Assertions.fail("Меню '" + nameHeader + "' не было найдено на странице товаров!");
        return this;
    }

}
