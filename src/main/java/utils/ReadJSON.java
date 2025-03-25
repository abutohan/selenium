package utils;

import org.json.JSONArray;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;

import static utils.ReadProperties.loadProperty;

public class ReadJSON {

    public static Object[][] getTestDataFromJSON(String propertyKey) throws IOException {
        String JSONFilePath = loadProperty().getProperty(propertyKey);
        if (JSONFilePath == null || JSONFilePath.isBlank())
            throw new IllegalArgumentException(String.format("Property not found: %s", propertyKey));
        String json = Files.readString(Paths.get(JSONFilePath));
        JSONArray jsonArray = new JSONArray(json);

        return IntStream.range(0, jsonArray.length())
                .mapToObj(i -> new Object[]{jsonArray.getJSONObject(i)})
                .toArray(Object[][]::new);
    }
}

