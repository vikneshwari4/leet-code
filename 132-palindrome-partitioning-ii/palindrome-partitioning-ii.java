class Solution {
    public int minCut(String s) {
        int n = s.length();
        if (n <= 1) return 0;

        boolean[][] isPal = new boolean[n][n];
        for (int right = 0; right < n; right++) {
            for (int left = 0; left <= right; left++) {
                if (s.charAt(left) == s.charAt(right) && (right - left <= 2 || isPal[left + 1][right - 1])) {
                    isPal[left][right] = true;
                }
            }
        }

        int[] cuts = new int[n];
        for (int i = 0; i < n; i++) {
            if (isPal[0][i]) {
                cuts[i] = 0;
            } else {
                cuts[i] = i; 
                for (int j = 1; j <= i; j++) {
                    if (isPal[j][i]) {
                        cuts[i] = Math.min(cuts[i], cuts[j - 1] + 1);
                    }
                }
            }
        }

        return cuts[n - 1];
    }
}
