package com.solidbeans.call4help.file;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReadJsonFile {

    @SuppressWarnings("unchecked")
    public List<Location> GetCities(){
        List<Location> locationList = new ArrayList<>();

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/resources/cities.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray list = (JSONArray) obj;

            for (JSONObject object : (Iterable<JSONObject>) list) {

                String region = (String) object.get("region");
                String city = (String) object.get("city");

                locationList.add(new Location(region, city));
            }


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return locationList;
    }

}
