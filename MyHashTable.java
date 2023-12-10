//The MyHashTable class is created with m (the hash table size) linked lists of, initially, the length ‘0’ (zero). A word sequence is stored to one of the linked lists, selected by your choice of a hash function, hence the array of linked objects grows, utilising instance methods provided by the MyLinkedObject class.
//The MyHashTable class provides the following methods:
//• public MyHashTable(int m) — constructor, creates an array of m linked lists of length ‘0’ (zero).

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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

    // print the hash table by iterating through it and save it in file "output.txt", also each linked list let it be in one line
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
