class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int l=0;
        int nc = matrix[0].length;
        int r = matrix.length*nc-1;
        int mid = 0;
        while(l<=r){
            mid = l+(r-l)/2;
            int row = mid/nc;
            int col = mid%nc;
            if(matrix[row][col]==target) return true;
            else if(matrix[row][col]<target) l = mid+1;
            else r = mid-1;
        }
        return false;
    }
}