package n_gram;

public class NGram {
    private String[] nGramArray;

    public NGram(String[] uniGram, int n) {
        // iterate through the array and create ngram
        if (uniGram.length < n) {
            System.out.println("The value of n is greater than the length of the array");
        }
        nGramArray = new String[uniGram.length - n + 1];
        for (int i = 0; i < uniGram.length - n + 1; i++) {
            String[] temp = new String[n];
            for (int j = 0; j < n; j++) {
                temp[j] = uniGram[i + j];
            }
            nGramArray[i] = String.join(" ", temp);
        }
    }

    public String[] getNGramArray() {
        return nGramArray;
    }

}
