import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DateSearch {
    private Map<String, Map<LocalDate, Integer>> searchMap;

    public DateSearch() {
        searchMap = new HashMap<>();
    }

    public void searchWord(String word) {
        LocalDate today = LocalDate.now();
        Map<LocalDate, Integer> wordMap = searchMap.get(word);
        if (wordMap == null) {
            wordMap = new HashMap<>();
            searchMap.put(word, wordMap);
        }
        Integer count = wordMap.get(today);
        if (count == null) {
            count = 0;
        }
        wordMap.put(today, count + 1);
    }

    public int getSearchCount(String word, LocalDate date) {
        Map<LocalDate, Integer> wordMap = searchMap.get(word);
        if (wordMap == null) {
            return 0;
        }
        Integer count = wordMap.get(date);
        if (count == null) {
            return 0;
        }
        return count;
    }

    public void setSearchCount(String word, LocalDate date, int count) {
        Map<LocalDate, Integer> wordMap = searchMap.get(word);
        if (wordMap == null) {
            wordMap = new HashMap<>();
            searchMap.put(word, wordMap);
        }
        wordMap.put(date, count);
    }

    public Map<String, Map<LocalDate, Integer>> getSearchMap() {
        return searchMap;
    }

    public void setSearchMap(Map<String, Map<LocalDate, Integer>> searchMap) {
        this.searchMap = searchMap;
    }
}
