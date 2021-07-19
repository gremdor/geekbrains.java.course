package Task7;
/*
Создать класс, который может выполнять «тесты».
В качестве тестов выступают классы с наборами методов, снабженных аннотациями @Test. Класс, запускающий тесты,
должен иметь статический метод start(Class testClass), которому в качестве аргумента передается объект типа Class.
Из «класса-теста» вначале должен быть запущен метод с аннотацией @BeforeSuite, если он присутствует.
Далее запускаются методы с аннотациями @Test, а по завершении всех тестов – метод с аннотацией @AfterSuite.

К каждому тесту необходимо добавить приоритеты (int-числа от 1 до 10), в соответствии с которыми будет
выбираться порядок их выполнения. Если приоритет одинаковый, то порядок не имеет значения.
Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре.
Если это не так – необходимо бросить RuntimeException при запуске «тестирования».
* */

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestRunner {
    private static Map<Integer, List<String>> testMap;
    private static List<String> beforeTest;
    private static List<String> afterTest;

    public static void main(String[] args) {
        Class testExx = TestHolder2Before.class;
        try {
            start(testExx);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        testExx = TestHolder.class;
        try {
            start(testExx );
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    static int addTestMap(Integer key, String value) {
        List<String> list;
        if (testMap.containsKey(key)) {
            list = testMap.get(key);
        } else {
            list = new ArrayList<>();
        }
        list.add(value);
        testMap.put(key, list);
        return list.size();
    }

    public static void getTests(Class testClass) {
        testMap = new TreeMap<>();
        beforeTest = new ArrayList<>();
        afterTest = new ArrayList<>();

        for (Method o : testClass.getDeclaredMethods()) {
//            System.out.println(o);
            if (o.getAnnotation(BeforeSuite.class) != null) {
                beforeTest.add(o.getName());
            }
            if (o.getAnnotation(AfterSuite.class) != null) {
                afterTest.add(o.getName());
            }
            if (o.getAnnotation(Test.class) != null) {
                Test testAn = o.getAnnotation(Test.class);
                addTestMap(testAn.value(), o.getName());
            }
        }

//        printTests();
    }

    public static void start(Class testClass) {
        getTests(testClass);

        if (beforeTest.size() > 1 || afterTest.size() > 1)
            throw new RuntimeException(testClass.getName() + ": The only BeforeSuite or AfterSuite method is allowed");

        System.out.println("Run testSuite from " + testClass.getName());
        try {
            Constructor constructor = testClass.getConstructor();
            Object obj = testClass.newInstance();

            Method m = testClass.getDeclaredMethod(beforeTest.get(0));
            m.invoke(obj);

            for (Map.Entry<Integer, List<String>> o : testMap.entrySet()) {
                for (String methodName : o.getValue() ) {
                    m = testClass.getDeclaredMethod(methodName);
                    m.invoke(obj);
                }
            }

            m = testClass.getDeclaredMethod(afterTest.get(0));
            m.invoke(obj);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void printTests() {
        System.out.println("BeforeSuite: " + beforeTest.toString());

        System.out.println("Tests:");
        for (Map.Entry<Integer, List<String>> o : testMap.entrySet()) {
            System.out.printf("%d: %s\n", o.getKey(), o.getValue().toString());
        }

        System.out.println("AfterSuite: " + afterTest.toString());

    }
}
