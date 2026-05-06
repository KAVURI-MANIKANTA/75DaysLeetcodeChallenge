class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0;
        int ln = Integer.MAX_VALUE;
        int l = 0;
        int n = nums.length;
        for(int r=0; r<n; r++){
            sum = sum + nums[r];
            while(sum>=target){
                if(ln>(r-l+1)){
                    ln = r-l+1;
                }
                sum = sum - nums[l];
                l++;
            }
        }
        return ln==Integer.MAX_VALUE?0:ln;
    }
}