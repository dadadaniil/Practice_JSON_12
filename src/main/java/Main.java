import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        List<Result> results = new ArrayList<>();

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("src/main/resources/students.json"));

            JSONArray jsonArray = (JSONArray) jsonObject.get("results");

            for (Object obj : jsonArray) {
                JSONObject studentObject = (JSONObject) obj;

                for (Object key : studentObject.keySet()) {
                    String login = (String) key;
                    JSONArray testsArray = (JSONArray) studentObject.get(key);

                    for (Object testObj : testsArray) {
                        JSONObject testObject = (JSONObject) testObj;

                        for (Object testKey : testObject.keySet()) {
                            String test = (String) testKey;
                            JSONObject detailsObject = (JSONObject) testObject.get(testKey);

                            String date = (String) detailsObject.get("date");

                            Result result = new Result(test, login, date, (double) detailsObject.get("mark"));
                            results.add(result);
                        }
                    }
                }
            }

            results.forEach(System.out::println);

        } catch (Exception e) {
            throw new IllegalArgumentException("Wrong file format");
        }
    }
}
