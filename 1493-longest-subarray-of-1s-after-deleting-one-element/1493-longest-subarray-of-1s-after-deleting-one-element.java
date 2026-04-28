class Solution {
    public int longestSubarray(int[] nums) {
        int l=0;
        int n=nums.length;
        int count=0;
        int max=0;
        for(int r=0; r<n; r++){
            if(nums[r]==0){
                count++;
            }
            while(count>1){
                if(nums[l]==0){
                    count--;
                }
                l++;
            }
            max = Math.max(max,r-l+1);
        }
        return max-1;
    }
}