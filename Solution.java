import java.util.*;

class Solution {
    List<List<String>> result = new ArrayList<>();
    Set<Integer> col = new HashSet<>();
    Set<Integer> diag1 = new HashSet<>(); 
    Set<Integer> diag2 = new HashSet<>(); 

    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];

        for (char[] row : board)
            Arrays.fill(row, '.');

        backtrack(0, n, board);

        return result;
    }

    void backtrack(int row, int n, char[][] board) {

    
        if (row == n) {
            List<String> temp = new ArrayList<>();

            for (char[] r : board)
                temp.add(new String(r));

            result.add(temp);
            return;
        }

    
        for (int c = 0; c < n; c++) {

            if (col.contains(c) ||
                diag1.contains(row - c) ||
                diag2.contains(row + c)) {
                continue;
            }


            board[row][c] = 'Q';
            col.add(c);
            diag1.add(row - c);
            diag2.add(row + c);

            
            backtrack(row + 1, n, board);

            
            board[row][c] = '.';
            col.remove(c);
            diag1.remove(row - c);
            diag2.remove(row + c);
        }
    }
}