package polsl.pl;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JSONReader {
    DatabaseHandler databaseHandler = new DatabaseHandler();
    String fileDate;


    public JSONReader(DatabaseHandler databaseHandler, String fileDate) {
        this.databaseHandler = databaseHandler;
        this.fileDate = fileDate;
    }

    public void readAndRewrite(String fileName) {
        BufferedReader br = null;

        try {
            String sCurrentLine = "";
            StringBuilder headerRedis = new StringBuilder("");
            StringBuilder masksRedis = new StringBuilder("");
            br = new BufferedReader(new FileReader(fileName));
            while ((sCurrentLine = br.readLine()) != null) {
                if (sCurrentLine.contains("naglowek")) {
                    do {
                        headerRedis.append(sCurrentLine);
                        sCurrentLine = br.readLine().trim();
                        if(sCurrentLine.contains("}"))
                            sCurrentLine = sCurrentLine.replace(",","");

                    } while (!sCurrentLine.contains("["));
                    databaseHandler.insertIntoRedis(fileDate.trim().concat(":naglowek"), headerRedis.toString().trim());
                }
                if (sCurrentLine.contains("skrotyPodatnikowCzynnych")) {
                    sCurrentLine = br.readLine().replace("\"", "").replace(",", "").trim(); //read first number
                    while (!(sCurrentLine.contains("]"))) {
                        databaseHandler.insertIntoRedis(sCurrentLine, "true");
                        sCurrentLine = br.readLine().replace("\"", "").replace(",", "").trim();
                    }
                }
                if(sCurrentLine.contains("maski")){
                    do {
                        masksRedis.append(sCurrentLine);
                        sCurrentLine = br.readLine().trim();
                    } while (!sCurrentLine.contains("}"));
                    databaseHandler.insertIntoRedis(fileDate.trim().concat(":maski"), masksRedis.toString());
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