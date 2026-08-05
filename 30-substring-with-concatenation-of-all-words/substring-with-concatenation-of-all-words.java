import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<Integer>();
        
        // Edge cases
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }
        
        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;
        
        if (s.length() < totalLen) {
            return result;
        }
        
        // Count frequencies of each word in the given array
        Map<String, Integer> wordFreq = new HashMap<String, Integer>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }
        
        // Run sliding window wordLen times for different starting offsets
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int right = i;
            int count = 0;
            Map<String, Integer> currentFreq = new HashMap<String, Integer>();
            
            while (right + wordLen <= s.length()) {
                // Extract the next word from the right side of the window
                String word = s.substring(right, right + wordLen);
                right += wordLen;
                
                if (wordFreq.containsKey(word)) {
                    currentFreq.put(word, currentFreq.getOrDefault(word, 0) + 1);
                    count++;
                    
                    // If we have more occurrences of the word than allowed, shrink window from left
                    while (currentFreq.get(word) > wordFreq.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        currentFreq.put(leftWord, currentFreq.get(leftWord) - 1);
                        count--;
                        left += wordLen;
                    }
                    
                    // If window matches the target count, record the starting index
                    if (count == wordCount) {
                        result.add(left);
                    }
                } else {
                    // Invalid word encountered: reset window states
                    currentFreq.clear();
                    count = 0;
                    left = right;
                }
            }
        }
        
        return result;
    }
}
