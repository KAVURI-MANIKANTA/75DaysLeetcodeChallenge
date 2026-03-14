class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k<=1){
            return 0;
        }
        int product = 1;
        int l = 0;
        int r = 0;
        int total = 0;
        while(r<nums.length){
            product = product*nums[r];
            while(product>=k){
                product = product/nums[l];
                l++;
            }
            total = total + r-l+1;
            r++;
        }
        return total;
    }
}