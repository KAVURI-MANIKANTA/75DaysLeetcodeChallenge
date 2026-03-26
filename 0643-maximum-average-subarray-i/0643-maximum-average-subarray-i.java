class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int kSum = 0;
        int maximumSum = 0;
        for(int j=0; j<k; j++){
            kSum = kSum + nums[j];
        }
        maximumSum = kSum;
        for(int a=k; a<nums.length; a++){
            kSum = kSum + nums[a] - nums[a-k];
            maximumSum = Math.max(maximumSum,kSum);
        }
        return (double) maximumSum/k;
    }
}