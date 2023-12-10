/*
 * Author : Manu Kenchappa Junjanna
 * Email : mkenchappajunjanna1@sheffield.ac.uk
 * Created on Sun Dec 10 2023
 */

/**
 * This class represents a hash table data structure.
 * It stores objects of type MyLinkedObject using a hash function to determine
 * their position in the table.
 */
public class MyHashTable {
    private MyLinkedObject[] hashTable;
    private int hashTableSize;
    private MyHashFunction hashFunction;
    private int totalWordCount = 0, uniqueWordCount = 0;

    public MyHashTable(int m) {
        hashTable = new MyLinkedObject[m];
        hashTableSize = m;
        hashFunction = new FirstHashFunction(hashTableSize);
    }

    public void add(String word) {
        totalWordCount++;
        int hash = hashFunction.hash(word);
        if (hashTable[hash] == null) {
            hashTable[hash] = new MyLinkedObject(word);
            uniqueWordCount++;
        } else {
            uniqueWordCount += hashTable[hash].setWord(word);
        }
    }

    public void printHashTable() {
        // print the hash table by iterating through it and printing each linked list
        for (int i = 0; i < hashTable.length; i++) {
            System.out.print(i + ": ");
            MyLinkedObject current = hashTable[i];
            while (current != null) {
                System.out.print(current);
                current = current.getNext();
            }
            System.out.println();
        }
    }

    public int getTotalWordCount() {
        return totalWordCount;
    }

    public int getUniqueWordCount() {
        return uniqueWordCount;
    }
}
