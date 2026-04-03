class Solution {
    public int search(int[] nums, int target) {
        int y = 0;
        int k = nums.length-1;
        int m = 0;
        while(y<=k){
            m = y+(k-y)/2;
            if(nums[m]==target) return m;
            else if(nums[m]<target) y=m+1;
            else k=m-1;
        }
        return -1;
    }
}