/*
 * Author : Manu Kenchappa Junjanna
 * Email : mkenchappajunjanna1@sheffield.ac.uk
 * Created on Sun Dec 10 2023
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class represents a hash table data structure.
 * It stores objects of type MyLinkedObject using a hash function to determine their position in the table.
 */
public class MyHashTable {
    private MyLinkedObject[] hashTable;
    private int hashTableSize;
    private MyHashFunction hashFunction;

    public MyHashTable(int m) {
        hashTable = new MyLinkedObject[m];
        hashTableSize = m;
        hashFunction = new FirstHashFunction(hashTableSize);
    }

    public void add(String word) {
        int hash = hashFunction.hash(word);
        if (hashTable[hash] == null) {
            hashTable[hash] = new MyLinkedObject(word);
        } else {
            hashTable[hash].setWord(word);
        }
    }

    public void printHashTable() {
        try {
            FileWriter fileWriter = new FileWriter("output.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (int i = 0; i < hashTable.length; i++) {
                MyLinkedObject current = hashTable[i];
                while (current != null) {
                    printWriter.print(current.getWord() + " ");
                    current = current.getNext();
                }
                printWriter.println();
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
