package ru.grabovsky.lists;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        arrayListText();
        linkedListTest();
    }

    private static void arrayListText(){
        MyArrayList<Integer> list = new MyArrayList<>(2);
        list.add(10);
        list.add(12);
        list.add(15);
        System.out.println(list);
        list.remove(1);
        System.out.println(list);
        list.add(1, 45);
        System.out.println(list);
        System.out.println(list.size());
        final Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    private static void linkedListTest(){
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(15);
        list.add(10);
        list.add(25);
        System.out.println(list);
        list.remove(1);
        System.out.println(list);
        list.add(1, 45);
        System.out.println(list);
        System.out.println(list.size());
        final Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
