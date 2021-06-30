package Task5;

import org.w3c.dom.css.Counter;

import javax.naming.SizeLimitExceededException;
import javax.swing.plaf.TableHeaderUI;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Task5 {
    private static int SIZE = 10000000;
    private static int HALF_SIZE = SIZE / 2;

    public static void main(String[] args) {
        firstMethod();
        try {
            secondMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            thirdMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void firstMethod() {

        float[] arr = new float[SIZE];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("One thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
        arr2file("method1.log", arr);
    }

    public static void secondMethod() throws InterruptedException {
        float[] arr = new float[SIZE];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        long startTime = System.currentTimeMillis();

        // Создаем два массива для левой и правой части исходного
        // Копируем в них значения из большого массива
        float[] arr_left = new float[HALF_SIZE];
        float[] arr_right = new float[HALF_SIZE];

        System.arraycopy(arr, 0, arr_left, 0, HALF_SIZE);
        System.arraycopy(arr, HALF_SIZE, arr_right, 0, HALF_SIZE);

        ArrayCalculation calc_left = new ArrayCalculation(arr_left, 0);
        Thread thread1 = new Thread(() -> {
            calc_left.calc();
        });

        ArrayCalculation calc_right = new ArrayCalculation(arr_right, HALF_SIZE);
        Thread thread2 = new Thread(() -> {
            calc_right.calc();
        });

        // Запускает два потока и параллельно просчитываем каждый малый массив
        // ...
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        // Склеиваем малые массивы обратно в один большой
        // ...
        System.arraycopy(calc_left.getArr(), 0, arr, 0, HALF_SIZE);
        System.arraycopy(calc_right.getArr(), 0, arr, HALF_SIZE, HALF_SIZE);

        System.out.println("Two thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
        arr2file("method2.log", arr);
    }

    public static void thirdMethod() throws InterruptedException {
        float[] arr = new float[SIZE];
        int zz = SIZE / 3;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        long startTime = System.currentTimeMillis();

        ArrayCalculation calc = new ArrayCalculation(arr);

        // Запускает два потока и параллельно просчитываем каждый малый массив
        // ...
        Thread thread1 = new Thread(() -> {
            calc.calc(0, zz);
        });
        Thread thread2 = new Thread(() -> {
            calc.calc(zz, 2 * zz);
        });
        Thread thread3 = new Thread(() -> {
            calc.calc(2 * zz, SIZE);
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Three thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
        arr2file("method3.log", arr);
    }

    public static void arr2file(String name, float[] arr) {
        try (FileWriter writer = new FileWriter(name, false)) {
            writer.write(Arrays.toString(arr));
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
