package utils;

import java.io.FileWriter;
import java.io.IOException;

public class TxtUtil {

    public static void writeToFile(String filePath, String content) {
        try (FileWriter fw = new FileWriter(filePath, true)) { // append true
            fw.write(content + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
