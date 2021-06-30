package test2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class test2 {
    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<>(Arrays.asList("A","B","C","A"));
        System.out.println(a.toString());
        a.remove("A");
        System.out.println(a.toString());
    }
}
