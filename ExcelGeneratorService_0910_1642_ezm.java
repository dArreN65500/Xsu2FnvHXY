// 代码生成时间: 2025-09-10 16:42:17
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExcelGeneratorService {

    /**
     * Generate an Excel file based on the provided data map.
     * 
     * @param dataMap A map containing the data to be written into the Excel file.
     * @return The generated Excel file as a byte array.
     * @throws IOException If there is an error during file generation.
     */
    public byte[] generateExcel(Map<String, Object[]> dataMap) throws IOException {

        try (Workbook workbook = new XSSFWorkbook()) {
            // Create a new sheet for each entry in the data map
            for (Map.Entry<String, Object[]> entry : dataMap.entrySet()) {
                String sheetName = entry.getKey();
                Object[] rowData = entry.getValue();

                // Create a new sheet
                workbook.createSheet(sheetName);

                // Write data to the sheet
                int rowNum = 0;
                for (Object cellData : rowData) {
                    workbook.getSheet(sheetName).createRow(rowNum++).createCell(0).setCellValue(cellData.toString());
                }
            }
            return workbook.getBytes();
        } catch (IOException e) {
            throw new IOException("Error generating Excel file", e);
        }
    }

    /**
     * Upload and process an Excel file.
     * 
     * @param file The Excel file to be processed.
     * @return A map containing the processed data.
     * @throws IOException If there is an error reading the file.
     */
    public Map<String, Object[]> processExcel(MultipartFile file) throws IOException {
        Map<String, Object[]> dataMap = new HashMap<>();
        try {
            // Process the Excel file and populate the dataMap
            // This is a placeholder for the actual file processing logic
            // which would involve reading the file and populating the dataMap accordingly.
            // For demonstration purposes, we'll just return an empty map.
            return dataMap;
        } catch (Exception e) {
            throw new IOException("Error processing Excel file", e);
        }
    }
}
