class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashSet<String> hs = new HashSet<>();
        for(int i=0; i<9; i++){
            for(int j = 0; j<9; j++){
                char ch = board[i][j];
                if(ch=='.') continue;
                String row = i+"r"+ch;
                String col = j+"c"+ch;
                String box = (i/3)+""+(j/3)+ch;
                if(hs.contains(row) || hs.contains(col) || hs.contains(box)){
                    return false;
                }
                hs.add(row);
                hs.add(col);
                hs.add(box);
            }
        }
        return true;
    }
}