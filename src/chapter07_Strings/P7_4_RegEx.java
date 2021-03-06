package chapter07_Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P7_4_RegEx {
    public static void main(String[] args) {

        //  split(). В качестве параметра он может принимать регулярное выражение, которое представляет критерий разделения строки.
        String text = "FIFA will never regret it";
        String[] words = text.split("\\s*(\\s|,|!|\\.)\\s*");
        for (String word : words) {
            System.out.println(word);
        }

        /*
        Подвыражние "\\s"  представляет пробел.
        Звездочка указывает, что символ может присутствовать от 0 до бесконечного количества раз.
        То есть добавляем звездочку и мы получаем неопределенное количество идущих подряд пробелов - "\\s*"
        (то есть неважно, сколько пробелов между словами). Причем пробелы может вообще не быть.

        В скобках указывает группа выражений, которая может идти после неопределенного количества пробелов.
        Группа позволяет нам определить набо значений через вертикальную черту,
        и подстрока должна соответствовать одному из этих значений.

        То есть в группе "\\s|,|!|\\." подстрока может соответствовать пробелу, запятой, восклицательному знаку или точке.
        Причем поскольку точка представляет специальную последовательность, то, чтобы указать, что мы имеем в виду имеено знак точки,
        а не специальную последовательность, перед точкой ставим слеши.
         */

        //--- Соответствие строки. matches
        // matches() принимает регулярное выражение и возвращает true, если строка соответствует этому выражению. Иначе возвращает false.
        System.out.println("---------------------");
        String input = "+12343454556";
        boolean result = input.matches("(\\+*)\\d{11}");
        if (result) {
            System.out.println("It is a phone number");
        } else {
            System.out.println("It is not a phone number!");
        }
        System.out.println("---------------------");

        /*
        В данном случае в регулярном выражение сначала определяется группа "(\\+*)".
        То есть вначале может идти знак плюса, но также он может отсутствовать.

        Далее смотрим, соответствуют ли последующие 11 символов цифрам.
        Выражение "\\d" представляет цифровой символ, а число в фигурных скобках - {11} - сколько раз данный тип символов должен повторяться.

        То есть мы ищем строку, где вначале может идти знак плюс (или он может отсутствовать), а потом идет 11 цифровых символов.
         */

        //--- Класс Pattern

        // Для задания подобного шаблона и поиска подстрок в строке, которые удовлетворяют данному шаблону, в Java определены классы Pattern и Matcher.
        //
        // Для простого поиска соответствий в классе Pattern определен статический метод boolean matches(String pattern, CharSequence input).
        // Данный метод возвращает true, если последовательность символов input полностью соответствует шаблону строки pattern:


        String input2 = "Hello";
        boolean found = Pattern.matches("Hello", input2);

        if (found)
            System.out.println("Найдено");
        else
            System.out.println("Не найдено");

        // Но, как правило, для поиска соответствий применяется другой способ - использование класса Matcher.
        System.out.println("---------------------");

        //--- Класс Matcher

        String input3 = "Hello world! Hello Java!";
        // создать объект Pattern с помощью статического метода compile(), который позволяет установить шаблон:
        Pattern pattern = Pattern.compile("Hello");

        /*
        В классе Pattern также определен метод matcher(String input), который в качестве параметра принимает строку,
        где надо проводить поиск, и возвращает объект Matcher:
         */
        Matcher matcher = pattern.matcher(input3);
        // Затем у объекта Matcher вызывается метод matches() для поиска соответствий шаблону в тексте:


        String input4 = "Hello";
//        Pattern pattern2 = Pattern.compile("Hello");
        Pattern pattern2 = Pattern.compile("hello", Pattern.CASE_INSENSITIVE);
        Matcher matcher2 = pattern2.matcher(input4);
        boolean foundMatch = matcher2.matches();
        if (foundMatch)
            System.out.println("Найдено");
        else
            System.out.println("Не найдено");
        System.out.println("---------------------");


        String inputString = "Hello Java! Hello JavaScript! JavaSE 8.";
        Pattern pattern3 = Pattern.compile("Hello Java! Hello JavaScript! JavaSE 8.");
        String patternStr = "Java!";
        boolean foundCheck = Pattern.matches(patternStr, inputString);
        System.out.println(foundCheck);
        /*
        Для этого применим шаблон "Java(\\w*)". Данный шаблон использует синтаксис регулярных выражений.
        Слово "Java" в начале говорит о том, что все совпадения в строке должны начинаться на Java.

        Выражение (\\w*) означает, что после "Java" в совпадении может находиться любое количество алфавитно-цифровых символов.
        Выражение \w означает алфавитно-цифровой символ, а звездочка после выражения указывает на неопределенное их количество.

        И чтобы java не рассматривала \w как эскейп-последовательность, как \n, то выражение экранируется еще одним слешем.
         */
        Matcher matcher3 = pattern3.matcher(inputString);
        boolean foundMatch2 = matcher3.matches();
        System.out.println(foundMatch2);
        System.out.println();

        while (matcher3.find())
            System.out.println(matcher3.group());

        System.out.println("---------------------");

        String stringIn = "Hello Java! Hello JavaScript! JavaSE 8.";
        Pattern patternIn = Pattern.compile("Jav(\\w*)");
        Matcher matcherIn = patternIn.matcher(stringIn);
        while (matcherIn.find()) {
            System.out.println(matcherIn.group());
        }

        System.out.println("---------------------");

        //--- Замена в строке

        String newStr = matcherIn.replaceAll("HTML");
        System.out.println(newStr); // Hello HTML! Hello HTML! HTML 8.

        // в классе String также имеется метод replaceAll() с подобным действием
        String input6 = "Hello Java! Hello JavaScript! JavaSE 8.";
        String myStr =input6.replaceAll("Java(\\w*)", "HTML");
        System.out.println(myStr); // Hello HTML! Hello HTML! HTML 8.
        System.out.println("---------------------");

        //--- Разделение строки на лексемы

        //С помощью метода String[] split(CharSequence input) класса Pattern можно разделить строку на массив подстрок по определенному разделителю.
        String input7 = "Hello Java! Hello JavaScript! JavaSE 8.";
        Pattern pattern4 = Pattern.compile("[ ,.!?]");
        String[] words2 = pattern4.split(input7);
        for(String word:words2) {
            System.out.println(word);
        }

        // При этом все символы-разделители удаляются.
        // Однако, данный способ разбивки не идеален: у нас остаются некоторые пробелы,
        // которые расцениваются как лексемы, а не как разделители.
        //
        // Для более точной и изощренной разбивки нам следует применять элементы регулярных выражений. Так, заменим шаблон на следующий:


        Pattern patternOnlyWords = Pattern.compile("\\s*(\\s|,|!|\\.)\\s*");


        //--- Жадный режим квантификатора
        // javarush.ru/groups/posts/regulyarnye-vyrazheniya-v-java

        // Особенностью квантификаторов является возможность использования их в разных режимах: жадном, сверхжадном и ленивом.
        // Сверхжадный режим включается добавлением символа «+» после квантификатора, а ленивый – символа «?». Например:

        //"А.+а"  // жадный режим
        //"А.++а" // сверхжадный режим
        //"А.+?а" // ленивый режим


        System.out.println("---------------------");
        String text1 = "Егор Алла Александр";
        Pattern pattern5 = Pattern.compile("А.+а");
        Matcher matcher5 = pattern4.matcher(text1);
        while (matcher5.find()) {
            System.out.println(text1.substring(matcher5.start(), matcher5.end()));
        }


    }
}

