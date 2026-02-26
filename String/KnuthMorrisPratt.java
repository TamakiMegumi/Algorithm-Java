package Algorithm.String;

/**
 * Knuth-Morris-Pratt (KMP) string matching algorithm implementation.
 * Efficiently finds pattern occurrences in text by avoiding redundant comparisons
 * through the use of a failure function (next array).
 * Time complexity: O(n + m) where n is the length of text and m is the length of pattern.
 */
public class KnuthMorrisPratt {

    private KnuthMorrisPratt() {
    }

    /**
     * Validates input parameters to ensure they meet the requirements for KMP algorithm.
     * Checks for null values and ensures pattern is not empty.
     *
     * @param text    the text to validate
     * @param pattern the pattern to validate
     * @throws IllegalArgumentException if text is null, pattern is null, or pattern is empty
     */
    private static void validateParams(String text, String pattern) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        if (pattern == null) {
            throw new IllegalArgumentException("Pattern cannot be null");
        }
        if (pattern.isEmpty()) {
            throw new IllegalArgumentException("Pattern cannot be empty");
        }
    }

    /**
     * Searches for the first occurrence of pattern in text using the standard KMP next array.
     *
     * @param text    the text to search in
     * @param pattern the pattern to search for
     * @return the index of the first occurrence, or -1 if not found
     * @throws IllegalArgumentException if text or pattern is null, or pattern is empty
     */
    public static int search(String text, String pattern) {
        validateParams(text, pattern);
        int[] next = buildNext(pattern);
        return searchMain(text, pattern, next);
    }

    /**
     * Searches for the first occurrence of pattern in text using the optimized nextval array.
     * The nextval array reduces unnecessary comparisons in certain cases compared to the standard next array.
     *
     * @param text    the text to search in
     * @param pattern the pattern to search for
     * @return the index of the first occurrence, or -1 if not found
     * @throws IllegalArgumentException if text or pattern is null, or pattern is empty
     */
    public static int searchWithNextVal(String text, String pattern) {
        validateParams(text, pattern);
        int[] nextval = buildNextVal(pattern);
        return searchMain(text, pattern, nextval);
    }

    /**
     * Performs the main KMP search algorithm using the provided failure function array.
     * Scans through the text and pattern, using the failure function to skip redundant comparisons.
     *
     * @param text    the text to search in
     * @param pattern the pattern to search for
     * @param next    the failure function array (either next or nextval)
     * @return the starting index of the first occurrence, or -1 if not found
     */
    private static int searchMain(String text, String pattern, int[] next) {
        // Quick check: if text is shorter than pattern, no match is possible
        if (text.length() < pattern.length()) {
            return -1;
        }

        int i = 0; // Pointer for text
        int j = 0; // Pointer for pattern

        // Scan through text and pattern simultaneously
        while (i < text.length() && j < pattern.length()) {
            if (j == -1 || text.charAt(i) == pattern.charAt(j)) {
                // Characters match: advance both pointers
                i++;
                j++;
            } else {
                // Mismatch: use failure function to skip ahead in pattern
                j = next[j];
            }
        }

        // Return the starting index if full pattern is matched, otherwise -1
        return j == pattern.length() ? i - j : -1;
    }

    /**
     * Builds the failure function (next array) for the given pattern.
     * next[i] indicates the length of the longest proper prefix of pattern[0..i]
     * that is also a suffix of pattern[0..i].
     * next[0] is set to -1 as a sentinel value for algorithm convenience.
     * <p>
     * Example: pattern = "ABABAC" → next = [-1, 0, 0, 1, 2, 3]
     * This means:
     * - At index 0: sentinel value -1
     * - At index 1: "AB" has no proper prefix-suffix match, next[1] = 0
     * - At index 2: "ABA" has prefix "A" matching suffix "A", next[2] = 0 (length of overlap)
     * - At index 3: "ABAB" has prefix "AB" matching suffix "AB", next[3] = 1
     * - At index 4: "ABABA" has prefix "ABA" matching suffix "ABA", next[4] = 2
     * - At index 5: "ABABAC" has prefix "ABAB" matching suffix "ABAB", next[5] = 3
     *
     * @param pattern the pattern string for which to build the failure function
     * @return an integer array representing the failure function
     */
    private static int[] buildNext(String pattern) {
        int[] next = new int[pattern.length()];
        next[0] = -1; // Sentinel value: indicates we need to advance both pointers
        int i = 0; // Pointer for pattern (current position being processed)
        int j = -1; // Pointer for pattern (tracking prefix length)

        // Build next array by finding longest prefix-suffix matches
        while (i < next.length - 1) {
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                // Characters match: extend the prefix-suffix match
                i++;
                j++;
                next[i] = j; // Record the length of the longest prefix-suffix
            } else {
                // Mismatch: follow the failure function chain to find shorter matches
                j = next[j];
            }
        }
        return next;
    }


    /**
     * Builds the optimized failure function (nextval array) for the given pattern.
     * The nextval array further optimizes the standard next array by avoiding redundant character comparisons
     * when the next character in the pattern is the same as the current mismatch position.
     * nextval[i] indicates where to jump when a mismatch occurs at position i.
     *
     * @param pattern the pattern string for which to build the optimized failure function
     * @return an integer array representing the optimized failure function
     */
    private static int[] buildNextVal(String pattern) {
        int[] nextval = new int[pattern.length()];
        nextval[0] = -1; // Sentinel value: indicates we need to advance both pointers
        int i = 0, j = -1;

        // Build nextval array by comparing characters and tracking prefix-suffix relationships
        while (i < nextval.length - 1) {
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                // Optimization: skip to nextval[j] if pattern[i] == pattern[j]
                if (pattern.charAt(i) != pattern.charAt(j)) {
                    nextval[i] = j;
                } else {
                    nextval[i] = nextval[j];
                }
            } else {
                // Mismatch: follow the failure function chain
                j = nextval[j];
            }
        }
        return nextval;
    }
}
