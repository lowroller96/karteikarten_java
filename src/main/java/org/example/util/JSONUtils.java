package org.example.util;
import org.example.model.Card;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class JSONUtils {

    private static final ObjectMapper mapper = new ObjectMapper();
    // Karteikarten über Card Klasse aufrufen und laden

    public static List<Card> loadCardsFromFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("Datei nicht gefunden: " + path);
            Files.createDirectories(file.getParentFile().toPath()); // Ordner anlegen falls nicht vorhanden
            Files.writeString(file.toPath(), "[]", StandardCharsets.UTF_8);
            return new ArrayList<>();
        }
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.asList(mapper.readValue(file, Card[].class));
    }
    // Karten in Datei speichern
    public static void saveCardstoFile(String filepath,List<Card>cards) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filepath),cards);
    }
}
