package polsl.pl;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Hello world!
 */

public class App {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        String dateFileName = formatter.format(calendar.getTime());
        final String fileURL = "https://plikplaski.mf.gov.pl/pliki/"  + dateFileName + ".7z";
        final String downloadLocation = "/home/osboxes/fileDownload/";
        final String fileName = "file.7z";
        JSONReader jsonReader = new JSONReader();

        FileHandler.downloadFile(fileURL, downloadLocation.concat(fileName));
        FileHandler.unzipFile(downloadLocation.concat(fileName), downloadLocation);
        jsonReader.read(dateFileName.concat(".json"));
        //DatabaseHandler databaseHandler = new DatabaseHandler();

    }
}
