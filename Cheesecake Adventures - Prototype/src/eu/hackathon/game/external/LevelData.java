package eu.hackathon.game.external;


import eu.hackathon.cheesecake.utils.external.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class LevelData implements Cloneable, Serializable {
    public static final String FOLDER = "data";
    public static final String FILE_EXTENSION = ".json";

    public static final JSONParser JSON_PARSER = new JSONParser();

    public String file, name;
    int x, y, width, height;

    int[] data;
    int[] entities;

    int key;

    public LevelData(String file) {
        this.file = file;

        load();
    }

    private void load() {
        File file = new File(FileUtils.getTotalPath() + LevelData.FOLDER + "/" + this.file + LevelData.FILE_EXTENSION);

        if (file.exists()) {
            try {
                JSONObject jsonObject = (JSONObject) JSON_PARSER.parse(new FileReader(file));

                this.name = (String) jsonObject.get("name");

                this.x = Integer.parseInt((String) jsonObject.get("x"));
                this.y = Integer.parseInt((String) jsonObject.get("y"));
                this.width = Integer.parseInt((String) jsonObject.get("width"));
                this.height = Integer.parseInt((String) jsonObject.get("height"));

                this.key = Integer.parseInt((String) jsonObject.get("key"));

                int index;

                JSONArray data = (JSONArray) jsonObject.get("data");
                this.data = new int[width * height];

                index = 0;
                for (Iterator iterator = data.iterator(); iterator.hasNext(); ) {
                    this.data[index] = Integer.parseInt(Long.toString((Long) iterator.next()));
                    index++;
                }
                JSONArray interactables = (JSONArray) jsonObject.get("entities");
                this.entities = new int[width * height];

                index = 0;
                for (Iterator iterator = interactables.iterator(); iterator.hasNext(); ) {
                    this.entities[index] = Integer.parseInt(Long.toString((Long) iterator.next()));
                    index++;
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public LevelData clone() {
        try {
            return (LevelData) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
