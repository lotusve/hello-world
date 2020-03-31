package org.spacelab.javalibrary.reusing;

public class Hide {
    public static void main(String[] args) {
        Bart bart = new Bart();
        bart.doh(1);
        bart.doh('x');
        bart.doh(1.0f);
        bart.doh(new Milhouse());
    }
}

class Homer{
    char doh(char c){
        System.out.println("doh(char)");
        return 'd';
    }
    float doh(float f){
        System.out.println("doh(float)");
        return 1.0f;
    }
}

class Milhouse{

}

class Bart extends Homer{

    void doh(Milhouse milhouse){
        System.out.println("doh(Milhouse)");
    }

    @Override
    char doh(char c){
        System.out.println("Bart.doh(char)");
        return 'b';
    }
}
