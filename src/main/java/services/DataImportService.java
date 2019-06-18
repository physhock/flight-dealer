package services;

import businesslogic.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import storage.ItemRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataImportService {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void loadNewObjectsFromFile() {

        List<Item> items = new ArrayList<>();
        File file = new File("/items.json");
        try (InputStream fileStream = DataImportService.class.getResourceAsStream("/items.json")) {
            items = Arrays.asList(objectMapper.readValue(fileStream, Item[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        items.forEach(ItemRepository::addItem);

    }

}
