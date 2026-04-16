from bisect import bisect_left

class Solution(object):
    def solveQueries(self, nums, queries):
        """
        :type nums: List[int]
        :type queries: List[int]
        :rtype: List[int]
        """
        n = len(nums)
        pos_map = {}
        for idx, val in enumerate(nums):
            if val not in pos_map:
                pos_map[val] = []
            pos_map[val].append(idx)
        
        results = []
        for q_idx in queries:
            target_val = nums[q_idx]
            indices = pos_map[target_val]
            
            if len(indices) == 1:
                results.append(-1)
                continue
            
            pos = bisect_left(indices, q_idx)
            
            left_neighbor = indices[(pos - 1) % len(indices)]
            right_neighbor = indices[(pos + 1) % len(indices)]
            
            def get_circ_dist(i, j):
                direct = abs(i - j)
                return min(direct, n - direct)
            
            results.append(min(get_circ_dist(q_idx, left_neighbor), 
                               get_circ_dist(q_idx, right_neighbor)))
            
        return results
