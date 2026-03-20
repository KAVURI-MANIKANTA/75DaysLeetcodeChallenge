class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int u = 1;
        for(int j=1; j<n; j++){
            if(nums[j]!=nums[j-1]){
                nums[u] = nums[j];
                u++;
            }
        }
        return u;
    }
}