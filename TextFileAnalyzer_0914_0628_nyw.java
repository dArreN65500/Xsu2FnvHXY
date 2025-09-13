// 代码生成时间: 2025-09-14 06:28:06
public class TextFileAnalyzer {

    /*
    * Analyze the content of a text file and return a map of word counts.
    *
    * @param filePath The path to the text file.
    * @return A map where the key is the word and the value is the count of that word.
    * @throws IOException If an I/O error occurs reading from the file or a malformed or unmappable byte sequence is read.
    */
    public Map<String, Integer> analyzeFileContent(String filePath) throws IOException {
        Map<String, Integer> wordCountMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into words using whitespace as a delimiter.
                String[] words = line.split(\\