import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    // Standard initialization compatible with Java 7, 8, and newer
    private static final Map<Character, String> PHONE_MAP = new HashMap<Character, String>();
    
    static {
        PHONE_MAP.put('2', "abc");
        PHONE_MAP.put('3', "def");
        PHONE_MAP.put('4', "ghi");
        PHONE_MAP.put('5', "jkl");
        PHONE_MAP.put('6', "mno");
        PHONE_MAP.put('7', "pqrs");
        PHONE_MAP.put('8', "tuv");
        PHONE_MAP.put('9', "wxyz");
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<String>();
        
        if (digits == null || digits.isEmpty()) {
            return result;
        }
        
        backtrack(digits, 0, new StringBuilder(), result);
        return result;
    }

    private void backtrack(String digits, int index, StringBuilder current, List<String> result) {
        if (current.length() == digits.length()) {
            result.add(current.toString());
            return;
        }

        char currentDigit = digits.charAt(index);
        String letters = PHONE_MAP.get(currentDigit);

        for (int i = 0; i < letters.length(); i++) {
            current.append(letters.charAt(i));       
            backtrack(digits, index + 1, current, result); 
            current.deleteCharAt(current.length() - 1); // Backtrack
        }
    }
}
