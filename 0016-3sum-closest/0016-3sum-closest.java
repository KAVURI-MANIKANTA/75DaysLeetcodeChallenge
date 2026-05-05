class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int closeSum = nums[0]+nums[1]+nums[2];
        int n = nums.length;
        for(int i=0; i<n-2; i++){
            for(int j=i+1; j<n-1; j++){
                for(int k = j+1; k<n; k++){
                    int sum = nums[i]+nums[j]+nums[k];
                    if(Math.abs(target-sum)<Math.abs(target-closeSum)){
                        closeSum = sum;
                    }
                }
            }
        }
        return closeSum;
    }
}