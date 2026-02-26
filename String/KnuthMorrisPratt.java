package Algorithm.String;

import java.util.ArrayList;
import java.util.List;

/**
 * Knuth-Morris-Pratt (KMP) string matching algorithm implementation.
 * Efficiently finds pattern occurrences in text by avoiding redundant comparisons
 * through the use of a failure function (next array).
 * Time complexity: O(n + m) where n is the length of text and m is the length of pattern.
 */
public class KnuthMorrisPratt {

    /**
     * Searches for the first occurrence of the pattern in the text using KMP algorithm.
     * Returns the starting index of the first match, or -1 if no match is found.
     *
     * @param text    the text string to be searched
     * @param pattern the pattern string to search for
     * @return the index of the first occurrence of pattern in text, or -1 if not found
     * @throws IllegalArgumentException if pattern is empty
     */
    public static int search(String text, String pattern) {
        if (pattern.isEmpty()) {
            throw new IllegalArgumentException("Pattern cannot be empty");
        }
        if (text.length() < pattern.length()) {
            return -1;
        }
        int[] next = buildNext(pattern);
        int i = 0, j = 0;
        while (i < text.length() && j < pattern.length()) {
            if (j == -1 || text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        return j == pattern.length() ? i - j : -1;
    }

    /**
     * Builds the failure function (next array) for the given pattern.
     * next[i] indicates the length of the longest proper prefix of pattern[0..i]
     * that is also a suffix of pattern[0..i].
     * next[0] is set to -1 as a sentinel value for algorithm convenience.
     * <p>
     * Example: pattern = "ABABAC" → next = [-1, 0, 0, 1, 2, 3]
     *
     * @param pattern the pattern string for which to build the failure function
     * @return an integer array representing the failure function
     */
    private static int[] buildNext(String pattern) {
        int[] next = new int[pattern.length()];
        next[0] = -1;
        int i = 1, j = -1;
        while (i < next.length) {
            if (j == -1 || pattern.charAt(i - 1) == pattern.charAt(j)) {
                next[i] = j + 1;
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        return next;
    }

    /**
     * Searches for all occurrences of the pattern in the text using KMP algorithm.
     * Returns a list of starting indices where the pattern is found.
     * Returns an empty list if no match is found.
     *
     * @param text    the text string to be searched
     * @param pattern the pattern string to search for
     * @return a list of all starting indices where pattern occurs in text
     * @throws IllegalArgumentException if pattern is empty
     */
    public static List<Integer> searchAll(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        if (pattern.isEmpty()) {
            throw new IllegalArgumentException("Pattern cannot be empty");
        }
        if (text.length() < pattern.length()) {
            return result;
        }

        int[] next = buildNext(pattern);
        int i = 0, j = 0;
        while (i < text.length()) {
            if (j == -1 || text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
            // 匹配成功，记录位置并继续搜索
            if (j == pattern.length()) {
                result.add(i - pattern.length());
                j = next[j];
            }
        }
        return result;
    }
}
