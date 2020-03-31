package org.spacelab.javalibrary.reusing;

public class Beetle extends Insect{
    private static int x2 = printInt("static Beetle.x2 init.");
    private int k = printInt("Beetle.k init");

    public Beetle() {
        System.out.println("k = " + k);
        System.out.println("j = " + j);
    }

    public static void main(String[] args) {
        System.out.println("Beetle constructor");
        Beetle beetle = new Beetle();
    }
}

class Insect{
    private static int x1 = printInt("static Insect.x1 init.");

    static int printInt(String s){
        System.out.println(s);
        return 47;
    }

    private int i = 9;
    protected int j;

    public Insect() {
        System.out.println("i = " + i + ", j = " + j);
        j = 39;
    }
}
