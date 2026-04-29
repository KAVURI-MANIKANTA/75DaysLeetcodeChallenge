class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashSet<Character>[] r = new HashSet[9];
        HashSet<Character>[] c = new HashSet[9];
        HashSet<Character>[] b = new HashSet[9];
        for(int i=0; i<9; i++){
            r[i] = new HashSet<>();
            c[i] = new HashSet<>();
            b[i] = new HashSet<>();
        }
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                char ch = board[i][j];
                if(ch=='.') continue;
                int bi = (i/3)*3 + (j/3);
                if(r[i].contains(ch) || c[j].contains(ch) || b[bi].contains(ch)){
                    return false;
                }
                r[i].add(ch);
                c[j].add(ch);
                b[bi].add(ch);
            }
        }
        return true;
    }
}