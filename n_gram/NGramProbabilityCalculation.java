package n_gram;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import constants.Constants;
import hash_table.MyHashTable;

public class NGramProbabilityCalculation {
    private MyHashTable uniGramHashTable, biGramHashTable, triGramHashTable;

    public NGramProbabilityCalculation(String[] uniGramData, MyHashTable uniGramHashTable) {
        // generate bi-gram and tri-gram
        NGram biGramData = new NGram(uniGramData, 2);
        NGram triGramData = new NGram(uniGramData, 3);

        // create hash table for bi-gram and tri-gram
        this.uniGramHashTable = uniGramHashTable;
        biGramHashTable = new MyHashTable(Constants.HASH_TABLE_SIZE);
        triGramHashTable = new MyHashTable(Constants.HASH_TABLE_SIZE);

        // insert bi-gram and tri-gram into hash table
        for (String biGramWord : biGramData.getArray()) {
            biGramHashTable.add(biGramWord);
        }
        for (String triGramWord : triGramData.getArray()) {
            triGramHashTable.add(triGramWord);
        }
    }

    public int getTotalTriGramWordCount() {
        return biGramHashTable.getTotalWordCount();
    }

    public int getTotalBiGramWordCount() {
        return triGramHashTable.getTotalWordCount();
    }

    public int getBiGramUniqueWordCount() {
        return biGramHashTable.getUniqueWordCount();
    }

    public int getTriGramUniqueWordCount() {
        return triGramHashTable.getUniqueWordCount();
    }

    private double getBiGramProbability(String wordK_1, String wordK, int countK_1) {
        int countK_1_K = biGramHashTable.getCount(wordK_1 + " " + wordK);
        if (countK_1 == 0) {
            return 0;
        }
        return (double) countK_1_K / countK_1;
    }

    private double getTriGramProbability(String wordK_2, String wordK_1, String wordK) {
        int countK_2_K_1 = biGramHashTable.getCount(wordK_2 + " " + wordK_1);
        int countK_2_K_1_K = triGramHashTable.getCount(wordK_2 + " " + wordK_1 + " " + wordK);
        if (countK_2_K_1 == 0) {
            return 0;
        }
        return (double) countK_2_K_1_K / countK_2_K_1;

    }

    public String get20MostFrequentWordsUsingBigram(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Cannot predict next word, since input is empty");
        }
        String[] inputWords = input.split(" ");
        if (uniGramHashTable.getCount(inputWords[inputWords.length - 1]) == 0) {
            throw new IllegalArgumentException(
                    "Cannot predict next word, since \"" + input + "\"is not in the dataset");
        }
        for (int i = 0; i < 20; i++) {
            String[] words = input.split(" ");
            String word_k_1 = words[words.length - 1];
            int countK_1 = uniGramHashTable.getCount(word_k_1);
            Map<Double, String> probability = new TreeMap<>(Collections.reverseOrder());
            for (Map.Entry<String, Integer> entry : uniGramHashTable.getAllWordAndItsCount().entrySet()) {
                String word_k = entry.getKey();
                double probabilityValue = getBiGramProbability(word_k_1, word_k, countK_1);
                probability.put(probabilityValue, word_k);
            }
            String mostProbableWord = probability.values().iterator().next();
            String mostProbableWordProbability = probability.keySet().iterator().next().toString();
            System.out.println("mostProbableWord = " + mostProbableWord + " mostProbableWordProbability = "
                    + mostProbableWordProbability);
            input += " " + mostProbableWord;
        }
        return input;
    }

    public String get20MostFrequentWordsUsingTrigram(String input) {
        if (input.isEmpty() || input.split(" ").length < 2) {
            throw new IllegalArgumentException("Cannot predict next word, since input is empty");
        }
        String[] inputWords = input.split(" ");
        if (biGramHashTable
                .getCount(inputWords[inputWords.length - 2] + " " + inputWords[inputWords.length - 1]) == 0) {
            throw new IllegalArgumentException(
                    "Cannot predict next word, since \"" + input + "\" is not in the dataset");
        }
        for (int i = 0; i < 20; i++) {
            String[] words = input.split(" ");
            String word_k_2 = words[words.length - 2];
            String word_k_1 = words[words.length - 1];
            Map<Double, String> probability = new TreeMap<>(Collections.reverseOrder());
            for (Map.Entry<String, Integer> entry : uniGramHashTable.getAllWordAndItsCount().entrySet()) {
                String word_k = entry.getKey();
                double probabilityValue = getTriGramProbability(word_k_2, word_k_1, word_k);
                probability.put(probabilityValue, word_k);
            }
            String mostProbableWord = probability.values().iterator().next();
            String mostProbableWordProbability = probability.keySet().iterator().next().toString();
            System.out.println("mostProbableWord = " + mostProbableWord + " mostProbableWordProbability = "
                    + mostProbableWordProbability);
            input += " " + mostProbableWord;
        }
        return input;
    }

}
