import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/training_hw_5"; // Замените на ваш URL базы данных
        String user = "admin"; // Замените на вашего пользователя
        String password = "root"; // Замените на ваш пароль

        PostgreSQLConnect postgresConnect = new PostgreSQLConnect(url, user, password);

        // Пример выполнения запроса к базе данных
        String select = """
                select number, product, location
                from audit_department;
                """;
        List<List<String>> result = postgresConnect.executeSelect(select);

        System.out.printf("%-17s %-10s %-10s \n","Ревизионный номер", "Тип изделия", "Адрес расположения");
        if (!result.isEmpty()) {
            for (List<String> row : result) {
                System.out.printf("%-17s %-10s  %-10s \n",row.get(0),row.get(1),row.get(2));
            }
        }

        postgresConnect.close();
    }
}