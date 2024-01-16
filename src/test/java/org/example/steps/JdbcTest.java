package org.example.steps;

import io.cucumber.java.bg.И;
import org.junit.jupiter.api.Assertions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class JdbcTest {
    /**
     * Переменная хранит id, чтобы добавить/удалить элемент в таблице
     */
    private static int foodId = 0;

    /**
     * Проверка кол-ва столбцов таблицы
     *
     * @param nameTable Имя таблицы
     * @param countColumnExpected Кол-во строк, которое мы ожидаем в таблице
     */
    @И("Проверка, что таблица {string} содержит {int} столбца")
    public void checkCountColumnTable(String nameTable, int countColumnExpected) throws SQLException {
            ResultSet data = getResultSet(nameTable);
            ResultSetMetaData mData = data.getMetaData();
            Assertions.assertEquals(countColumnExpected, mData.getColumnCount(),
                    "Кол-во столбцов не равно ожидаемому кол-ву!");
    }

    /**
     * Генерация id для добавления новой строки в таблицу
     *
     * @param nameTable Имя таблицы
     */
    @И("Сгенерировать id элемента для добавления в таблицу {string}")
    public static void generateFoodID(String nameTable) throws SQLException {
        int strRows = findCountRowsTable(nameTable);
        foodId = strRows + 1;
    }

    /**
     * Добавить новую строку в таблицу
     */
    @И("Добавить в таблицу {string} товар с названием {string} типом {string} и экзотичностью {int}")
    public void addDataToTable(String nameTable, String name, String type, int exotic) throws SQLException {
        String insert = "INSERT INTO " + nameTable + " VALUES (?, ?, ?, ?);";
        PreparedStatement ps = Hooks.getConnection().prepareStatement(insert);
        ps.setInt(1, foodId);
        ps.setString(2, name);
        ps.setString(3, type);
        ps.setInt(4, exotic);
        ps.executeUpdate();
    }

    /**
     * Проверка значений добавленной строки в таблице
     */
    @И("Проверка в таблице {string} значений названия {string} типа {string} экзотичности {int} добавленной строки")
    public void checkDataLastAddRowInTable(String nameTable, String expectName,
                                           String expectType, int expectExotic) throws SQLException {
        ResultSet resultSet = getResultSetRow(nameTable, foodId);
        resultSet.first();

        List<String> listExpected = Arrays.asList(String.valueOf(foodId),
                expectName, expectType, String.valueOf(expectExotic));

        List<String> listReal = Arrays.asList(
                String.valueOf(resultSet.getInt("food_id")),
                resultSet.getString("food_name"),
                resultSet.getString("food_type"),
                String.valueOf(resultSet.getInt("food_exotic"))
        );

        for (int i = 0; i < listReal.size(); i++) {
            Assertions.assertEquals(listExpected.get(i), listReal.get(i),
                    "Одно значение строки или все значения строки не совпадают с переданными");
        }

        listReal.forEach(System.out::println);
    }

    /**
     * Удаление добавленной строки из таблицы
     */
    @И("Удалить из таблицы {string} добавленную строку")
    public void deleteRowFromTable(String nameTable) throws SQLException {
        String sql = "DELETE FROM " + nameTable + " WHERE FOOD_ID = " + foodId + ";";
        Hooks.getStatement().executeUpdate(sql);
        int countRowsAfterDelete = findCountRowsTable(nameTable);
        Assertions.assertEquals(foodId - 1, countRowsAfterDelete,
                "Удаление выполнено некорректно! Кол-во строк после удаления " +
                        "не совпадает с ожидаемым значением");
    }

    /**
     * Получает ResultSet по имени таблицы
     *
     * @param nameTable Имя таблицы
     * @return resultSet - т.е. ResultSet, в котором будут все строки таблицы
     */
    private static ResultSet getResultSet(String nameTable) throws SQLException {
        String sql = "SELECT * FROM " + nameTable + ";";
        return Hooks.getStatement().executeQuery(sql);
    }

    /**
     * Получает определенную строку по имени таблицы и id строки
     *
     * @return resultSet - т.е. ResultSet, в котором будет строка таблицы
     */
    private ResultSet getResultSetRow(String nameTable, int id) throws SQLException {
        String sql = "SELECT * FROM " + nameTable + " WHERE FOOD_ID = " + id + ";";
        return Hooks.getStatement().executeQuery(sql);
    }
    /**
     * Ищет кол-во строк в таблице по имени
     *
     * @return rows - т.е. кол-во строк в таблице
     */
    private static int findCountRowsTable(String nameTable) throws SQLException {
        ResultSet resultSet = getResultSet(nameTable);
        resultSet.absolute(-1);
        int rows = resultSet.getRow();
        System.out.println("Кол-во строк в таблице: " + rows);

        return rows;
    }
}
