package n_gram;

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

    public double getBiGramProbability(String wordK_1, String wordK) {
        int countK_1 = uniGramHashTable.getCount(wordK_1);
        int countK_1_K = biGramHashTable.getCount(wordK_1 + " " + wordK);
        double probability = (double) countK_1_K / countK_1;
        System.out.println("P(" + wordK + "|" + wordK_1 + ") = " + countK_1_K + "/" + countK_1 + " = " + probability);
        return probability;
    }

    public double getTriGramProbability(String wordK_2, String wordK_1, String wordK) {
        int countK_2_K_1 = biGramHashTable.getCount(wordK_2 + " " + wordK_1);
        int countK_2_K_1_K = triGramHashTable.getCount(wordK_2 + " " + wordK_1 + " " + wordK);
        double probability = (double) countK_2_K_1_K / countK_2_K_1;
        System.out.println("P(" + wordK + "|" + wordK_2 + " " + wordK_1 + ") = " + countK_2_K_1_K + "/" + countK_2_K_1
                + " = " + probability);
        return probability;
    }

}
