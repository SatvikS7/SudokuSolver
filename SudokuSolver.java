public class SudokuSolver {

    static int[][] board = {
        { 5, 1, 6, 8, 4, 9, 7, 3, 2 },
        { 3, 0, 7, 6, 0, 5, 0, 0, 0 },
        { 8, 0, 9, 7, 0, 0, 0, 6, 5 },
        { 1, 3, 5, 0, 6, 0, 9, 0, 7 },
        { 4, 7, 2, 5, 9, 1, 0, 0, 6 },
        { 9, 6, 8, 3, 7, 0, 0, 5, 0 },
        { 2, 5, 3, 1, 8, 6, 0, 7, 4 },
        { 6, 8, 4, 2, 0, 7, 5, 0, 0 },
        { 7, 9, 1, 0, 5, 0, 6, 0, 8 } 
    };
    public static boolean checkNum(int r, int c, int num) {
        //check row for same num
        for(int col = 0; col < board[r].length; col++) {
            if(board[r][col] == num) {
                return false;
            }
        }
        //check col for same num
        for(int row = 0; row < board.length; row++) {
            if(board[row][c] == num) {
                return false;
            }
        }
        //check square for same num
        for(int i = r-(r%3); i < r-(r%3)+3; i++) {
            for(int j = c-(c%3); j < c-(c%3)+3; j++) {
                if(i != r && j != c) {
                    if(board[i][j] == num) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static int adjNumRecur(int r, int c, int num) {
        if(num == 10) {
            return 0;
        }
        if(!checkNum(r, c, num)) {
            return adjNumRecur(r, c, num+1);
        } else {
            return num;
        }
    }

    public static void printBoard() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        
    }

    public static void main(String[] args) {
        boolean[][] isBlank = new boolean[board.length][board[0].length]; 
        int r = 0;
        int c = 0; 
        boolean firstPass = true;
        int firstR = -1;
        int firstC = -1;
        
        while(r < board.length && c < board[0].length) {
            if(board[r][c] != 0 && isBlank[r][c] == false) {
                if(c == 8) {
                    c = 0;
                    r++;
                } else {
                    c++;
                }
                continue;
            } else {
                isBlank[r][c] = true;
                if(firstPass) {
                    firstC = c;
                    firstR = r;
                    firstPass = false;
                }
            }
 
            board[r][c] = adjNumRecur(r, c, board[r][c]+1);
            if(board[r][c] == 0) {
                if(firstC == c && firstR == r) {
                    System.out.println("No solution exists");
                    return;
                }
                if(c == 0) {
                    c = 8;
                    r--;
                } else {
                    c--;
                }  
                while(isBlank[r][c] != true) {
                    if(c == 0) {
                        c = 8;
                        r--;
                    } else {
                        c--;
                    }        
                }
            } else {
                if(c == 8) {
                    c = 0;
                    r++;
                } else {
                    c++;
                }
            }
        }
        printBoard();
    }
}
