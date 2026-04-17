import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> reversedIndices = new HashMap<>();
        int minDistance = Integer.MAX_VALUE;

        for (int j = 0; j < nums.length; j++) {
            if (reversedIndices.containsKey(nums[j])) {
                int distance = j - reversedIndices.get(nums[j]);
                minDistance = Math.min(minDistance, distance);
            }

            int reversedVal = reverse(nums[j]);
            
            reversedIndices.put(reversedVal, j);
        }

        return (minDistance == Integer.MAX_VALUE) ? -1 : minDistance;
    }

    private int reverse(int n) {
        int rev = 0;
        while (n > 0) {
            rev = rev * 10 + (n % 10);
            n /= 10;
        }
        return rev;
    }
}
