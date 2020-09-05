package polsl.pl;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileHandler {

    public static boolean downloadFile(String URL, String downloadPath) {
        try {
            InputStream inputStream = new URL(URL).openStream();
            Files.copy(inputStream, Paths.get(downloadPath), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static boolean unzipFile(String zipFilePath, String destDir) {
        try {
            SevenZFile sevenZFile = new SevenZFile(new File(zipFilePath));
            SevenZArchiveEntry entry = sevenZFile.getNextEntry();
            while (entry != null) {
                System.out.println(entry.getName());
                FileOutputStream out = new FileOutputStream(entry.getName());
                byte[] content = new byte[(int) entry.getSize()];
                sevenZFile.read(content, 0, content.length);
                out.write(content);
                out.close();
                entry = sevenZFile.getNextEntry();
            }
            sevenZFile.close();
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
