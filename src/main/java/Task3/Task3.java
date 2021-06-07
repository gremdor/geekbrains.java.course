package Task3;

import java.util.*;

/*
1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
Посчитать, сколько раз встречается каждое слово.
2. Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров.
В этот телефонный справочник с помощью метода add() можно добавлять записи, а с помощью метода get()
искать номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов
(в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.
Желательно не добавлять лишний функционал (дополнительные поля (имя, отчество, адрес),
взаимодействие с пользователем через консоль и т.д). Консоль использовать только для вывода
результатов проверки телефонного справочника.
*/
public class Task3 {
    public static void main(String[] args) {
        System.out.println("== Arrays ==");
        testArray();

        System.out.println("== PhoneBook ==");
        testPhoneBook();
    }

    public static void testArray() {
        ArrayList<String> ar = new ArrayList<String>(Arrays.asList(
                "spring", "Spring", "Fall", "table", "cat", "dog", "frog",
                "dog", "winter", "fly", "run", "swim", "summer",
                "frog", "frog", "frog", "frog", "car", "cat", "Fall"
        ));
        Collections.sort(ar);
        System.out.printf("Sorted array:\n  %s\n\n", ar);

        String value = "", prevValue = "";
        Integer cnt = 0;

        Iterator<String> iter = ar.iterator();
        HashMap<String, String> hm = new HashMap<>();

        while (iter.hasNext()) {
            value = iter.next();
            if (value != prevValue && cnt > 0) {
                cnt = 1;
            } else {
                cnt++;
            }
            hm.put(value, cnt.toString());
            prevValue = value;
        }

        System.out.println("Unique strings and they counts:");
        for (Map.Entry<String, String> o : hm.entrySet()) {
            System.out.printf("  %10s: %s\n", o.getKey(), o.getValue());
        }

    }


    public static void testPhoneBook() {
        PhoneBook pb = new PhoneBook();

        pb.add("Name1", "1233456");
        pb.add("Name2", "2222-454-55");
        pb.add("Name1", "33-44-555");
        pb.add("Name3", "(222)22 22 333");
        pb.add("Name4", "98709853");
        pb.add("Name5", "+7812123456");
        pb.add("Name5", "+79119876543");
        pb.add("Psy", "+3134733333-0");

        System.out.printf("Name1: %s\n", pb.get("Name1"));
        System.out.printf("Name2: %s\n", pb.get("Name2"));
        System.out.printf("Name3: %s\n", pb.get("Name3"));
        System.out.printf("Name4: %s\n", pb.get("Name4"));
        System.out.printf("Name5: %s\n", pb.get("Name5"));
        System.out.printf("Psy: %s\n", pb.get("Psy"));
        System.out.printf("Name6: %s\n", pb.get("Name6"));
    }
}
