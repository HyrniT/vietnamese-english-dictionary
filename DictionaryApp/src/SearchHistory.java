// import java.time.LocalDate;
// import java.util.HashMap;
// import java.util.Map;

// public class SearchHistory {
//     private Map<String, Integer> searchMap;
//     private LocalDate fromDate;
//     private LocalDate toDate;

//     public SearchHistory(LocalDate fromDate, LocalDate toDate) {
//         this.fromDate = fromDate;
//         this.toDate = toDate;
//         this.searchMap = new HashMap<>();
//     }

//     public void addSearch(String word) {
//         if (searchMap.containsKey(word)) {
//             int count = searchMap.get(word);
//             searchMap.put(word, count + 1);
//         } else {
//             searchMap.put(word, 1);
//         }
//     }

//     public Map<String, Integer> getSearchMap() {
//         return searchMap;
//     }

//     public LocalDate getFromDate() {
//         return fromDate;
//     }

//     public LocalDate getToDate() {
//         return toDate;
//     }
// }

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class SearchHistory {
    LocalDate fromDate;
    LocalDate toDate;
    private final Map<String, Integer> searchMap;

    public SearchHistory(LocalDate fromDate, LocalDate toDate) {
        this.searchMap = new HashMap<>();
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public void addSearch(String search) {
        int count = searchMap.getOrDefault(search, 0);
        searchMap.put(search, count + 1);
    }

    public Map<String, Integer> getSearchMap() {
        return searchMap;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}