package org.spacelab.javalibrary.initialize;

public class Mugs {
    Mug mug1;
    Mug mug2;

    {
        mug1 = new Mug(1);
        mug2 = new Mug(2);
        System.out.println("Mug1 & Mug2 init");
    }

    Mugs() {
        System.out.println("Mugs()");
    }

    Mugs(int marker) {
        System.out.println("Mugs(" + marker + ")");
    }

    public static void main(String[] args) {
        System.out.println("in main");
        new Mugs();
        System.out.println("new Mugs completed");
        new Mugs(1);
        System.out.println("new Mugs(1) completed");
    }
}

class Mug {
    Mug(int marker) {
        System.out.println("Mug(" + marker + ")");
    }

    void f(int marker) {
        System.out.println("f(" + marker + ")");
    }
}
