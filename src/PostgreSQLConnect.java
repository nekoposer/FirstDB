import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQLConnect {
    private Connection connection;

    public PostgreSQLConnect(String url, String user, String password) {
        try {
            // Загружаем драйвер PostgreSQL
            Class.forName("org.postgresql.Driver");

            // Устанавливаем соединение с базой данных
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Не удалось найти драйвер PostgreSQL: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к PostgreSQL: " + e.getMessage());
        }
    }

    public List<List<String>> executeSelect(String query) {
        List<List<String>> result = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                List<String> row = new ArrayList<>();
                row.add(resultSet.getString(1));
                row.add(resultSet.getString(2));
                row.add(resultSet.getString(3));
                result.add(row);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при выполнении SQL-запроса: " + e.getMessage());
        }
        return result;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при закрытии соединения: " + e.getMessage());
        }
    }
}