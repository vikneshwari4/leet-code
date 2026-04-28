class Solution(object):
    def minOperations(self, grid, x):
        """
        :type grid: List[List[int]]
        :type x: int
        :rtype: int
        """
        nums = []
        for row in grid:
            for val in row:
                nums.append(val)
        
        base_remainder = nums[0] % x
        for val in nums:
            if val % x != base_remainder:
                return -1
        
        nums.sort()
        median = nums[len(nums) // 2]
        
        total_ops = 0
        for val in nums:
            total_ops += abs(val - median) // x
            
        return total_ops
