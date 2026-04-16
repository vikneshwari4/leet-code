class Solution(object):
    def solveSudoku(self, board):
        rows = [set() for _ in range(9)]
        cols = [set() for _ in range(9)]
        boxes = [set() for _ in range(9)]
        empty_cells = []

        for r in range(9):
            for c in range(9):
                if board[r][c] == ".":
                    empty_cells.append((r, c))
                else:
                    val = board[r][c]
                    rows[r].add(val)
                    cols[c].add(val)
                    boxes[(r // 3) * 3 + (c // 3)].add(val)

        def backtrack(index):
            if index == len(empty_cells):
                return True
            
            r, c = empty_cells[index]
            box_idx = (r // 3) * 3 + (c // 3)

            for char in "123456789":
                if char not in rows[r] and char not in cols[c] and char not in boxes[box_idx]:
                    board[r][c] = char
                    rows[r].add(char)
                    cols[c].add(char)
                    boxes[box_idx].add(char)

                    if backtrack(index + 1):
                        return True
                    
                    board[r][c] = "."
                    rows[r].remove(char)
                    cols[c].remove(char)
                    boxes[box_idx].remove(char)
            
            return False

        backtrack(0)