// Некоторые методы класса String принимают регулярные выражения и используют их для выполнения операций над строками.


/*
    основные методы класса Matcher:
        boolean matches():              возвращает true, если вся строка совпадает с шаблоном
        boolean find():                 возвращает true, если в строке есть подстрока, которая совпадает с шаблоном,
                                            и переходит к этой подстроке
        String group():                 возвращает подстроку, которая совпала с шаблоном в результате вызова метода find.
                                            Если совпадение отсутствует, то метод генерирует исключение IllegalStateException.
        int start():                    возвращает индекс текущего совпадения
        int end():                      возвращает индекс следующего совпадения после текущего
        String replaceAll(String str):  заменяет все найденные совпадения подстрокой str и возвращает измененную строку с учетом замен
 */

/*  для поиска совпадений границ строк или текста
        ^	начало строки
        $	конец строки
        \b	граница слова
        \B	не граница слова
        \A	начало ввода
        \G	конец предыдущего совпадения
        \Z	конец ввода
        \z	конец ввода
 */

/* для поиска символьных классов
        \d	цифровой символ
        \D	нецифровой символ
        \s	символ пробела
        \S	непробельный символ
        \w	буквенно-цифровой символ или знак подчеркивания
        \W	любой символ, кроме буквенного, цифрового или знака подчеркивания
        .	любой символ
 */

/* для поиска символов редактирования текста
        \t	            символ табуляции
        \n	            символ новой строки
        \r	            символ возврата каретки
        \f	            переход на новую страницу
        \u0085	        символ следующей строки
        \u2028	        символ разделения строк
        \u2029	        символ разделения абзацев
 */

/* для группировки символов
        [абв]	        любой из перечисленных (а,б, или в)
        [^абв]	        любой, кроме перечисленных (не а,б, в)
        [a-zA-Z]	    слияние диапазонов (латинские символы от a до z без учета регистра )
        [a-d[m-p]]	    объединение символов (от a до d и от m до p)
        [a-z&&[def]]	пересечение символов (символы d,e,f)
        [a-z&&[^bc]]	вычитание символов (символы a, d-z)
 */

/* для обозначения количества символов – квантификаторы. Квантификатор всегда следует после символа или группы символов.
        ?	    один или отсутствует
        *	    ноль или более раз
        +	    один или более раз
        {n}	    n раз
        {n,}	n раз и более
        {n,m}	не менее n раз и не более m раз
 */