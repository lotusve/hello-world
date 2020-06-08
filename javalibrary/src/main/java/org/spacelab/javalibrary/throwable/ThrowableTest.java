package org.spacelab.javalibrary.throwable;

public class ThrowableTest {

    private static void f() throws Some1Exception {

        String s = "haha";

        throw new NullPointerException();

        // throw new Some1Exception();

        // throw new RuntimeException("msg");

    }

    public static void main(String[] args) {

        try {

            f();

        } catch (Some1Exception e) {

        }

    }

}
