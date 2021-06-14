package Task1;

import java.util.Random;

/*
* Практическое задание
Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса.
* Эти классы должны уметь бегать и прыгать (методы просто выводят информацию о действии в консоль).
Создайте два класса: беговая дорожка и стена, при прохождении через которые, участники должны выполнять
* соответствующие действия (бежать или прыгать), результат выполнения печатаем в консоль (успешно пробежал,
* не смог пробежать и т.д.).
Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти этот набор препятствий.

* У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения на бег и прыжки.
* Если участник не смог пройти одно из препятствий, то дальше по списку он препятствий не идет.
* */
public class Task1 {
    private static Random rnd = new Random();

    public static void main(String[] args) {
        Obstacle[] obstacles = new Obstacle[]{
                new Wall(rnd.nextInt(100)),
                new Wall(rnd.nextInt(100)),
                new Field(rnd.nextInt(800)),
                new Field(rnd.nextInt(800)),
                new Field(rnd.nextInt(800))
        };

        Subject[] subjects = new Subject[]{
                new Human("Fedor", 120, 500),
                new Human("Mary", 80, 200),
                new Cat("Irwin", 300, 100),
                new Cat("Champion", 180, 3000),
                new Cat("Kitty", 15, 50)
        };

        for (Subject s : subjects) {
            s.info();
        }
        for (Obstacle obs : obstacles) {
            System.out.println("=============");
            obs.info();
            for (Subject s : subjects) {
                if (obs instanceof Wall)
                    s.jump(obs);
                else
                    s.run(obs);
            }

        }
    }
}
