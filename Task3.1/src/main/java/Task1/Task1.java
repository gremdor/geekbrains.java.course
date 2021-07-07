package Task1;

import java.util.ArrayList;
import java.util.Arrays;

/*
Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
Написать метод, который преобразует массив в ArrayList;

Задача:
    Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;

    Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя
    сложить и яблоки, и апельсины;

    Для хранения фруктов внутри коробки можно использовать ArrayList;

    Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их количество:
    вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);

    Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той,
    которую подадут в compare() в качестве параметра. true – если их массы равны, false в противоположном случае.
    Можно сравнивать коробки с яблоками и апельсинами;

    Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую. Помним про сортировку фруктов:
    нельзя яблоки высыпать в коробку с апельсинами. Соответственно, в текущей коробке фруктов не остается,
    а в другую перекидываются объекты, которые были в первой;

    Не забываем про метод добавления фрукта в коробку.
*/
public class Task1 {
    public static void main(String[] args) {
        // 1.
        Integer[] iar = {1, 3, 5, 6};
        System.out.println("Original array: " + Arrays.toString(iar));
        swap(iar, 1, 2);
        System.out.println("Swapped array: " + Arrays.toString(iar));

        //2.
        ArrayList<Object> objs = transform(iar);
        System.out.println(objs);

        //3.
        Apple apple = new Apple();
        Orange orange = new Orange();

        Box<Fruit> appleBox = new Box<>();
        Box<Fruit> appleBox2 = new Box<>();
        Box<Fruit> orangeBox = new Box<>();

        System.out.print("Put apple into the apple box: ");
        appleBox.add(apple);
        System.out.printf("appleBox weight is %f\n", appleBox.getWeight());

        System.out.print("Try to put orange into the apple box: ");
        appleBox.add(orange);

        System.out.print("Put more apples into the apple box: ");
        appleBox.add(apple, 2);
        System.out.printf("appleBox weight is %f\n", appleBox.getWeight());

        System.out.print("Put some oranges into the orange box: ");
        orangeBox.add(orange, 2);
        System.out.printf("orangeBox weight is %f\n", orangeBox.getWeight());

        System.out.print("Compare weight of apple and orange boxes: ");
        if (orangeBox.compare(appleBox)) {
            System.out.println("weight is equal");
        } else {
            System.out.println("weight is different");
        }


        System.out.print("Move apples from first box into another apple box: ");
        appleBox.moveTo(appleBox2);
        System.out.printf("appleBox weight is %f, appleBox2 - %f\n", appleBox.getWeight(), appleBox2.getWeight());

        System.out.print("Try to Move apples into orange box: ");
        appleBox2.moveTo(orangeBox);

        System.out.print("Compare weight of apple boxes: ");
        if (appleBox2.compare(appleBox)) {
            System.out.println("weight is different");
        } else {
            System.out.println("weight is unequal");
        }


        System.out.print("Put apple into the first apple box: ");
        appleBox.add(apple);
        System.out.printf("appleBox weight is %f\n", appleBox.getWeight());

        System.out.print("Move apples from first box into another apple box: ");
        appleBox.moveTo(appleBox2);
        System.out.printf("appleBox weight is %f, appleBox2 - %f\n", appleBox.getWeight(), appleBox2.getWeight());


    }

    //Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
    public static void swap(Object[] array, int fromPos, int toPos) {
        Object obj = new Object();
        if (array == null || array.length == 0 || fromPos < 0 || fromPos >= array.length || toPos < 0 || toPos >= array.length) {
            System.out.println("One or more parameters are invalid (array is empty or from/toPos is incorrect)");
            return;
        }

        obj = array[toPos];
        array[toPos] = array[fromPos];
        array[fromPos] = obj;


    }

    //Написать метод, который преобразует массив в ArrayList;
    public static ArrayList<Object> transform(Object[] array) {
        ArrayList<Object> objArr = new ArrayList<>();

        if (array == null || array.length == 0)
            return objArr;

        objArr.ensureCapacity(array.length);
        for (int i = 0; i < array.length; i++) {
            objArr.add((Object) array[i]);
        }
        return objArr;
    }


}
