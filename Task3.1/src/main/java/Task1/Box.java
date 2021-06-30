package Task1;

import java.util.ArrayList;
import java.util.Iterator;

public class Box<T extends Fruit> {
    private final double EPS = 0.0001f;
    private ArrayList<T> items;

    private String type;


    public float getWeight() {
        float weight = 0f;
        for (int i = 0; i < items.size(); i++)
            weight += items.get(i).getWeight();

        return weight;
    }

    public Box() {
        this.items = new ArrayList<T>();
        this.type = null;
    }

    public boolean add(T item) {
        if (items.isEmpty()) {
            items.add(item);
            type = item.getType();
        } else {
            if (type == item.getType()) {
                items.add(item);
            } else {
                System.out.printf("The Box already contains %ss. You can't put %ss here.\n", this.type, item.getType());
                return false;
            }
        }
        return true;
    }

    public boolean add(T item, int count) {
        boolean res = true;
        for (int i = 0; i < count; i++) {
            res &= add(item);
        }
        return res;
    }

    public void moveTo(Box<T> dest) {
        Iterator<T> item = items.iterator();
        while (item.hasNext()) {
            if (dest.add(item.next())) {
                item.remove();
            } else {
                return;
            }
        }
        this.type = null;
    }


    public boolean compare(Box<?> other) {
        return Math.abs(getWeight() - other.getWeight()) < EPS;
    }
}
