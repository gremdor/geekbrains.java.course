package Task5;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class ArrayCalculation {
    private float[] arr;
    private int delta;

    public ArrayCalculation(float[] arr, int delta) {
        this.arr = arr;
        this.delta = delta;
    }

    public ArrayCalculation(float[] arr) {
        this.arr = arr;
    }

    public float[] getArr() {
        return arr;
    }

    public void calc() {
//        System.out.println(Thread.currentThread().getId() + ' ' +Thread.currentThread().getName());

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + (i + delta) / 5) *
                    Math.cos(0.2f + (i + delta) / 5) * Math.cos(0.4f + (i + delta) / 2));
        }
    }

    public void calc(int start, int end) {
//        System.out.println(Thread.currentThread().getId() + ' ' +Thread.currentThread().getName());

        for (int i = max(start,0); i < min(end, arr.length); i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}
