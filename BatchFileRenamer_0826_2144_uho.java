// 代码生成时间: 2025-08-26 21:44:18
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class BatchFileRenamer {

    @Bean
    CommandLineRunner run() {
        return args -> {
            // 获取目录路径，这里假设作为参数传入
            String directoryPath = "/path/to/directory";
            // 获取文件名称前缀，这里假设作为参数传入
            String prefix = "new_prefix_";
            // 重命名文件
            renameFiles(directoryPath, prefix);
        };
    }

    public static void renameFiles(String directoryPath, String prefix) {
        try (Stream<Path> paths = Files.walk(Paths.get(directoryPath))) {
            List<File> files = paths
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());

            for (int i = 0; i < files.size(); i++) {
                File file = files.get(i);
                String fileName = prefix + (i + 1) + getFileExtension(file.getName());
                File newFile = new File(file.getParent(), fileName);
                if (file.renameTo(newFile)) {
                    System.out.println("Renamed: " + file.getName() + " to " + newFile.getName());
                } else {
                    System.err.println("Failed to rename: " + file.getName());
                }
            }
        } catch (Exception e) {
            System.err.println("Error while renaming files: " + e.getMessage());
        }
    }

    // Helper method to get file extension
    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > 0) {
            return fileName.substring(dotIndex);
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(BatchFileRenamer.class, args);
    }
}
