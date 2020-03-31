package org.spacelab.javalibrary.reusing;

public class Chess extends BoardGame {
    Chess() {
        super(11);
        System.out.println("Chess constructor");
    }

    public static void main(String[] args) {
        Chess chess = new Chess();
    }
}
