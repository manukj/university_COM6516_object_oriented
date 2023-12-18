/*
 * Author : Manu Kenchappa Junjanna
 * Email : mkenchappajunjanna1@sheffield.ac.uk
 * Created on Sun Dec 3 2023
 */

import java.util.Arrays;

import constants.Constants;
import hash_table.MyFileReader;
import hash_table.MyHashTable;
import n_gram.NGramProbabilityCalculation;

public class MyLanguageModel {
    private static final String INPUT_FILE = "./news.txt";

    public static void main(String[] args) {
        // Frame frame = new Frame();
        StringBuilder wordsInStringBuilder = MyFileReader.readFile(INPUT_FILE);
        // create a bigram model
        String[] data = wordsInStringBuilder.toString().replaceAll("\\n", " ").split(" ");
        int maxElements = Constants.MAX_WORD_LIMIT;
        data = Arrays.copyOfRange(data, 0, Math.min(data.length, maxElements));
        MyHashTable uniGramHashTable = new MyHashTable(Constants.HASH_TABLE_SIZE);
        for (String word : data) {
            uniGramHashTable.add(word);
        }
        NGramProbabilityCalculation nGramProbabilityCalculation = new NGramProbabilityCalculation(data,
                uniGramHashTable);
        nGramProbabilityCalculation.getTotalBiGramWordCount();
        nGramProbabilityCalculation.getTotalTriGramWordCount();
        nGramProbabilityCalculation.getBiGramProbability("the", "company");
        nGramProbabilityCalculation.getTriGramProbability("the", "company", "said");

    }

}