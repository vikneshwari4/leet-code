class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        int[] targetMap = new int[128];
        for (int i = 0; i < t.length(); i++) {
            targetMap[t.charAt(i)]++;
        }

        int left = 0;
        int right = 0;
        int minLen = Integer.MAX_VALUE;
        int startIndex = 0;
        int requiredCharsCount = t.length();

        while (right < s.length()) {
            char rightChar = s.charAt(right);
            
            if (targetMap[rightChar] > 0) {
                requiredCharsCount--;
            }
            
            targetMap[rightChar]--;
            right++;

            while (requiredCharsCount == 0) {
                if (right - left < minLen) {
                    minLen = right - left;
                    startIndex = left;
                }

                char leftChar = s.charAt(left);
                targetMap[leftChar]++;
                
                if (targetMap[leftChar] > 0) {
                    requiredCharsCount++;
                }
                
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(startIndex, startIndex + minLen);
    }
}
