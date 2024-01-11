package org.example.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.nio.file.WatchEvent;
import java.util.List;

public class ProductsPage extends BasePage{
    @FindBy(xpath = "//h5[contains(text(),'Список товаров')]")
    private WebElement title;

    @FindBy(xpath = "//table[@class = 'table']/thead/*/*")
    private List<WebElement> tableHeaders;

    @FindBy(xpath = "//button[contains(text(), 'Добавить')]")
    private WebElement btnAdd;

    @FindBy(id = "editModalLabel")
    private WebElement dialogHeader;

    @FindBy(xpath = "//form[@id = 'editForm']/*/input[@id = 'name']")
    private WebElement dialogFieldName;

    @FindBy(xpath = "//form[@id = 'editForm']/*/select[@id = 'type']")
    private WebElement dialogSelectType;

    @FindBy(xpath = "//form[@id = 'editForm']/*/input[@id = 'exotic']")
    private WebElement dialogCheckBoxExotic;

    /**
     * Проверка открытия страницы, путём проверки title страницы
     *
     * @return ProductsPage - т.е. остаемся на этой странице
     */
    public ProductsPage checkOpenProductsPage(String header) {
        Assertions.assertEquals(header,
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

    /**
     * Проверка наличия кнопки соответствующего цвета
     *
     * @return ProductsPage - т.е. остаемся на этой странице
     */
    public ProductsPage checkBtn(String color){
        String colorBtn = btnAdd.getCssValue("background-color");
        Assertions.assertEquals(color, colorBtn, "Цвет кнопки не соответствует переданному цвету");
        System.out.println("Цвет кнопки: " + colorBtn);
        return this;
    }

    /**
     * Клик по кнопке "Добавить"
     *
     * @return ProductsPage - т.е. остаемся на этой странице
     */
    public ProductsPage clickBtnAdd() {
        waitUtilElementToBeClickable(btnAdd).click();
        return this;
    }

    /**
     * Проверка модального окна добавления нового товара в таблицу
     *
     * @return ProductsPage - т.е. остаемся на этой странице
     */
    public ProductsPage checkDialog (String header){
        waitUtilElementToBeVisible(dialogHeader);
        Assertions.assertEquals(header, dialogHeader.getText(), "Не найден заголовок окна добавления товара");
        System.out.println("Заголовок окна добавления товара: " + dialogHeader.getText());
        waitUtilElementToBeClickable(dialogFieldName);
        waitUtilElementToBeVisible(dialogSelectType);
        waitUtilElementToBeVisible(dialogCheckBoxExotic);
        return this;
    }

}
