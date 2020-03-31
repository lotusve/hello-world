package org.spacelab.javalibrary.reusing;

public class SubClass extends BaseClass {

    // private String name;

    private int age;

    public SubClass(String name, int age) {
        super(name);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getName() {
        return super.getName() + " haha";
    }

    public static void main(String[] args) {
        SubClass subClass = new SubClass("Tom", 18);

//        subClass.name = "haha";
//        subClass.age = 18;
//        subClass.getName();
        System.out.println("subClass: " + subClass.getName());

        BaseClass sub = new SubClass("Jim", 20);
//        sub.name = "blabla";
//        sub.getName();
        System.out.println("baseClass: " + sub.getName());

    }

}
