class Solution {
    public void dfs(int i, int j, char[][] grid){
        if(i>=grid.length || i<0 || j<0 || j>=grid[0].length){
            return;
        }
        if(grid[i][j]!='1'){
            return;
        }
        grid[i][j] = 'x';
        dfs(i-1,j,grid);
        dfs(i+1,j,grid);
        dfs(i,j-1,grid);
        dfs(i,j+1,grid);
    }
    public int numIslands(char[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int count = 0;
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(grid[i][j]=='1'){
                    dfs(i,j,grid);
                    count++;
                }
            }
        }
        return count;
    }
}