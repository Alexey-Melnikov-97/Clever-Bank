package org.example.InterestCalculation;

import org.example.ConnectionMaker;

import java.io.FileNotFoundException;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.time.Month;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InterestCalculator {
    private static final long INTERVAL = 30 * 1000;
    private static LocalDateTime firstDay = LocalDateTime.of(1990, Month.JANUARY, 1, 0, 0);


    public void start() {
        Timer timer = new Timer();
        timer.schedule(new InterestTask(), 0, INTERVAL);
    }

    private class InterestTask extends TimerTask {
        @Override
        public void run() {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<Boolean> conditionResult = executor.submit(this::check);
            try {
                if (conditionResult.get()) {
                    executor.execute(this::CreateCalculation);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            executor.shutdown();
        }
        private boolean check() {
            // Узнаем текущее время, сравниваем его с первым днем месяца, добавляем сравнение со статическим LocalDateTime
            // для того, чтобы не начислять каждые 30 секунд первого дня.
            boolean isTrue = false;
            LocalDateTime timestamp = LocalDateTime.now();
            if (timestamp.getDayOfMonth() == 1) {
                if (timestamp.getYear() != firstDay.getYear() && timestamp.getMonth() != firstDay.getMonth()
                        && timestamp.getDayOfMonth() != firstDay.getDayOfMonth()) {
                    firstDay = timestamp;
                    isTrue = true;
                }
            }
            return isTrue;
        }

        private void CreateCalculation() {
            try {
                // Получаем процент
                Map<String, Object> config = ConfigReader.getConfig();
                double interestRate = (double) config.get("interestRate");
                // Подключаемся к бд и создаем мапу с деньгами
                Connection connection = ConnectionMaker.getConnection();
                String query = "SELECT id, amount FROM bank_account";
                PreparedStatement statement = connection.prepareStatement(query);
                Map<Integer, Double> money = new HashMap<>();
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        money.put(resultSet.getInt("id"), resultSet.getDouble("amount"));
                    }
                }
                // Начисляем в мапу проценты
                for (Map.Entry<Integer, Double> entry : money.entrySet()) {
                    int id = entry.getKey();
                    double value = entry.getValue() * interestRate;
                    money.put(id, value);
                }
                // Закидываем мапу назад в таблицу
                for (Map.Entry<Integer, Double> entry : money.entrySet()) {
                    String queryTwo = "UPDATE bank_account SET amount = ? WHERE id = ?";
                    DecimalFormat decimalFormat = new DecimalFormat("#,##0,00");
                    PreparedStatement statementTwo = connection.prepareStatement(queryTwo);
                    statementTwo.setString(1, decimalFormat.format(entry.getValue()));
                    statementTwo.setInt(2, entry.getKey());
                    statementTwo.executeUpdate();
                    statementTwo.close();
                }
                // Закрытие ресурсов
                statement.close();
                connection.close();
                // Пишем о начислении процента
                System.out.println("Начислены процентына остаток счета");
            } catch (FileNotFoundException e) {
                System.out.println("Ошибка чтения конфигурационного файла");
            } catch (SQLException e) {
                System.out.println("Ошибка подключения к базе данных");
            }
        }
    }
}
