from collections import Counter

class Solution(object):
    def findSubstring(self, s, words):
        """
        :type s: str
        :type words: List[str]
        :rtype: List[int]
        """
        if not s or not words:
            return []
        
        word_len = len(words[0])
        word_count = len(words)
        total_len = word_len * word_count
        word_freq = Counter(words)
        result = []
        
        for i in range(word_len):
            left = i
            right = i
            current_count = Counter()
            words_found = 0
            
            while right + word_len <= len(s):
                word = s[right : right + word_len]
                right += word_len
                
                if word in word_freq:
                    current_count[word] += 1
                    words_found += 1
                    
                    while current_count[word] > word_freq[word]:
                        left_word = s[left : left + word_len]
                        current_count[left_word] -= 1
                        words_found -= 1
                        left += word_len
                    
                    if words_found == word_count:
                        result.append(left)
                else:
                    current_count.clear()
                    words_found = 0
                    left = right
                    
        return result
