// 代码生成时间: 2025-08-19 11:48:05
package com.example.documentconverter;

import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class DocumentConverter {
    // Converts a document from one format to another
    public ResponseEntity<ByteArrayResource> convertDocument(MultipartFile file) {
        try {
            // Validate file type and size
            if (file.isEmpty() || !isValidFileType(file.getContentType())) {
                return ResponseEntity.badRequest().build();
            }

            // Temporary storage for the file
            File tempFile = createTempFile(file.getContentType());

            // Save the uploaded file to a temporary location
            try (InputStream fileContent = file.getInputStream();
                 OutputStream fileOut = new FileOutputStream(tempFile)) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileContent.read(buffer)) != -1) {
                    fileOut.write(buffer, 0, bytesRead);
                }
            }

            // Convert the document
            // For simplicity, this example only demonstrates conversion to a GZIP compressed file
            File convertedFile = convertToGzip(tempFile);

            // Read the converted file into a byte array
            byte[] convertedFileContent = Files.readAllBytes(convertedFile.toPath());

            // Create a response entity with the converted file content
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, 
                         "attachment; filename=converted_" + tempFile.getName())
                    .contentLength(convertedFileContent.length)
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new ByteArrayResource(convertedFileContent));

        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Checks if the file type is valid for conversion
    private boolean isValidFileType(String contentType) {
        // Add supported file types here
        return contentType != null && (contentType.equals("application/pdf") ||
                                      contentType.equals("application/msword") ||
                                      contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document"));
    }

    // Creates a temporary file with the appropriate extension based on the content type
    private File createTempFile(String contentType) throws IOException {
        String extension = contentType.equals("application/pdf") ? ".pdf" : ".docx";
        return File.createTempFile("document", extension);
    }

    // Converts a file to a GZIP compressed file
    private File convertToGzip(File file) throws IOException {
        File gzippedFile = new File(file.getAbsolutePath() + ".gz");
        try (InputStream is = new FileInputStream(file);
             OutputStream os = new GzipCompressorOutputStream(new FileOutputStream(gzippedFile))) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            }
        }
        return gzippedFile;
    }
}
