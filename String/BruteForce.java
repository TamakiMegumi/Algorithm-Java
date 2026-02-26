package Algorithm.String;

/**
 * Brute Force string matching algorithm implementation.
 * Compares pattern with every possible position in text sequentially.
 * When a mismatch occurs, backtracks and tries the next position.
 * Time complexity: O(n * m) where n is the length of text and m is the length of pattern.
 * Space complexity: O(1)
 */
public class BruteForce {

    private BruteForce() {
    }

    /**
     * Searches for the first occurrence of pattern in text using the brute force algorithm.
     * Compares the pattern character by character with each position in the text.
     * When a mismatch is found, the algorithm moves to the next starting position in the text.
     *
     * @param text    the text to search in
     * @param pattern the pattern to search for
     * @return the index of the first occurrence, or -1 if not found
     * @throws IllegalArgumentException if text or pattern is null, or pattern is empty
     */
    public static int search(String text, String pattern) {
        validateParams(text, pattern);

        int i = 0; // Pointer for text
        int j = 0; // Pointer for pattern

        // Scan through text and pattern
        while (i < text.length() && j < pattern.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                // Characters match: advance both pointers
                i++;
                j++;
            } else {
                // Mismatch: backtrack text pointer and reset pattern pointer
                // Move to next starting position: i = i - j + 1
                // This is equivalent to: i = (i - j) + 1 = start_of_current_match + 1
                i = i - j + 1;
                j = 0; // Reset pattern pointer to beginning
            }
        }

        // Return the starting index if full pattern is matched, otherwise -1
        return j == pattern.length() ? i - j : -1;
    }

    /**
     * Validates input parameters to ensure they meet the requirements for string matching.
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
}
