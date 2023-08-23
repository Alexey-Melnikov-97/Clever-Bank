package org.example;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DepositWithdrawReceipt {
    private static int receiptNumber = 1;
    private final LocalDateTime dateTime = LocalDateTime.now();

    /*public static void main(String[] args) {
        Receipt rc = new Receipt();
        rc.make("asdasdsadasd", "asdasdads", "asdasdadsadsd", "15245345",
                "124141241", 70.02);

    }*/
    public void make(String transaction, String sendersAccount, double amount) {
        int length = 50;
        String num = Integer.toString(receiptNumber);
        receiptNumber++;
        DateTimeFormatter receiptDtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
        String path = "src/check/Receipt " + num + " " + receiptDtf.format(dateTime) + ".txt";
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
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("| dd-MM-yyyy                               HH:mm |");
            fw.write(dtf.format(dateTime));

            //5-я строка
            fw.write("\n");
            String t5 = "| Тип транзакции:";
            fw.write(t5);
            for (int i = 2; i < length - 17 - transaction.length(); i++) {
                fw.write(" ");
            }
            fw.write(transaction + " |");

            //8-я строка
            fw.write("\n");
            String t8 = "| Счет:";
            fw.write(t8);
            for (int i = 2; i < length - 7 - sendersAccount.length(); i++) {
                fw.write(" ");
            }
            fw.write(sendersAccount + " |");

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
