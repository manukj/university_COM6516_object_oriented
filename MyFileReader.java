/*
 * Author : Manu Kenchappa Junjanna
 * Email : mkenchappajunjanna1@sheffield.ac.uk
 * Created on Sun Dec 10 2023
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyFileReader {

    public static String[] readFile(String filePath) {
        List<String> wordsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into words using whitespace as delimiter
                String[] wordsInLine = line.split("\\s+");
                // Add words to the list
                wordsList.addAll(Arrays.asList(wordsInLine));
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception - File not found, unable to read, etc.
        }

        // Convert list to array
        return wordsList.toArray(new String[0]);
    }
}
