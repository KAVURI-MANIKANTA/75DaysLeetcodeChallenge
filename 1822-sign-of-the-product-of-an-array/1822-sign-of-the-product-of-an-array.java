class Solution {
    public int arraySign(int[] nums) {
        int negCount = 0;
        int n = nums.length;
        for(int i=0; i<n; i++){
            if(nums[i]==0) return 0;
            else if(nums[i]<0) negCount++;
        }
        if(negCount%2==0) return 1;
        return -1;
    }
}