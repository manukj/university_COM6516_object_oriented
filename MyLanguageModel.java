/*
 * Author : Manu Kenchappa Junjanna
 * Email : mkenchappajunjanna1@sheffield.ac.uk
 * Created on Sun Dec 10 2023
 */

public class MyLanguageModel {

    public static void main(String[] args) {
        // testMyLinkedObject();
        // testFirstHashFunction();
        testHashTable();
    }

    private static void testHashTable() {
        MyHashTable hashTable = new MyHashTable(10);
        // read data from file news.txt,
        // add each word to the hash table
        // and print the hash table
        String[] words = MyFileReader.readFile("news.txt");

        // Displaying the words (for demonstration purposes)
        System.out.println("Words in the file:");
        for (String word : words) {
            hashTable.add(word);
        }
        hashTable.printHashTable();
        System.out.println("Total word count" + hashTable.getTotalWordCount());
        System.out.println("Total Unique count" + hashTable.getUniqueWordCount());

        // hashTable.printHashTable();
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