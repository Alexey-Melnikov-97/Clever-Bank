package org.example.InterestCalculation;

import java.util.Timer;


/**
 * Класс InterestCalculator представляет средство для расчета и начисления процентов на банковские счета.
 * Расчет выполняется периодически через определенные интервалы времени.
 */
public class InterestCalculator {
    private static final long INTERVAL = 30 * 1000;

    /**
     * Метод start запускает таймер, который выполняет задачу начисления процентов через определенные интервалы времени.
     */
    public void start() {
        Timer timer = new Timer();
        timer.schedule(new InterestTask(), 0, INTERVAL);
    }
}
