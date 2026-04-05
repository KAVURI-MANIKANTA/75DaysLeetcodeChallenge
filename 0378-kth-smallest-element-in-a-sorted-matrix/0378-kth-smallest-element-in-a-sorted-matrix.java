class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int l=matrix[0][0];
        int r = matrix[n-1][n-1];
        int mid = 0;
        while(l<r){
            mid = l+(r-l)/2;
            int c = lessTK(matrix, mid);
            if(c<k) l = mid+1;
            else r=mid;
        }
        return l;
    }
    public int lessTK(int[][] matrix, int mid){
        int count = 0;
        int n = matrix.length;
        int row = n-1;
        int col = 0;
        while(row>=0 && col<n){
            if(matrix[row][col]<=mid){
                count = count + (row+1);
                col++;
            }
            else{
                row--;
            }
        }
        return count;
    }
}