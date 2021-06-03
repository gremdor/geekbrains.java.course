package Task2;

//Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4.
// При подаче массива другого размера необходимо бросить исключение MyArraySizeException.
//Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать.
// Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ
// или текст вместо числа), должно быть брошено исключение MyArrayDataException с детализацией,
// в какой именно ячейке лежат неверные данные.
//В методе main() вызвать полученный метод, обработать возможные исключения MyArraySizeException и
// MyArrayDataException и вывести результат расчета (сумму элементов, при условии что подали на вход корректный массив).
//Заметка: Для преобразования строки к числу используйте статический метод класса Integer:
//Integer.parseInt(сюда_подать_строку);
//Заметка: Если Java не сможет преобразовать входную строку (в строке число криво написано),
// то будет сгенерировано исключение NumberFormatException.

public class Task2 {
    private static final int ARRAY_SIZE = 4;

    public static void main(String[] args) {

        String[][] ar = {
                {"11", "12", "13"},
                {"21", "22", "23"},
                {"41", "42", "43"}
        };
        test(ar);

        ar = new String[][]{
                {"11", "12", "13", "14"},
                {"21", "22", "23", "24"},
                {"31", "32", "33", "34"},
                {"41", "42", "43", "44"}
        };
        test(ar);

        ar = new String[][]{
                {"11", "12", "13", "14"},
                {"21", "2x", "23", "24"},
                {"31", "32", "33", "34"},
                {"41", "42", "43", "44"}
        };
        test(ar);

        ar = new String[][]{
                {"11", "12", "13", "14"},
                {"21", "22", "23", "24"},
                {"31", "32", "33", "34"},
                {"41", "42", "43"}
        };
        test(ar);
    }

    public static void test(String[][] array) {
        print2DArray(array);
        try {
            int res = get2DArraySum(array);
            System.out.printf("get2DArraySum=%d\n", res);
        } catch (MyArraySizeException ex) {
            System.out.printf("Array dimension error: %s\n", ex.getMessage());
        } catch (MyArrayDataException ex) {
            System.out.printf("Array data error in element: %s\n", ex.getData());
        } catch (Exception ex) {
            System.out.printf("Other error: %s\n", ex.getMessage());
        }
    }

    public static int get2DArraySum(String[][] array) {

        if (array.length != ARRAY_SIZE) {
            throw new MyArraySizeException(String.format("Array has %d row(s) instead of %d", array.length, ARRAY_SIZE));
        }

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i].length != ARRAY_SIZE) {
                    throw new MyArraySizeException(String.format("Array row %d has %d column(s) instead of %d", i, array[i].length, ARRAY_SIZE));
                }

                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(array[i][j]);
                }
            }
        }
        return sum;
    }

    public static void print2DArray(String[][] arr) {
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d: ", i);
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

}
