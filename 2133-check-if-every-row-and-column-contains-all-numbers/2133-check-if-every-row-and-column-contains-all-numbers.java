class Solution {
    public boolean checkValid(int[][] matrix) {
        int n = matrix.length;
        for(int i=0; i<n; i++){
            boolean[] row = new boolean[n+1];
            boolean[] col = new boolean[n+1];
            for(int j=0; j<n; j++){
                int rn = matrix[i][j];
                if(row[rn]) return false;
                row[rn] = true;
                int cn = matrix[j][i];
                if(col[cn]) return false;
                col[cn] = true;
            }
        }
        return true;
    }
}