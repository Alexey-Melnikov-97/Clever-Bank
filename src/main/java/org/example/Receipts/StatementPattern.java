package org.example.Receipts;

import org.example.entities.Transactions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Класс `StatementPattern` предоставляет методы для создания финансовых выписок в виде текстовых документов.
 * Выписки содержат информацию о клиенте, банковском счете, периоде, дате формирования, остатке и выполненных транзакциях.
 */
public class StatementPattern {
    /**
     * Создает финансовую выписку с информацией о клиенте, банковском счете, периоде, дате формирования, остатке и выполненных транзакциях.
     * Выписка сохраняется в текстовом формате в файле.
     *
     * @param name              имя клиента
     * @param account           номер банковского счета
     * @param from              начальная дата периода
     * @param to                конечная дата периода
     * @param balance           текущий остаток на счете
     * @param transactionsList  список выполненных транзакций
     */
    public void createStatement(String name, String account, LocalDateTime from, LocalDateTime to, double balance,
                                List<Transactions> transactionsList) {

        DateTimeFormatter receiptDtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm");
        LocalDateTime now = LocalDateTime.now();
        String path = "src/statement-money/Statement from " + receiptDtf.format(now) + ".txt";
        File newReceipt = new File(path);

        try (FileWriter fw = new FileWriter(newReceipt)) {

            //1
            fw.write("                           Выписка                          ");
            //2
            fw.write("\n");
            fw.write("                         Clever-Bank                        ");
            //3
            fw.write("\n");
            fw.write("Клиент                       | " + name);
            //4
            fw.write("\n");
            fw.write("Счет                         | " + account);
            //5
            fw.write("\n");
            fw.write("Валюта                       | BYN");
            //6
            DateTimeFormatter fromToFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            fw.write("\n");
            fw.write("Период                       | " + fromToFormat.format(from) + " - " + fromToFormat.format(to));
            //7
            fw.write("\n");
            fw.write("Дата и время формирования    | " + receiptDtf.format(to));
            //8
            fw.write("\n");
            fw.write("остаток                      | " + balance + " BYN");
            //9
            fw.write("\n");
            fw.write("   Дата    |            Примечание            |    Сумма    ");
            //10
            fw.write("\n");
            fw.write("------------------------------------------------------------");
            //11 - ...
            for (Transactions trans : transactionsList) {
                DateTimeFormatter transFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                int length = 33 - trans.getTransaction().length();
                fw.write("\n");
                fw.write(transFormat.format(trans.getDateTime()) + " | " + trans.getTransaction());
                for (int i = 0; i < length; i++) {
                    fw.write(" ");
                }
                fw.write("| " + trans.getAmount());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Создает финансовую выписку за указанный период с информацией о клиенте, банковском счете, периоде, дате формирования, остатке,
     * сумме пополнений и сумме снятий с банковского счета. Выписка сохраняется в текстовом формате в файле.
     *
     * @param name              имя клиента
     * @param account           номер банковского счета
     * @param from              начальная дата периода
     * @param to                конечная дата периода
     * @param balance           текущий остаток на счете
     * @param plus              сумма пополнений счета
     * @param minus             сумма снятий со счета
     */
    public void createMoneyStatement(String name, String account, LocalDateTime from, LocalDateTime to, double balance,
                                     double plus, double minus) {
        DateTimeFormatter receiptDtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm");
        LocalDateTime now = LocalDateTime.now();
        String path = "src/statement-money/Statement from " + receiptDtf.format(now) + ".txt";
        File newReceipt = new File(path);

        try (FileWriter fw = new FileWriter(newReceipt)) {

            //1
            fw.write("                       Money Statement                      ");
            //2
            fw.write("\n");
            fw.write("                         Clever-Bank                        ");
            //3
            fw.write("\n");
            fw.write("Клиент                       | " + name);
            //4
            fw.write("\n");
            fw.write("Счет                         | " + account);
            //5
            fw.write("\n");
            fw.write("Валюта                       | BYN");
            //6
            DateTimeFormatter fromToFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            fw.write("\n");
            fw.write("Период                       | " + fromToFormat.format(from) + " - " + fromToFormat.format(to));
            //7
            fw.write("\n");
            fw.write("Дата и время формирования    | " + receiptDtf.format(to));
            //8
            fw.write("\n");
            fw.write("остаток                      | " + balance + " BYN");
            //9
            fw.write("\n");
            fw.write("           Приход            | Уход           ");
            //10
            fw.write("\n");
            fw.write("           -----------------------------------");
            //11
            String pl = Double.toString(plus);
            fw.write("\n");
            fw.write("           " + pl + " BYN");
            for (int i = 0; i < 18 - pl.length() - 4; i++) {
                fw.write(" ");
            }
            fw.write("| ");
            fw.write(minus + " BYN");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
