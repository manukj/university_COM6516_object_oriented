package GUI;
/*
 * Author : Manu Kenchappa Junjanna
 * Email : mkenchappajunjanna1@sheffield.ac.uk
 * Created on Mon Dec 11 2023
 */

import javax.swing.JPanel;

import hash_table.MyHashTable;
import hash_table.MyLinkedObject;

public class HashTablePanel extends JPanel {

    public HashTablePanel(MyHashTable table) {
        for (int i = 0; i < table.hashTable.length; i++) {
            System.out.println("--------------------" + i + "--------------------");
            MyLinkedObject current = table.hashTable[i];
            while (current != null) {
                System.out.print(current);
                current = current.getNext();
            }
        }
    }

}
