package ru.tbank.edu.lesson3;

import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        CustomLinkedList list = new CustomLinkedList();


        list.add(1);
        list.add(2);
        list.add(3);


        System.out.println("Element at index 0: " + list.get(0));


        System.out.println("Size of the list: " + list.size());


        System.out.println("Contains 2: " + list.contains(2));
        System.out.println("Contains 4: " + list.contains(4));


        CustomLinkedList anotherList = new CustomLinkedList();
        anotherList.add(4);
        anotherList.add(5);

        list.addAll(anotherList);
        System.out.println("Size after adding another list: " + list.size());

        Stream<Integer> stream = Stream.of(6, 7, 8, 9);

        CustomLinkedList streamList = stream.reduce(new CustomLinkedList(), (customList, element) -> {
            customList.add(element);
            return customList;
        }, (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        });

        System.out.println("Size of stream converted list: " + streamList.size()); // Output: 4
    }
}
