import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<String>();
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char number = board[i][j];
                
                if (number != '.') {
                    String rowKey = number + " in row " + i;
                    String colKey = number + " in col " + j;
                    String boxKey = number + " in box " + (i / 3) + "-" + (j / 3);
                    
                    if (!seen.add(rowKey) || !seen.add(colKey) || !seen.add(boxKey)) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
}
