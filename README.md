# java-automation
Автоматизация тест-кейсов добавления товара в таблицу с помощью Cucumber и Allure
- Класс [TestRunner](https://github.com/Salimgareev/java-automation/blob/1015ead488f11dcb20c4436a159b6cb8cfc5fbee/src/test/java/org/example/runner/TestRunner.java) запускает тесты
- Класс [Hooks](https://github.com/Salimgareev/java-automation/blob/1015ead488f11dcb20c4436a159b6cb8cfc5fbee/src/test/java/org/example/steps/Hooks.java) содержит пре и пост степы 
- Тестирование веба
  - Класс [HomePage](https://github.com/Salimgareev/java-automation/blob/1015ead488f11dcb20c4436a159b6cb8cfc5fbee/src/main/java/org/example/pages/HomePage.java) содержит степы для домашней страницы
  - Класс [ProductsPage](https://github.com/Salimgareev/java-automation/blob/1015ead488f11dcb20c4436a159b6cb8cfc5fbee/src/main/java/org/example/pages/ProductsPage.java) содержит степы для страницы товаров
- Тестирование БД
  - Класс [JdbcTest](https://github.com/Salimgareev/java-automation/blob/1015ead488f11dcb20c4436a159b6cb8cfc5fbee/src/test/java/org/example/steps/JdbcTest.java) содержит степы для тестирования БД