package chapter07_Strings;

public class P7_3_StringBuffer_StringBuilder {

    //'java doc'
    //### public final class StringBuffer
    //        extends AbstractStringBuilder
    //        implements java.io.Serializable, Comparable<java.lang.StringBuffer>, CharSequence{}

    // StringBuffer - synchronized

    //'java doc'
    //###     public final class StringBuilder
    //             extends AbstractStringBuilder
    //             implements Serializable, Comparable<StringBuilder>, CharSequence{}

    public static void main(String[] args) {
        // метода capacity() можно получить количество символов, для которых зарезервирована память.
        // с помощью метода ensureCapacity() изменить минимальную емкость буфера символов:
        StringBuilder stringBuilder = new StringBuilder("Java");
        String str = "Java";
        StringBuffer strBuffer = new StringBuffer(str);
        System.out.println("Емкость: " + strBuffer.capacity()); // 20
        strBuffer.ensureCapacity(32);
        System.out.println("Емкость: " + strBuffer.capacity()); // 42 - про запас
        System.out.println("Длина: " + strBuffer.length()); // 4

        // Чтобы получить строку, которая хранится в StringBuffer, можно использовать стандартный метод toString():
        System.out.println(strBuffer.toString()); // Java
        System.out.println("---------------------");

        //--- Получение и установка символов

        // charAt() получает, а метод setCharAt() устанавливает символ по определенному индексу
        StringBuffer strBuffer1 = new StringBuffer("Java");
        char c = strBuffer1.charAt(0); // J
        System.out.println(c);
        strBuffer.setCharAt(0, 'c');
        System.out.println(strBuffer1.toString()); // cava

        // getChars() получает набор символов между определенными индексами:
        StringBuffer strBuffer2 = new StringBuffer("world");
        int startIndex = 1;
        int endIndex = 4;
        char[] buffer = new char[endIndex-startIndex];
        strBuffer2.getChars(startIndex, endIndex, buffer, 0);
        System.out.println(buffer); // orl
        System.out.println("---------------------");

        //--- Добавление в строку
        // append() добавляет подстроку в конец StringBuffer

        StringBuffer strBuffer3 = new StringBuffer("hello");
        strBuffer3.append(" world");
        System.out.println(strBuffer3.toString()); // hello world

        // insert() добавляет строку или символ по определенному индексу в StringBuffer:

        StringBuffer strBuffer4 = new StringBuffer("word");

        strBuffer4.insert(3, 'l');
        System.out.println(strBuffer4.toString()); //world

        strBuffer4.insert(0, "s");
        System.out.println(strBuffer4.toString()); //sworld
        System.out.println("---------------------");

        //--- Удаление символов
        // Метод delete() удаляет все символы с определенного индекса о определенной позиции,
        // а метод deleteCharAt() удаляет один символ по определенному индексу

        StringBuffer strBuffer5 = new StringBuffer("assembler");
        strBuffer5.delete(0,2);
        System.out.println(strBuffer5.toString()); //sembler

        strBuffer5.deleteCharAt(6);
        System.out.println(strBuffer5.toString()); //semble
        System.out.println("---------------------");

        //--- Обрезка строки
        // substring() обрезает строку с определенного индекса до конца, либо до определенного индекса:

        StringBuffer strBuffer6 = new StringBuffer("hello java!");
        String str1 = strBuffer6.substring(6); // обрезка строки с 6 символа до конца
        System.out.println(str1); //java!

        String str2 = strBuffer6.substring(3, 9); // обрезка строки с 3 по 9 символ
        System.out.println(str2); //lo jav

        //--- Изменение длины
        // Для изменения длины StringBuffer (не емкости буфера символов) применяется метод setLength().
        // Если StringBuffer увеличивается, то его строка просто дополняется в конце пустыми символами,
        // если уменьшается - то строка по сути обрезается:

        StringBuffer strBuffer7 = new StringBuffer("hello");
        strBuffer7.setLength(10);
        System.out.println(strBuffer7.toString()); //"hello     "

        strBuffer7.setLength(2);
        System.out.println(strBuffer7.toString()); //"he"
        System.out.println("---------------------");

        //--- Замена в строке
        //  replace() для замены подстроки между определенными позициями в StringBuffer на другую подстроку

        StringBuffer strBuffer8 = new StringBuffer("hello world!");
        strBuffer8.replace(6,11,"java");
        System.out.println(strBuffer8.toString()); //hello java!
        System.out.println("---------------------");

        //--- Обратный порядок в строке
        // reverse() меняет порядок в StringBuffer на обратный

        StringBuffer strBuffer9 = new StringBuffer("assembler");
        strBuffer9.reverse();
        System.out.println(strBuffer.toString()); //relbmessa

        String palindrome  = "A man, a plan, a canal. Panama";
        System.out.println(palindrome);
        String newPalindrome = palindrome.toLowerCase();
        StringBuffer palindromeBuffer = new StringBuffer();
        for (char s: newPalindrome.toCharArray()) {
            palindromeBuffer.append(s).append(" ");
        }

        System.out.println("---------------------");

        System.out.println(palindromeBuffer.reverse().toString());


    }

}


/*
Объекты String являются неизменяемыми, поэтому все операции, которые изменяют строки,
фактически приводят к созданию новой строки, что сказывается на производительности приложения.

Для решения этой проблемы, чтобы работа со строками проходила с меньшими издержками в Java были добавлены
классы StringBuffer и StringBuilder.

По сути они напоминает расширяемую строку, которую можно изменять без ущерба для производительности
 */

/*
Эти классы похожи, практически двойники, они имеют одинаковые конструкторы, одни и те же методы, которые одинаково используются.
Единственное их различие состоит в том, что класс StringBuffer синхронизированный и потокобезопасный.

То есть класс StringBuffer удобнее использовать в многопоточных приложениях,
где объект данного класса может меняться в различных потоках.

Если же речь о многопоточных приложениях не идет, то лучше использовать класс StringBuilder,
который не потокобезопасный, но при этом работает быстрее, чем StringBuffer в однопоточных приложениях.
 */


/* StringBuffer constructors // конструкторы
        StringBuffer()                          16 символов
        StringBuffer(int capacity)              к-во символов
        StringBuffer(String str)                строка + 16 доп символов
        StringBuffer(CharSequence chars)        набор символов + 16 доп символов
*/


/* StringBuilder: constructors // конструкторы
        StringBuilder()                         16 символов
        StringBuilder(int capacity)             к-во символов
        StringBuilder(String str)               строка + 16 доп символов
        StringBuilder(CharSequence chars)       набор символов + 16 доп символов
 */


