/*
 * Author : Manu Kenchappa Junjanna
 * Email : mkenchappajunjanna1@sheffield.ac.uk
 * Created on Sun Dec 3 2023
 */

import hash_table.MyFileReader;
import n_gram.NGram;

public class MyLanguageModel {
    private static final String INPUT_FILE = "./test.txt";

    public static void main(String[] args) {
        // Frame frame = new Frame();
        StringBuilder wordsInStringBuilder = MyFileReader.readFile(INPUT_FILE);
        // create a bigram model
        NGram biGram = new NGram(wordsInStringBuilder.toString().split(" "), 3);
        for (String nGramArray : biGram.getNGramArray()) {
            System.out.println(nGramArray);
        }

    }

}