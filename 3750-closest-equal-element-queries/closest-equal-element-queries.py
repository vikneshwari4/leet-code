from bisect import bisect_left

class Solution(object):
    def solveQueries(self, nums, queries):
        """
        :type nums: List[int]
        :type queries: List[int]
        :rtype: List[int]
        """
        n = len(nums)
        # 1. Map each value to its indices
        pos_map = {}
        for idx, val in enumerate(nums):
            if val not in pos_map:
                pos_map[val] = []
            pos_map[val].append(idx)
        
        results = []
        # 2. Process each query
        for q_idx in queries:
            target_val = nums[q_idx]
            indices = pos_map[target_val]
            
            # If the number only appears once, return -1
            if len(indices) == 1:
                results.append(-1)
                continue
            
            # Binary search for neighbors
            pos = bisect_left(indices, q_idx)
            
            # Circular neighbors
            left_neighbor = indices[(pos - 1) % len(indices)]
            right_neighbor = indices[(pos + 1) % len(indices)]
            
            # Calculate circular distances
            d1 = abs(q_idx - left_neighbor)
            dist1 = min(d1, n - d1)
            
            d2 = abs(q_idx - right_neighbor)
            dist2 = min(d2, n - d2)
            
            results.append(min(dist1, dist2))
            
        return results
