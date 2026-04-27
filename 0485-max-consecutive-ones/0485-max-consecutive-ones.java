class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int r = 0;
        int n = nums.length;
        int res = 0;
        int max = 0;
        while(r<n){
            if(nums[r]==1) res++;
            else res = 0;
            max = Math.max(max,res);
            r++;
        }
        return max;
    }
}