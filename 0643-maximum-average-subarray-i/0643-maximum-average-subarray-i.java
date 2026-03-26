class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int s = 0;
        int mS = 0;
        int n = nums.length;
        for(int j=0; j<k; j++){
            s = s + nums[j];
        }
        mS = s;
        for(int a=k; a<n; a++){
            s = s + nums[a] - nums[a-k];
            mS = Math.max(mS,s);
        }
        return (double) mS/k;
    }
}