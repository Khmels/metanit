package chapter02_Basics;

public class P2_15_ReturnOperator {
    public static void main(String[] args) {
        //return возвращаемое_значение;

        ///Cannot resolve method 'sum' in 'P15_ReturnOperator'
        ///Create method 'sum' ~~~~ Alt+Shift+Enter
        int x = sum(1, 2, 3);
        int y = sum(1, 4, 9);
        System.out.println(x);  // 6
        System.out.println(y);  // 14

        System.out.println("---------------------");

        System.out.println(daytime(7));     // Good morning
        System.out.println(daytime(13));    // Good after noon
        System.out.println(daytime(18));    // Good evening
        System.out.println(daytime(2));     // Good night
        System.out.println(daytime(25));    // ! Invalid data

        System.out.println("---------------------");
        daytimeExit(10);
    }

    private static int sum(int i, int i1, int i2) {
        return i+i1+i2;
    }

    //несколько return (условие)
    static String daytime(int hour){

        if (hour >24 || hour < 0)
            return "! Invalid data";
        else if(hour > 21 || hour < 6)
            return "Good night";
        else if(hour >= 15)
            return "Good evening";
        else if(hour >= 11)
            return "Good after noon";
        else
            return "Good morning";
    }

    //--- Выход из метода ---

    // Выход из void метода
    // Возвращаемое значение после return указывать в этом случае не нужно.

    static void daytimeExit(int hour){

        if (hour >24 || hour < 0)
            return; // просто выйдет из метода
        if(hour > 21 || hour < 6)
            System.out.println("Good night");
        else if(hour >= 15)
            System.out.println("Good evening");
        else if(hour >= 11)
            System.out.println("Good after noon");
        else
            System.out.println("Good morning");
    }

}
