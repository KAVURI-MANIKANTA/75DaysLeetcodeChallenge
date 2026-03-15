class Solution {
    public void dfs(int[][] image, int i, int j, int temp, int color){
        if(i<0 || j<0 || i>=image.length || j>=image[0].length) return;

        if(image[i][j]!=temp) return;

        if(image[i][j]==color) return;
        
        image[i][j] = color;

        dfs(image,i-1,j,temp,color);
        dfs(image,i+1,j,temp,color);
        dfs(image,i,j-1,temp,color);
        dfs(image,i,j+1,temp,color);
    }
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int temp = image[sr][sc];
        if(temp==color) return image;
        dfs(image,sr,sc,temp,color);
        return image;
    }
}