package be.jonasclaes.pingy.filestore;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileStore {
    private String filename;
    private JSONArray jsonArray;
    private static FileStore fileStore;

    public FileStore(String filename) {
        this.filename = filename;

        JSONParser jsonParser = new JSONParser();

        try {
            FileReader fileReader = new FileReader(this.filename);

            Object obj = jsonParser.parse(fileReader);

            this.jsonArray = (JSONArray) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } finally {
            if (this.jsonArray == null) {
                this.jsonArray = new JSONArray();
            }
        }

        this.write();
    }

    public void write() {
        try {
            FileWriter fileWriter = new FileWriter(this.filename);

            fileWriter.write(this.jsonArray.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void addRecord(JSONObject object) {
        if (this.jsonArray == null) {
            this.jsonArray = new JSONArray();
        }

        this.jsonArray.add(object);

        this.write();
    }

    public static FileStore getInstance() {
        if (FileStore.fileStore == null) {
            FileStore.fileStore = new FileStore("connection_lost.json");
        }

        return FileStore.fileStore;
    }

    public static FileStore getInstance(String filename) {
        if (FileStore.fileStore == null) {
            FileStore.fileStore = new FileStore(filename);
        }

        return FileStore.fileStore;
    }
}
