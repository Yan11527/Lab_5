package com.yan.common.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.yan.common.element.StudyGroup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * File handling and serving class
 */
public class FileHandler {

    private static File file;
    private final Validator validator = new Validator();

    public FileHandler(String filePath) throws FileNotFoundException {
        file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("!!! File was not found !!!");
        }
        if (!file.canRead() || !file.canWrite()) {
            throw new SecurityException("!!! Cannot read or write file !!!");
        }
    }

    /**
     * Loading collection from json formatted file (parsing json to objects)
     * @return HashMap of elements from file
     */
    public HashMap<Integer, StudyGroup> loadCollection() throws IOException {
        Gson gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, (JsonDeserializer<ZonedDateTime>)
                (json, type, jsonDeserializationContext) -> deserializeDate(json)).create();
        HashMap<Integer, StudyGroup> fromFile;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        try {
            fromFile = gson.fromJson(sb.toString(), new TypeToken<HashMap<Integer, StudyGroup>>() { }.getType());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            System.out.println("Json syntax error or wrong fields. Loading empty collection...");
            return new HashMap<>();
        }
        if (fromFile == null) {
            System.out.println("Empty file = empty collection");
            return new HashMap<>();
        }
        HashMap<Integer, StudyGroup> map = new HashMap<>();
        for (Map.Entry<Integer, StudyGroup> entry: fromFile.entrySet()) {
            if (!validator.checkStudyGroup(entry.getValue())) {
                System.out.println("This element doesn't satisfy requirements and will not be loaded:\n" + entry);
                continue;
            }
            StudyGroup.setCount(StudyGroup.getCount() + 1);
            entry.getValue().setId(StudyGroup.getCount());
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    private ZonedDateTime deserializeDate(JsonElement json) {
        JsonObject jsonObj = json.getAsJsonObject();
        JsonObject dateTime = jsonObj.getAsJsonObject("dateTime");

        JsonObject date = dateTime.getAsJsonObject("date");
        int year = date.get("year").getAsInt();
        int month = date.get("month").getAsInt();
        int day = date.get("day").getAsInt();

        JsonObject time = dateTime.getAsJsonObject("time");
        int hour = time.get("hour").getAsInt();
        int minute = time.get("minute").getAsInt();
        int second = time.get("second").getAsInt();
        int nano = time.get("nano").getAsInt();

        JsonObject zone = jsonObj.getAsJsonObject("zone");
        String id = zone.get("id").getAsString();

        return ZonedDateTime.of(year, month, day, hour, minute, second, nano, ZoneId.of(id));
    }

    /**
     * Parsing collection objects to json format and wring to file
     * @param map collection to write to file
     */
    public static void writeFile(Map<Integer, StudyGroup> map) throws FileNotFoundException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (!file.exists()) {
            throw new FileNotFoundException("!!! File not found. Cannot save collection !!!");
        }
        if (!file.canWrite() || !file.canRead()) {
            throw new SecurityException("!!! Cannot write this file. Saving collection failed !!!");
        }
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(file));
            String toJson = gson.toJson(map);
            writer.write(toJson);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
