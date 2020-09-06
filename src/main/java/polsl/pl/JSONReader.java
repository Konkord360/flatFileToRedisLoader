package polsl.pl;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JSONReader {
    DatabaseHandler databaseHandler = new DatabaseHandler();
    public JSONReader(DatabaseHandler databaseHandler){
        this.databaseHandler = databaseHandler;
    }

    public void readAndRewrite(String fileName) {
        BufferedReader br = null;

        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(fileName));

            while ((sCurrentLine = br.readLine()) != null) {
                if (sCurrentLine.contains("[")) {
                    sCurrentLine = br.readLine().replace("\"", "").replace(",", "").trim(); //read first number
                    while (!(sCurrentLine.contains("]"))) {
                        databaseHandler.insertIntoRedis(sCurrentLine, "true");
                        sCurrentLine = br.readLine().replace("\"", "").replace(",", "").trim();
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