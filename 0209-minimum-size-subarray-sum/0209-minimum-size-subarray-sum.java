class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0;
        int sl = Integer.MAX_VALUE;
        int l = 0;
        int n = nums.length;
        for(int r=0; r<n; r++){
            sum = sum + nums[r];
            while(sum>=target){
                if(sl>(r-l+1)){
                    sl = r-l+1;
                }
                sum = sum - nums[l];
                l++;
            }
        }
        return sl==Integer.MAX_VALUE?0:sl;
    }
}