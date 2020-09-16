package polsl.pl;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class App {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        String dateFileName = formatter.format(calendar.getTime());
        String fileURL = "https://plikplaski.mf.gov.pl/pliki/"  + dateFileName + ".7z";
        String downloadLocation = "/home/osboxes/fileDownload/";
        String fileName = "file.7z";

        if(args.length > 0){
            downloadLocation = args[0];
            System.out.println(downloadLocation);
        }
        if(args.length >= 2){
            fileName = args[1];
            System.out.println(fileName);
        }
        if(args.length >= 3){
            fileURL = args[2];
            System.out.println(fileURL);
        }

        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.clearDatabase();
        JSONReader jsonReader = new JSONReader(databaseHandler, dateFileName);

        FileHandler.downloadFile(fileURL, downloadLocation.concat(fileName));
        FileHandler.unzipFile(downloadLocation.concat(fileName));
        jsonReader.readAndRewrite(dateFileName.concat(".json"));
        databaseHandler.closeDatabaseConnection();
        FileHandler.deleteFile(dateFileName.concat(".json"));
        FileHandler.deleteFile(dateFileName.concat(".json.sha512sum"));
    }
}