package hash_table;
/*
 * Author : Manu Kenchappa Junjanna
 * Email : mkenchappajunjanna1@sheffield.ac.uk
 * Created on Sun Dec 3 2023
 */

/**
 * This class represents a hash table data structure.
 * It stores objects of type MyLinkedObject using a hash function to determine
 * their position in the table.
 */
public class MyHashTable {
    public MyLinkedObject[] linkedList;
    private int hashTableSize;
    private MyHashFunction hashFunction;
    private int totalWordCount = 0, uniqueWordCount = 0;

    public MyHashTable(int m) {
        linkedList = new MyLinkedObject[m];
        hashTableSize = m;
        hashFunction = new FirstHashFunction(hashTableSize);
    }

    public void add(String word) {
        if (word == null || word.isEmpty())
            return;
        totalWordCount++;
        int hash = hashFunction.hash(word);
        if (linkedList[hash] == null) {
            linkedList[hash] = new MyLinkedObject(word);
            uniqueWordCount++;
        } else {
            uniqueWordCount += linkedList[hash].setWord(word);
        }
        System.out.println("Word: " + word + " Hash: " + hash);
    }

    public void printHashTable() {
        // print the hash table by iterating through it and printing each linked list
        for (int i = 0; i < linkedList.length; i++) {
            System.out.println("--------------------" + i + "--------------------");
            MyLinkedObject current = linkedList[i];
            while (current != null) {
                System.out.print(current);
                current = current.getNext();
            }
            System.out.println("--------------------------------------------------");
        }
    }

    public int getTotalWordCount() {
        return totalWordCount;
    }

    public int getUniqueWordCount() {
        return uniqueWordCount;
    }

    public int getCount(String word) {
        int hash = hashFunction.hash(word);
        MyLinkedObject current = linkedList[hash];
        while (current != null) {
            if (current.getWord().equals(word)) {
                return current.getCount();
            }
            current = current.getNext();
        }
        return 0;
    }
}
