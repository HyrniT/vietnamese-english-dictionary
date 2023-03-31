import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.time.format.DateTimeParseException;

public class WordDateSearch {
    private Map<LocalDate, Map<String, Integer>> wordDateSearch;
    private static WordDateSearch instance = null;

    public static WordDateSearch getInstance(String fileName) {
        if (instance == null) {
            synchronized (WordDateSearch.class) {
                if (instance == null) {
                    instance = new WordDateSearch(fileName);
                }
            }
        }
        return instance;
    }

    public WordDateSearch(String fileName) {
        try {
            wordDateSearch = loadFromFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WordDateSearch getInstance() {
        if (instance == null) {
            synchronized (WordDateSearch.class) {
                if (instance == null) {
                    instance = new WordDateSearch();
                }
            }
        }
        return instance;
    }

    public WordDateSearch() {
        wordDateSearch = new HashMap<>();
    }

    public void addWordDateSearch(String word) {
        LocalDate today = LocalDate.now();
        Map<String, Integer> wordMap = wordDateSearch.get(today);
        if (wordMap == null) {
            wordMap = new HashMap<>();
            wordDateSearch.put(today, wordMap);
        }
        Integer count = wordMap.get(word);
        if (count == null) {
            count = 0;
        }
        wordMap.put(word, count + 1);
    }

    public Map<LocalDate, Map<String, Integer>> getWordDateSearch() {
        return this.wordDateSearch;
    }

    public void setWordDateSearch(Map<LocalDate, Map<String, Integer>> wordDateSearch) {
        this.wordDateSearch = wordDateSearch;
    }

    public Map<LocalDate, Map<String, Integer>> getWordFrequencyMap() {
        Map<LocalDate, Map<String, Integer>> wordFrequencyMap = new HashMap<>();
        for (Map.Entry<LocalDate, Map<String, Integer>> dateEntry : wordDateSearch.entrySet()) {
            LocalDate date = dateEntry.getKey();
            Map<String, Integer> frequencyMap = new HashMap<>();
            for (Map.Entry<String, Integer> wordEntry : dateEntry.getValue().entrySet()) {
                frequencyMap.put(wordEntry.getKey(), wordEntry.getValue());
            }
            wordFrequencyMap.put(date, frequencyMap);
        }
        return wordFrequencyMap;
    }
    
    public Map<LocalDate, Map<String, Integer>> filterRangeDate(String date1, String date2) throws DateTimeParseException {
        LocalDate startDate = LocalDate.parse(date1);
        LocalDate endDate = LocalDate.parse(date2);
    
        Map<LocalDate, Map<String, Integer>> result = new HashMap<>();
        for (Map.Entry<LocalDate, Map<String, Integer>> entry : wordDateSearch.entrySet()) {
            LocalDate date = entry.getKey();
            if (date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0) {
                result.put(date, entry.getValue());
            }
        }
        return result;
    }

    // public void saveToFile(String fileName) throws IOException {
    //     try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
    //         oos.writeObject(wordDateSearch);
    //     }
    // }

    public void saveToFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<LocalDate, Map<String, Integer>> entry : wordDateSearch.entrySet()) {
                LocalDate date = entry.getKey();
                Map<String, Integer> wordMap = entry.getValue();
                writer.write(date.toString());
                writer.newLine();
                for (Map.Entry<String, Integer> wordEntry : wordMap.entrySet()) {
                    String word = wordEntry.getKey();
                    int count = wordEntry.getValue();
                    writer.write(word + ":" + count);
                    writer.newLine();
                }
            }
        }
    }

    public Map<LocalDate, Map<String, Integer>> loadFromFile(String fileName) throws IOException {
        Map<LocalDate, Map<String, Integer>> wordDateSearch = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LocalDate date = LocalDate.parse(line);
                Map<String, Integer> wordMap = new HashMap<>();
                while ((line = reader.readLine()) != null && !line.isEmpty()) {
                    String[] parts = line.split(":");
                    String word = parts[0];
                    int count = Integer.parseInt(parts[1]);
                    wordMap.put(word, count);
                }
                wordDateSearch.put(date, wordMap);
            }
        }
        return wordDateSearch;
    }
    
    
}
