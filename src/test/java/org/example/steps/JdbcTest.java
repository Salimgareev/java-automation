package org.example.steps;

import org.example.basetestsclass.BaseJdbcTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class JdbcTest {
    @Test
    public void jdbcTest() throws SQLException {
        String nameTable = "FOOD";
        int strRows = findCountRowsTable(nameTable);

        int foodId = strRows + 1;
        String foodName = "Огурцы";
        String foodType = "VEGETABLE";
        int foodExotic = 0;

        addDataToTable(nameTable, foodId, foodName, foodType, foodExotic);
        checkDataLastAddRowInTable(nameTable, foodId, foodName, foodType, foodExotic);
        deleteRowFromTable(foodId, nameTable);
    }

    /**
     * Получает ResultSet по имени таблицы
     *
     * @param nameTable Имя таблицы
     * @return resultSet - т.е. ResultSet, в котором будут все строки таблицы
     */
    private ResultSet getResultSet(String nameTable) throws SQLException {
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
    private int findCountRowsTable(String nameTable) throws SQLException {
        ResultSet resultSet = getResultSet(nameTable);
        resultSet.absolute(-1);
        int rows = resultSet.getRow();
        System.out.println("Кол-во строк в таблице: " + rows);

        return rows;
    }

    /**
     * Добавить данные в таблицу
     */
    private void addDataToTable(String nameTable, int id, String name, String type, int exotic) throws SQLException {
        String insert = "INSERT INTO " + nameTable + " VALUES (?, ?, ?, ?);";
        PreparedStatement ps = Hooks.getConnection().prepareStatement(insert);
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setString(3, type);
        ps.setInt(4, exotic);
        ps.executeUpdate();
    }

    /**
     * Проверка значений добавленной строки в таблице
     */
    private void checkDataLastAddRowInTable(String nameTable, int expectId, String expectName,
                                              String expectType, int expectExotic) throws SQLException {
        ResultSet resultSet = getResultSetRow(nameTable, expectId);
        resultSet.first();

        List<String> listExpected = Arrays.asList(String.valueOf(expectId),
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
    private void deleteRowFromTable(int rowId, String nameTable) throws SQLException {
        String sql = "DELETE FROM " + nameTable + " WHERE FOOD_ID = " + rowId + ";";
        Hooks.getStatement().executeUpdate(sql);
        int countRowsAfterDelete = findCountRowsTable(nameTable);
        Assertions.assertEquals(rowId - 1, countRowsAfterDelete,
                "Удаление выполнено некорректно! Кол-во строк после удаления " +
                        "не совпадает с ожидаемым значением");
    }
}
