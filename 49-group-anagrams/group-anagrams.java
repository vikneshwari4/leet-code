import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<List<String>>();
        }
        
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedKey = String.valueOf(chars);
            
            if (!map.containsKey(sortedKey)) {
                map.put(sortedKey, new ArrayList<String>());
            }
            
            map.get(sortedKey).add(str);
        }
        
        return new ArrayList<List<String>>(map.values());
    }
}
