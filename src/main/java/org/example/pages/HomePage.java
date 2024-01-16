package org.example.pages;

import io.cucumber.java.bg.И;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class HomePage extends BasePage{
    @FindBy(xpath = "//li[contains(@class,'nav-item')]/a[@role or @aria-expanded]")
    private List<WebElement> listBaseMenu;

    @FindBy(xpath = "//a[@class = 'dropdown-item']")
    private List<WebElement> listSubMenu;


    /**
     * Функция наведения мыши на любой пункт меню
     *
     * @param nameBaseMenu - наименование меню
     * @return HomePage - т.е. остаемся на этой странице
     */
    @И("нажать на меню {string}")
    public HomePage selectBaseMenu(String nameBaseMenu) {
        for (WebElement menuItem : listBaseMenu) {
            if (menuItem.getText().trim().equalsIgnoreCase(nameBaseMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                System.out.println("Найдено меню: " + menuItem.getText().trim());
                return this;
            }
        }
        Assertions.fail("Меню '" + nameBaseMenu + "' не было найдено на стартовой странице!");
        return this;
    }

    /**
     * Функция клика на любое подменю
     *
     * @param nameSubMenu - наименование подменю
     * @return ProductsPage - т.е. переходим на страницу {@link ProductsPage}
     */
    @И("выбрать в нем {string}")
    public ProductsPage selectSubMenu(String nameSubMenu) {
        for (WebElement menuItem : listSubMenu) {
            if (menuItem.getText().equalsIgnoreCase(nameSubMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                return pageManager.getProductsPage();
            }
        }
        Assertions.fail("Подменю '" + nameSubMenu + "' не было найдено на стартовой странице!");
        return pageManager.getProductsPage();
    }
}
