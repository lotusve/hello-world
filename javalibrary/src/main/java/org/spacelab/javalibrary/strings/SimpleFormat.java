package org.spacelab.javalibrary.strings;

import java.util.Random;

public class SimpleFormat {

    public static void main(String[] args) {

        int x = 5;
        double y = 5.33;

        System.out.println("Row 1: [" + x + " " + y + "]");
        System.out.format("Row 2: [%d %f]\n", x, y);
        System.out.printf("Row 3: [%d %f]\n", x, y);

        String s = String.format("Row 4: [%d %f]", x, y);
        System.out.println(s);

        Class clazz1 = String.class;

        Class clazz2 = s.getClass();

        try {
            Class clazz3 = Class.forName("java.lang.String");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Random r = new Random(47);
        r.nextInt(5);

    }

}
