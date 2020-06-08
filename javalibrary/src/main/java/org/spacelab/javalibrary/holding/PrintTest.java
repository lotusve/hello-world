package org.spacelab.javalibrary.holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class PrintTest {

    public static void main(String[] args) {

        Random rand = new Random();

        Collections.emptyList();

        Arrays.asList();

        // List
        List<String> list = new ArrayList<>();
        list.add("rat");
        list.add("cat");
        list.add("dog");
        System.out.println(list);

        LinkedList<String> list1;


        Collections.shuffle(list);

        // Map
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "rat");
        map.put(2, "cat");
        map.put(3, "dog");
        System.out.println(map);

        map.put(1, "haah");
        System.out.println(map);

        map.put(null, "null");
        System.out.println(map);

        map.put(4, null);
        System.out.println(map);


        // Set
        Set<String> set = new HashSet<>();
        TreeSet<String> set2 = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        LinkedHashSet<String> set3;

        // Queue
        PriorityQueue queue = new PriorityQueue(10, Collections.reverseOrder());


    }

}
