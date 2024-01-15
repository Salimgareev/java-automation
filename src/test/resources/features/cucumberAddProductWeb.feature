# language: ru

@all
@addproduct
Функция: Добавление товара в таблицу

  @correct
  Структура сценария: Успешное добавление товара в таблицу <checkboxIsSelected> <text> <nameMenuWindow> <selectedValue>
    * Здесь будет сценарий

    Примеры:
      | checkboxIsSelected  | text                    | nameMenuWindow  | selectedValue |
      | false               | йцю123qw!@#$%^&*()_+/\\ | Овощ            | VEGETABLE     |
      | true                | Ананас                  | Фрукт           | FRUIT         |