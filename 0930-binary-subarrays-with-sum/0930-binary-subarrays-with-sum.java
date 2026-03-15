class Solution {
    public int atMostK(int[] nums, int goal){
        if(goal<0) return 0;
        int l = 0;
        int sum = 0;
        int result = 0;
        for(int r=0; r<nums.length; r++){
            sum = sum + nums[r];
            while(sum>goal){
                sum = sum - nums[l];
                l++;
            }
            result = result + (r-l+1);
        }
        return result;
    }
    public int numSubarraysWithSum(int[] nums, int goal) {
        return atMostK(nums, goal)-atMostK(nums, goal-1);
    }
}