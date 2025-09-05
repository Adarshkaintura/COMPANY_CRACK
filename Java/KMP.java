import java.util.ArrayList;
import java.util.List;

public class KMP {

    /**
     * Searches for occurrences of a pattern within a text using the KMP algorithm.
     *
     * @param text The main text string.
     * @param pattern The pattern string to search for.
     * @return A list of starting indices where the pattern is found in the text.
     */
    public static List<Integer> kmpSearch(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();

        if (m == 0) { // Empty pattern matches at every position
            for (int i = 0; i <= n; i++) {
                result.add(i);
            }
            return result;
        }
        if (n == 0 || m > n) { // Empty text or pattern longer than text
            return result;
        }

        int[] lps = computeLPSArray(pattern);

        int i = 0; // index for text
        int j = 0; // index for pattern

        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                result.add(i - j); // Pattern found at (i - j)
                j = lps[j - 1]; // Shift pattern using LPS array
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1]; // Shift pattern using LPS array
                } else {
                    i++; // Mismatch at first character of pattern, move to next text character
                }
            }
        }
        return result;
    }

    /**
     * Computes the Longest Proper Prefix which is also a Suffix (LPS) array for the given pattern.
     *
     * @param pattern The pattern string.
     * @return The LPS array.
     */
    private static int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int length = 0; // Length of the previous longest prefix suffix
        int i = 1;

        lps[0] = 0; // LPS for the first character is always 0

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lps[length - 1]; // Fallback to previous LPS value
                } else {
                    lps[i] = 0; // No proper prefix is also a suffix
                    i++;
                }
            }
        }
        return lps;
    }

    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";
        List<Integer> matches = kmpSearch(text, pattern);
        System.out.println("Pattern found at indices: " + matches); // Output: Pattern found at indices: [10]

        text = "AAAAAA";
        pattern = "AA";
        matches = kmpSearch(text, pattern);
        System.out.println("Pattern found at indices: " + matches); // Output: Pattern found at indices: [0, 1, 2, 3, 4]
    }
}
