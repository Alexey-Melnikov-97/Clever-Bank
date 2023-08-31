package org.example;

import org.example.entities.Transactions;
import org.example.entities.TransactionsDAO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Receipt {

    //Создаем объект транзакции и узнаем номер нового чека
    TransactionsDAO trdao = new TransactionsDAO(ConnectionMaker.getConnection());
    private int receiptNumber = trdao.getMaxId() + 1;

    private final LocalDateTime dateTime = LocalDateTime.now();

    public Receipt() throws SQLException {
    }

    public void makeDepositWithdraw(String transaction, String sendersAccount, double amount) throws SQLException {

        // добавляем операцию в таблицу
        Transactions tr = new Transactions(receiptNumber, dateTime, transaction, amount);
        trdao.create(tr);

        // делаем тектовый чек
        int length = 50;
        String num = Integer.toString(receiptNumber);
        receiptNumber++;
        DateTimeFormatter receiptDtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
        String path = "src/check/Receipt " + num + " from " + receiptDtf.format(dateTime) + ".txt";
        File newReceipt = new File(path);

        try (FileWriter fw = new FileWriter(newReceipt)) {
            //1-я строка
            for (int i = 0; i < length; i++) {
                fw.write("-");
            }

            //2-я строка
            fw.write("\n");
            fw.write("|                 Банковский Чек                 |");

            //3-я строка
            fw.write("\n");
            String t3 = "| Чек:";
            fw.write(t3);
            for (int i = 2; i < length - 6 - num.length(); i++) {
                fw.write(" ");
            }
            fw.write(num + " |");

            //4-я строка
            fw.write("\n");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("| dd-MM-yyyy                            HH:mm:ss |");
            fw.write(dtf.format(dateTime));

            //5-я строка
            fw.write("\n");
            String t5 = "| Тип транзакции:";
            fw.write(t5);
            for (int i = 2; i < length - 17 - transaction.length(); i++) {
                fw.write(" ");
            }
            fw.write(transaction + " |");

            //6-я строка
            fw.write("\n");
            String t8 = "| Счет:";
            fw.write(t8);
            for (int i = 2; i < length - 7 - sendersAccount.length(); i++) {
                fw.write(" ");
            }
            fw.write(sendersAccount + " |");

            //7-я строка
            fw.write("\n");
            String t10 = "| Сумма:";
            String amountStr = Double.toString(amount);
            fw.write(t10);
            for (int i = 6; i < length - 8 - amountStr.length(); i++) {
                fw.write(" ");
            }
            fw.write(amount + " BYN |");

            //8-я строка
            fw.write("\n");
            for (int i = 0; i < length; i++) {
                fw.write("-");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void makeMoneyOrder(String transaction, String sendersBank, String payeesBank, String sendersAccount,
                               String payeesAccount, double amount) throws SQLException {

        // добавляем операцию в таблицу
        Transactions tr = new Transactions(receiptNumber, dateTime, transaction, amount);
        trdao.create(tr);

        // делаем чек
        int length = 50;
        String num = Integer.toString(receiptNumber);
        receiptNumber++;
        DateTimeFormatter receiptDtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
        String path = "src/check/Receipt " + num + " from " + receiptDtf.format(dateTime) + ".txt";
        File newReceipt = new File(path);

        try (FileWriter fw = new FileWriter(newReceipt)) {
            //1-я строка
            for (int i = 0; i < length; i++) {
                fw.write("-");
            }

            //2-я строка
            fw.write("\n");
            fw.write("|                 Банковский Чек                 |");

            //3-я строка
            fw.write("\n");
            String t3 = "| Чек:";
            fw.write(t3);
            for (int i = 2; i < length - 6 - num.length(); i++) {
                fw.write(" ");
            }
            fw.write(num + " |");

            //4-я строка
            fw.write("\n");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("| dd-MM-yyyy                            HH:mm:ss |");
            fw.write(dtf.format(dateTime));

            //5-я строка
            fw.write("\n");
            String t5 = "| Тип транзакции:";
            fw.write(t5);
            for (int i = 2; i < length - 17 - transaction.length(); i++) {
                fw.write(" ");
            }
            fw.write(transaction + " |");

            //6-я строка
            fw.write("\n");
            String t6 = "| Банк отправителя:";
            fw.write(t6);
            for (int i = 2; i < length - 19 - sendersBank.length(); i++) {
                fw.write(" ");
            }
            fw.write(sendersBank + " |");

            //7-я строка
            fw.write("\n");
            String t7 = "| Банк получателя:";
            fw.write(t7);
            for (int i = 2; i < length - 18 - payeesBank.length(); i++) {
                fw.write(" ");
            }
            fw.write(payeesBank + " |");

            //8-я строка
            fw.write("\n");
            String t8 = "| Счет отправителя:";
            fw.write(t8);
            for (int i = 2; i < length - 19 - sendersAccount.length(); i++) {
                fw.write(" ");
            }
            fw.write(sendersAccount + " |");

            //9-я строка
            fw.write("\n");
            String t9 = "| Счет получателя:";
            fw.write(t9);
            for (int i = 2; i < length - 18 - payeesAccount.length(); i++) {
                fw.write(" ");
            }
            fw.write(payeesAccount + " |");

            //10-я строка
            fw.write("\n");
            String t10 = "| Сумма:";
            String amountStr = Double.toString(amount);
            fw.write(t10);
            for (int i = 6; i < length - 8 - amountStr.length(); i++) {
                fw.write(" ");
            }
            fw.write(amount + " BYN |");

            //11-я строка
            fw.write("\n");
            for (int i = 0; i < length; i++) {
                fw.write("-");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
