import math

class Solution(object):
    def getPermutation(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: str
        """
        numbers = [str(i) for i in range(1, n + 1)]
        
        k -= 1
        
        res = []
        factorial = math.factorial(n - 1)
        
        for i in range(n - 1, 0, -1):
            idx = k // factorial
            res.append(numbers.pop(idx))
            
            k %= factorial
            factorial //= i
            
        res.append(numbers[0])
        
        return "".join(res)
