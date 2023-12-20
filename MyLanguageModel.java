/*
 * Author : Manu Kenchappa Junjanna
 * Email : mkenchappajunjanna1@sheffield.ac.uk
 * Created on Sun Dec 3 2023
 */

import GUI.Frame;

public class MyLanguageModel {
    private static final String INPUT_FILE = "./news.txt";

    public static void main(String[] args) {
        Frame frame = new Frame();
        // StringBuilder wordsInStringBuilder = FileReaderUtil.readFile(INPUT_FILE, Constants.MAX_CHAR_LIMIT);
        // // create a bigram model
        // String[] data = wordsInStringBuilder.toString().split("\\s+|\\n");
        // int maxElements = Constants.MAX_CHAR_LIMIT;
        // data = Arrays.copyOfRange(data, 0, Math.min(data.length, maxElements));
        // MyHashTable uniGramHashTable = new MyHashTable(Constants.HASH_TABLE_SIZE);
        // for (String word : data) {
        //     uniGramHashTable.add(word);
        // }
        // NGramProbabilityCalculation nGramProbabilityCalculation = new NGramProbabilityCalculation(data,
        //         uniGramHashTable);
        // nGramProbabilityCalculation.get20MostFrequentWordsUsingBigram("");
    }

}