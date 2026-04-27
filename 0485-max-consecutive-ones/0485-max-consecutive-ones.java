class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int res = 0;
        int max = 0;
        for(int r=0; r<n; r++){
            if(nums[r]==1) res++;
            else res = 0;
            max = Math.max(max,res);
        }
        return max;
    }
}