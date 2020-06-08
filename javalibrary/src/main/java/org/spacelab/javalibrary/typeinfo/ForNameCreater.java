package org.spacelab.javalibrary.typeinfo;

import java.util.ArrayList;
import java.util.List;

public class ForNameCreater extends PetCreater {

    private static List<Class<? extends Pet>> types = new ArrayList<>();

    private static String[] typeNames = {
            "org.spacelab.javalibrary.typeinfo.Mutt",
            "org.spacelab.javalibrary.typeinfo.Pug",
            "org.spacelab.javalibrary.typeinfo.EgyptianMau",
            "org.spacelab.javalibrary.typeinfo.Manx",
            "org.spacelab.javalibrary.typeinfo.Cymric",
            "org.spacelab.javalibrary.typeinfo.Rat",
            "org.spacelab.javalibrary.typeinfo.Mouse",
            "org.spacelab.javalibrary.typeinfo.Hamster"
    };

    @SuppressWarnings("unchecked")
    private static void loader() {
        try {
            for (String name : typeNames) {
                types.add((Class<? extends Pet>) Class.forName(name));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static {
        loader();
    }

    @Override
    public List<Class<? extends Pet>> types() {
        return types;
    }

}
