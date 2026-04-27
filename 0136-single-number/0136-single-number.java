class Solution {
    public int singleNumber(int[] nums) {
        int r = 0;
        int n = nums.length;
        for(int i=0; i<n; i++) r = r^nums[i];
        return r;
    }
}