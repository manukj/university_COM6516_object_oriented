/*
 * Author : Manu Kenchappa Junjanna
 * Email : mkenchappajunjanna1@sheffield.ac.uk
 * Created on Sun Dec 10 2023
 */

public class Assignment {

    public static void main(String[] args) {
        // testMyLinkedObject();
        testFirstHashFunction();
    }

    private static void testFirstHashFunction() {
        FirstHashFunction hashFunction = new FirstHashFunction(10);
        System.out.println(hashFunction.hash("hello"));
        System.out.println(hashFunction.hash("world"));
        System.out.println(hashFunction.hash("hello"));
        System.out.println(hashFunction.hash("world"));
        System.out.println(hashFunction.hash("hello"));
        System.out.println(hashFunction.hash("world"));
        System.out.println(hashFunction.hash("hello"));
        System.out.println(hashFunction.hash("world"));
        System.out.println(hashFunction.hash("hello"));
        System.out.println(hashFunction.hash("world"));
        System.out.println(hashFunction.hash("hello"));
        System.out.println(hashFunction.hash("world"));
        System.out.println(hashFunction.hash("hello"));
        System.out.println(hashFunction.hash("world"));
        System.out.println(hashFunction.hash("hello"));
        System.out.println(hashFunction.hash("world"));
    }

    private static void testMyLinkedObject() {
        MyLinkedObject list = new MyLinkedObject("aa");
        list.setWord("aaa");
        list.setWord("aab");
        list.setWord("hello");

        // print the list by iterating through it
        MyLinkedObject current = list;
        while (current != null) {
            System.out.println(current);
            current = current.getNext();
        }
    }
}