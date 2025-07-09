package org.example.util;
import org.example.model.Card;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.nio.file.*;
import java.util.List;
public class JSONUtils {
    private static final ObjectMapper mapper=new ObjectMapper();
    public static List<Card> loadCardsFromFile(String filepath) throws IOException {
        Path path=Paths.get(filepath);
        if (!Files.exists(path)){
            throw new FileNotFoundException("Datei wurde nicht gefunden!") +filepath);
        } return mapper.readValue(path.toFile(), new TypeReference<List<Card>>(){});

        public static void saveCardsToFile(String filepath, List<Card> cards) throws IOException {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filepath), cards);
        }
    }
}
