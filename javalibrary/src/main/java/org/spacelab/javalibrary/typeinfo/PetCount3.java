package org.spacelab.javalibrary.typeinfo;

import java.util.LinkedHashMap;

public class PetCount3 {

    static class PetCounter extends LinkedHashMap<Class<? extends Pet>, Integer> {
        public PetCounter() {
            super();
            Dog.class.isInstance(new Dog());
        }

    }
}
