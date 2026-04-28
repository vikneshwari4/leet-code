import java.util.*;

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return res;

        Map<String, Integer> steps = new HashMap<>();
        steps.put(beginWord, 0);
        
        Map<String, List<String>> adj = new HashMap<>();
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        
        boolean found = false;
        int step = 0;
        
        while (!queue.isEmpty() && !found) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                char[] chars = curr.toCharArray();
                
                for (int j = 0; j < chars.length; j++) {
                    char temp = chars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[j] = c;
                        String next = new String(chars);
                        
                        if (steps.containsKey(next) && step == steps.get(next)) {
                            adj.get(next).add(curr);
                        }
                        
                        if (!dict.contains(next)) continue;
                        
                        dict.remove(next); 
                        queue.offer(next);
                        steps.put(next, step);
                        adj.computeIfAbsent(next, k -> new ArrayList<>()).add(curr);
                        
                        if (next.equals(endWord)) found = true;
                    }
                    chars[j] = temp;
                }
            }
        }

        if (found) {
            Deque<String> path = new ArrayDeque<>();
            path.add(endWord);
            backtrack(endWord, beginWord, adj, path, res);
        }
        return res;
    }

    private void backtrack(String curr, String beginWord, Map<String, List<String>> adj, Deque<String> path, List<List<String>> res) {
        if (curr.equals(beginWord)) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (String prev : adj.getOrDefault(curr, new ArrayList<>())) {
            path.addFirst(prev);
            backtrack(prev, beginWord, adj, path, res);
            path.removeFirst();
        }
    }
}
