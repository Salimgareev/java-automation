package org.example.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
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

    @FindBy(xpath = "//form[@id = 'editForm']/*/select[@id = 'type']/*")
    private List<WebElement> listSelectTypeSubMenu;

    @FindBy(xpath = "//form[@id = 'editForm']/*/input[@id = 'exotic']/../*")
    private List<WebElement> dialogCheckBoxExotic;

    @FindBy(id = "save")
    private WebElement btnSave;

    @FindBy(xpath = "//table[@class = 'table']/tbody/*")
    private List<WebElement> listRowsTable;

    @FindBy(xpath = "//li[contains(@class,'nav-item')]/a[@role or @aria-expanded]")
    private List<WebElement> listBaseMenu;

    @FindBy(xpath = "//a[@class = 'dropdown-item']")
    private List<WebElement> listSubMenu;

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
     * Проверка наличия кнопки с опр текстом
     *
     * @return ProductsPage - т.е. остаемся на этой странице
     */
    public ProductsPage checkBtn(String btnText){
        waitUtilElementToBeClickable(btnSave);
        Assertions.assertEquals(btnText, btnSave.getText(),
                "Текст кнопки сохранить не соответствует ожидаемому");
        return this;
    }


    /**
     * Проверка наличия кнопки соответствующего цвета
     *
     * @return ProductsPage - т.е. остаемся на этой странице
     */
    public ProductsPage checkBtnColor(String color){
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
     * Клик по кнопке "Сохранить"
     *
     * @return ProductsPage - т.е. остаемся на этой странице
     */
    public ProductsPage clickBtnSave() {
        waitUtilElementToBeClickable(btnSave).click();
        return this;
    }

    /**
     * Клик по чекбоксу
     *
     * @return ProductsPage - т.е. остаемся на этой странице
     */
    private ProductsPage clickBtnExotic(WebElement element){
        waitUtilElementToBeClickable(element).click();
        return this;
    }

    /**
     * Проверка поля ввода
     *
     * @return ProductsPage - т.е. остаемся на этой странице
     */
    public ProductsPage checkField(String text){
        fillInputField(dialogFieldName, text);
        Assertions.assertEquals(text, dialogFieldName.getAttribute("value"),
                "Значение поля не соответствует введенному");
        System.out.println("Полученный текст из поля ввода: " +  dialogFieldName.getAttribute("value"));
        return this;
    }

    /**
     * Функция клика на любое подменю выпадающего списка в окне добавления товара
     *
     * @param nameSubMenu - наименование подменю
     * @return ProductsPage - т.е. переходим на страницу {@link ProductsPage}
     */
    public ProductsPage selectSubMenuSelect(String nameSubMenu) {
        for (WebElement menuItem : listSelectTypeSubMenu) {
            if (menuItem.getText().equalsIgnoreCase(nameSubMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                return this;
            }
        }
        Assertions.fail("Подменю '" + nameSubMenu + "' не было найдено в окне добавления товара!");
        return this;
    }

    /**
     * Проверка, что в выпадающем списке выбрано именно нужное значение
     *
     * @return ProductsPage - т.е. остаемся на этой странице
     */
    public ProductsPage checkTypeSelected(String selectedValue){
        Assertions.assertEquals(selectedValue, dialogSelectType.getAttribute("value"),
                "Значение не соответствует выбранному");
        System.out.println("В списке выбрано значение: " + dialogSelectType.getAttribute("value"));
        return this;
    }

    /**
     * Проверка, поставлена ли галочка на чекбокс
     *
     * @return ProductsPage - т.е. остаемся на этой странице
     */
    public ProductsPage checkCheckboxSelected(WebElement element, boolean isSelected){
        Assertions.assertEquals(isSelected, element.isSelected(),
                "Чекбокс либо выбран, либо наоборот не выбран");
        return this;
    }

    /**
     * Проверка модального окна добавления нового товара в таблицу
     *
     * @return ProductsPage - т.е. остаемся на этой странице
     */
    public ProductsPage checkDialog (String header, String checkboxId,
                                     String checkboxType, String textCheckbox, boolean checkboxIsSelected){
        waitUtilElementToBeVisible(dialogHeader);
        Assertions.assertEquals(header, dialogHeader.getText(), "Не найден заголовок окна добавления товара");
        System.out.println("Заголовок окна добавления товара: " + dialogHeader.getText());

        waitUtilElementToBeVisible(dialogSelectType);

        WebElement btnCheck = null; // Хранение самой кнопки чекбокса
        for (WebElement checkbox: dialogCheckBoxExotic) {
            if (checkbox.getAttribute("id").equals(checkboxId)
                    && checkbox.getAttribute("type").equals(checkboxType)){
                System.out.println("id = " + checkbox.getAttribute("id")
                        + ", type = " + checkbox.getAttribute("type"));
                btnCheck = checkbox;    // Если нашли, присвоить нашей переменной
            } else if (checkbox.getText().equals(textCheckbox)){
                System.out.println("Текст чекбокса: " + checkbox.getText());
            } else {
                Assertions.fail("Чекбокс не совпадает либо не существует с такими атрибутами или " +
                        "Текст чекбокса не совпадает со словом Экзотический");
            }
        }
        waitUtilElementToBeClickable(dialogSelectType).click();

        // Если мы передали true вызови метод, который кликает по чекбоксу
        if (checkboxIsSelected == true){
            clickBtnExotic(btnCheck);
        }

        checkCheckboxSelected(btnCheck, checkboxIsSelected);

        return this;
    }

    /**
     * Проверка, что окно создания товара закрылось успешно
     *
     * @return ProductsPage - т.е. остаемся на этой странице
     */
    public ProductsPage checkWindowClosed(){
        boolean isInvisible = waitUtilElementToBeInvisible(dialogHeader);
        System.out.println("Окно закрыто: " + isInvisible);
        Assertions.assertTrue(isInvisible, "Окно не закрыто!");

        return this;
    }

    /**
     * Проверка, что в таблицу добавился новый товар
     *
     * @return ProductsPage - т.е. остаемся на этой странице
     */
    public ProductsPage checkNewAddToTable(String name, String type, boolean isExotic){
        System.out.println("Размер таблицы товаров: " + listRowsTable.size());

        WebElement rowTable = listRowsTable.get(listRowsTable.size() - 1);
        List<String> elementsOfRowListExpected = Arrays.asList(name, type, String.valueOf(isExotic));
        boolean allEqual = true;

        String strWithOutId = rowTable.getText().substring(2);

        List<String> elementsOfRowList = Arrays.asList(strWithOutId.split(" ", -1));

        for (int i = 0; i < elementsOfRowList.size(); i++) {
            if (!elementsOfRowList.get(i).equals(elementsOfRowListExpected.get(i))) {
                allEqual = false;
                break;
            }
        }
        if (!allEqual){
            Assertions.fail("Элементы не совпадают!" +
                    "\nСами элементы:\t\t" + strWithOutId +
                    "\nПереданная строка:\t" + String.join(" ", elementsOfRowListExpected));
        }

        return this;
    }

    /**
     * Функция наведения мыши на любой пункт меню
     *
     * @param nameBaseMenu - наименование меню
     * @return ProductsPage - т.е. остаемся на этой странице
     */
    public ProductsPage selectBaseMenuProductsPage(String nameBaseMenu) {
        for (WebElement menuItem : listBaseMenu) {
            if (menuItem.getText().trim().equalsIgnoreCase(nameBaseMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                System.out.println("Найдено меню: " + menuItem.getText().trim());
                return this;
            }
        }
        Assertions.fail("Меню '" + nameBaseMenu + "' не было найдено на странице товаров!");
        return this;
    }

    /**
     * Функция клика на любое подменю
     *
     * @param nameSubMenu - наименование подменю
     * @return ProductsPage - т.е. остаемся на этой странице
     */
    public ProductsPage selectSubMenuProductsPage(String nameSubMenu) {
        for (WebElement menuItem : listSubMenu) {
            if (menuItem.getText().equalsIgnoreCase(nameSubMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                return this;
            }
        }
        Assertions.fail("Подменю '" + nameSubMenu + "' не было найдено на странице товаров!");
        return this;
    }

}
