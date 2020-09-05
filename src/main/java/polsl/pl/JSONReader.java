package polsl.pl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

public class JSONReader {
    DatabaseHandler databaseHandler = new DatabaseHandler();

    public void read(String fileName) {
        BufferedReader br = null;

        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(fileName));

            while ((sCurrentLine = br.readLine()) != null) {
                if (sCurrentLine.contains("[")) {
                    sCurrentLine = br.readLine().replace("\"", "").replace(",", ""); //read first number
                    while (!(sCurrentLine.contains("]"))) {
                        databaseHandler.insertIntoRedis(sCurrentLine, null);
                        sCurrentLine = br.readLine().replace("\"", "").replace(",", "");
                    }
                    break;
                }
            }
            databaseHandler.closeDatabaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}