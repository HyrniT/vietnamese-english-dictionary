import java.io.UnsupportedEncodingException;
import java.text.Normalizer;

class Helper {
    public static String UnicodeToASCII(String s) {
        String s1 = Normalizer.normalize(s, Normalizer.Form.NFKD);
        String regex = "[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+";
        String s2 = null;
        try {
            s2 = new String(s1.replaceAll(regex, "").getBytes("ascii"),
                    "ascii");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
        return s2;
    }

    public static int LevenshteinDistance(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] d = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            d[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            d[0][j] = j;
        }

        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= m; i++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    d[i][j] = d[i - 1][j - 1];
                } else {
                    d[i][j] = Math.min(d[i - 1][j] + 1, Math.min(d[i][j - 1] + 1, d[i - 1][j - 1] + 1));
                }
            }
        }

        return d[m][n];
    }
}