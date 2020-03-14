package org.spacelab.javalibrary.initialize;

public class SimpleConstructor {

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            // new Rock();
            new Rock2(i);
        }

    }

}

class Rock {
    Rock() {
        System.out.print("Rock ");
    }
}

class Rock2 {
    Rock2(int i) {
        System.out.print("Rock " + i + " ");
    }
}

