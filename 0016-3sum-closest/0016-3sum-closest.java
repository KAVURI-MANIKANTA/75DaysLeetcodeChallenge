class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int closeSum = nums[0]+nums[1]+nums[2];
        if(closeSum>=target || n==3){
            return closeSum;
        }
        int largeSum = nums[n-1]+nums[n-2]+nums[n-3];
        if(largeSum<=target){
            return largeSum;
        }
        for(int i=0; i<n-2; i++){
            int l = i+1;
            int r = n-1;
            while(l<r){
                int sum = nums[i]+nums[l]+nums[r];
                if(Math.abs(target-sum)<Math.abs(target-closeSum)){
                    closeSum = sum;
                }
                if(sum>target){
                    r--;
                }
                else if(sum<target){
                    l++;
                }
                else{
                    return closeSum;
                }
            }
        }
        return closeSum;
    }
}