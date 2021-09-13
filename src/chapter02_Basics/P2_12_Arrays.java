package chapter02_Basics;

public class P2_12_Arrays {
    public static void main(String[] args) {

        //--- Инициализация массива ---
        // тип_данных название_массива[];
        // либо
        // тип_данных[] название_массива;

        int nums1[];
        int[] nums2;

        //инициализация
        int numsInit[];
        numsInit = new int[4];
        // выделяется память на 4 int, где значение по умолчанию (0)

        int nums3[] = new int[4];    // массив из 4 чисел
        int[] nums4 = new int[5];   // массив из 5 чисел

        char[] chars = new char[3];

        for (int i = 0; i < chars.length; i++) {
            System.out.println((chars[i]) + ","); //'\u0000' 0 Null
        }

        // 'for' loop replaceable with enhanced 'for' (forEach)
        // forEach loop  называют enhanced for-loop
        for (char aChar : chars) {
            System.out.println(aChar + ","); //'\u0000' 0 Null
        }

        // равноценно объявление
        int[] nums5 = new int[] { 1, 2, 3, 5 };
        int[] nums6 = { 1, 2, 3, 5 };

        int[] numbers = new int[4];
        // устанавливаем значения элементов массива
        numbers[0] = 1;
        numbers[1] = 2;
        numbers[2] = 4;
        numbers[3] = 100;

        // получаем значение третьего элемента массива
        System.out.println(numbers[2]);    // 4

        //--- Длина массива ---
        int[] nums7 = {1, 2, 3, 4, 5};
        int length = nums7.length;   // 5

        int last = nums7[nums7.length-1];
        System.out.println("element at last index " + last);

        /// System.out.println(nums[0]);
        /// Variable 'nums' might not have been initialized

        ///System.out.println(nums7[5]);
        ///java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5

        //--- МНОГОМЕРНЫЕ МАССИВЫ ---
        int[] nums = new int[] { 0, 1, 2, 3, 4, 5 };

        int[][] nums2Dim = { { 0, 1, 2 }, { 3, 4, 5 } }; // перечисление строк
        int[][] nums2Dim1 = new int[2][3];

        // установим элемент первого столбца второй строки
        nums2Dim[1][0]=44;
        System.out.println(nums2Dim[1][0]);

        int[][][] nums3Dim = new int[2][3][4];

        //--- Зубчатый массив ---
        int[][] numsZ = new int[3][];
        numsZ[0] = new int[2];
        numsZ[1] = new int[3];
        numsZ[2] = new int[5];

        //--- foreach ---
        // Специальная версия цикла for предназначена для перебора элементов
        // в наборах элементов, например, в массивах и коллекция

        /*
        for (тип_данных название_переменной : контейнер){
            // действия
        }
         */

        int[] arrayForEach = new int[] { 1, 2, 3, 4, 5 };
        for (int i : arrayForEach){
            System.out.println("для каждого типа данных в контейнере " + i);
        }

        System.out.println("---------------------");
        // анаолог с for
        int[] arrayFor = new int[] { 5, 4, 3, 2, 1 };
        for (int i = 0; i < arrayFor.length; i++){
            System.out.println("Вывод " + arrayFor[i]+ " -элемента по индексу "  + i);
        }

        System.out.println("---------------------");

        //--- Перебор многомерных массивов ---
        int[][] numsTwoDim = new int[][]
                {
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}
                };
        for (int i = 0; i < numsTwoDim.length; i++){
            for(int j=0; j < numsTwoDim[i].length; j++){

                System.out.printf("%d ", numsTwoDim[i][j]);
            }
            System.out.println();
        }

        System.out.println("----------зубчастый массив-----------");

        numsZ[0] = new int[] {1, 2};
        numsZ[1] = new int[3];
        numsZ[2] = new int[5];

        int[] numsInnerForZ = {4,5,6};
        int[] numsInnerForZ2 = new int[] {7,8,9,10,11,12};

        numsZ[1] = numsInnerForZ;
        numsZ[2] = numsInnerForZ2;

        int[][] numbersZ = new int[3][];
        ///numbersZ[0]= {4,5,6}; // не возможно без new int[]
        numbersZ[0]=numbersZ[2] = new int[2];
        numbersZ[1]= new int[] {1,1};

        for (int i = 0; i < numsZ.length; i++) {
            for (int j = 0; j < numsZ[i].length; j++) {
                System.out.print(numsZ[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------");

        for (int[] i:numbersZ) {
            for (int j: i) {
                System.out.print(j + ", ");
            }
            System.out.println();
        }


    }
}
