package GUI;

/*
 * Author : Manu Kenchappa Junjanna
 * Email : mkenchappajunjanna1@sheffield.ac.uk
 * Created on Sun Dec 10 2023
 */

public interface ReadFileUICallback {
    void onFileReading(String filePath);
    void onFileReadingComplete(StringBuilder wordsBuilder, String filePath);
    void onFileReadingError(String errorMessage);
}