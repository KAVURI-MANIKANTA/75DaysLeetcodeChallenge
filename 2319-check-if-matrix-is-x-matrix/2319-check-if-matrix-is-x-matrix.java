class Solution {
    public boolean checkXMatrix(int[][] grid) {
        int n = grid.length;
        for(int i=0; i<n; i++){
            int sdi = n-i-1;
            if(grid[i][i]==0 || grid[i][sdi]==0){
                return false;
            }
            for(int j=0; j<n; j++){
                if(j!=i && j!=sdi && grid[i][j]!=0){
                    return false;
                }
            }
        }
        return true;
    }
}