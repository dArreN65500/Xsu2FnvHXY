// 代码生成时间: 2025-07-31 13:57:33
 * Features:
 * 1. Error handling
 * 2. Comments and documentation
 * 3. Adherence to Java best practices
 * 4. Maintainability and extensibility
 */
package com.example.folderorganizer;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FolderOrganizer {
    private static final String ORGANIZED_FOLDER = "organized";
    private static final String TEMP_FOLDER = "temp";

    public void organizeFolder(String folderPath) {
        try {
            File folder = new File(folderPath);
            if (!folder.exists()) {
                throw new IllegalArgumentException("Folder does not exist: " + folderPath);
            }

            File organizedFolder = new File(folder, ORGANIZED_FOLDER);
            if (!organizedFolder.exists()) {
                organizedFolder.mkdir();
            }

            File tempFolder = new File(folder, TEMP_FOLDER);
            if (!tempFolder.exists()) {
                tempFolder.mkdir();
            }

            // Move all files to temp folder for sorting
            moveFilesToTemp(folder);

            // Sort files and move them to organized folder
            sortAndMoveFiles(tempFolder, organizedFolder);

        } catch (IOException e) {
            throw new RuntimeException("Failed to organize folder: " + folderPath, e);
        }
    }

    private void moveFilesToTemp(File folder) throws IOException {
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.isFile()) {
                Path sourcePath = file.toPath();
                Path tempPath = Paths.get(folder.getPath(), TEMP_FOLDER, file.getName());
                Files.move(sourcePath, tempPath, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    private void sortAndMoveFiles(File tempFolder, File organizedFolder) throws IOException {
        List<File> sortedFiles = Objects.requireNonNull(tempFolder.listFiles())
                .stream()
                .sorted(Comparator.comparing(File::getName))
                .collect(Collectors.toList());

        for (File file : sortedFiles) {
            Path sourcePath = file.toPath();
            Path organizedPath = Paths.get(tempFolder.getParent(), ORGANIZED_FOLDER, file.getName());
            Files.move(sourcePath, organizedPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
