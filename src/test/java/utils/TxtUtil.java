package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TxtUtil {

    public static void writeToFile(String filePath, String content) {
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(content + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readLastPriceFromFile(String filePath) {
        String lastLine = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.trim().isEmpty()) {
                    lastLine = currentLine;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Örnek satır: "Ürün: Gömlek - Fiyat: 899,00 TL"
        if (lastLine.contains("Fiyat:")) {
            return lastLine.split("Fiyat:")[1].trim();
        }

        return null;
    }
}
