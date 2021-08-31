package chapter12;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class P12_3_LocalDate {

    public static void main(String[] args) {

        LocalDate date = LocalDate.now(); // получаем текущую дату
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println(date);
        System.out.println(dayOfWeek);
        System.out.printf("%d.%d.%d \n", dayOfMonth, month, year);
        System.out.println("---------------------");

        LocalDate date2 = LocalDate.of(1994, 12, 31);
        System.out.println(date2);

        LocalDate date3 = LocalDate.of(1910, 7, 28);
        System.out.println("---------------------");

        date3 = date3.plusYears(4);
        date3 = date3.plusMonths(3);
        date3 = date3.plusDays(14);
        System.out.println(date3);   // 1914-11-11
        System.out.println("---------------------");

        date3 = date3.minusMonths(10);
        date3 = date3.minusDays(3);
        System.out.println(date3);   // 1914-01-08
    }
}

/*
Класс LocalDate из пакета java.time предназначен для работы с датами.
Функционал этого класса позволяет создавать даты и изменять их, добавляя и отнимая необходимое количество дней/месяцев/лет.
 */

/*
Основные методы LocalDate:

        static LocalDate now():                 возвращает объект, который представляет текущую дату
        static LocalDate
            of(int year, int month, int day):   возвращает объект, который представляет дату с определенными годом, месяцем и днем
        int getYear():                          возвращает год даты
        int getMonthValue():                    возвращает месяц
        int getDayOfMonth():                    возвращает день месяца (значение от 1 до 31)
        int getDayOfYear():                     возвращает номер дня года (значение от 1 до 365)
        DayOfWeek getDayOfWeek():               возвращает день недели в виде значения перечисления DayOfWeek
        LocalDate plusDays(int n):              добавляет к дате некоторое количество дней
        LocalDate plusWeeks(int n):             добавляет к дате некоторое количество недель
        LocalDate plusMonths(int n):            добавляет к дате некоторое количество месяцев
        LocalDate plusYears(int n):             добавляет к дате некоторое количество лет
        LocalDate minusDays(int n):             отнимает от даты некоторое количество дней
        LocalDate minusMonths(int n):           отнимает от даты некоторое количество месяцев
        LocalDate minusWeeks(int n):            отнимает от даты некоторое количество недель
        LocalDate minusYears(int n):            отнимает от даты некоторое количество лет
        int getMonthValue():                    возвращает месяц
 */