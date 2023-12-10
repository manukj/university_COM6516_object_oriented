/*
 * Author : Manu Kenchappa Junjanna
 * Email : mkenchappajunjanna1@sheffield.ac.uk
 * Created on Sun Dec 10 2023
 */

public interface MyHashFunction {
    /**
     * Calculates the hash value for the given word.
     *
     * @param word the word to be hashed
     * @return the hash value of the word
     */
    public int hash(String word);
}
