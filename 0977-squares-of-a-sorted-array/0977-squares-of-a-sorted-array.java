class Solution {
    public int[] sortedSquares(int[] nums) {
        int r = nums.length-1;
        int l = 0;
        int n = nums.length;
        int[] res = new int[n];
        int pos = n-1;
        while(l<=r){
            int left = nums[l]*nums[l];
            int right = nums[r]*nums[r];
            if(right>left){
                res[pos] = right;
                r--;
            }
            else{
                res[pos] = left;
                l++;
            }
            pos--;
        }
        return res;
    }
}