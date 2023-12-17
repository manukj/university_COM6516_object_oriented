package hash_table;
/*
 * Author : Manu Kenchappa Junjanna
 * Email : mkenchappajunjanna1@sheffield.ac.uk
 * Created on Sun Dec 3 2023
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import GUI.ReadFileUICallback;

public class MyFileReader {

    public static StringBuilder readFile(String filePath, ReadFileUICallback callback) {
        StringBuilder content = new StringBuilder();
        callback.onFileReading(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            callback.onFileReadingError(e.getMessage());
            return null;
        }
        return content;
    }
}
