
public class FirstHashFunction implements MyHashFunction {

    @Override
    public int hash(String input,int hashTableSize) {
        // the first letter of a word sequence — e.g., its unicode value modulo ‘the
        // hash table size’, i.e., a remainder of the division of a unicode value by the
        // hash table size m
        int hash = input.charAt(0) % hashTableSize;
        return hash;
    }
}