/*
 * Author : Manu Kenchappa Junjanna
 * Email : mkenchappajunjanna1@sheffield.ac.uk
 * Created on Sun Dec 3 2023
 */

import GUI.Frame;
import GUI.ReadFileUICallback;
import hash_table.MyFileReader;
import hash_table.MyHashTable;

public class MyLanguageModel {

    public static void main(String[] args) {
        Frame frame = new Frame();
        StringBuilder wordsInStringBuilder = MyFileReader.readFile("test.txt", (ReadFileUICallback) frame);
        String[] words = wordsInStringBuilder.toString().split("\\s+|\\n");
        MyHashTable hashTable = new MyHashTable(10);
        for (String word : words) {
            hashTable.add(word);
        }
        frame.renderHashTable(hashTable);
    }

}