package project.fileOrganizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class FileOrganizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileOrganizerApplication.class, args);


        String sourceDir = "C:\\Users\\sseba\\Downloads"; // Change this to your source directory
        String destDir = "D:\\OrganizedFiles"; // Change this to your source directory

        // Create the destination directory if it doesn't exist
        new File(destDir).mkdirs();

        // Get the list of files in the source directory
        File[] files = new File(sourceDir).listFiles();
        // Loop through the files
        for (File file : files) {
            // Skip directories
            if (file.isDirectory()) {
                continue;
            }

            // Get the file extension
            String extension = getFileExtension(file);

            // Create a new directory for the file type
            String typeDir = destDir + "/" + extension;
            new File(typeDir).mkdirs();

            // Move the file to the new directory
            Path sourcePath = Paths.get(file.getAbsolutePath());
            Path destPath = Paths.get(typeDir + "/" + file.getName());
            try {
                Files.move(sourcePath, destPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

}
