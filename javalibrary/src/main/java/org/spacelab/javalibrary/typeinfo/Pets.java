package org.spacelab.javalibrary.typeinfo;

import java.util.ArrayList;

public class Pets {

    public static final PetCreater creater = new LiteralPetCreater();

    public static Pet randomPet() {
        return creater.randomPet();
    }

    public static Pet[] createArray(int size) {
        return creater.createArray(size);
    }

    public static ArrayList<Pet> arrayList(int size) {
        return creater.arrayList(size);
    }

}
