class Solution {
    public int diagonalSum(int[][] mat) {
        int n = mat.length;
        int sum = 0;
        for(int j=0; j<n; j++){
            sum = sum + mat[j][j]+mat[j][n-j-1];
        }
        if(n%2==1){
            sum = sum-mat[n/2][n/2];
        }
        return sum;
    }
}