class Solution {
    public void sortColors(int[] nums) {
        int l=0;
        int r=nums.length-1;
        int j=0;
        while(j<=r){
            if(nums[j]==0){
                int temp = nums[l];
                nums[l] = 0;
                nums[j] = temp;
                j++;
                l++;
            }
            else if(nums[j]==2){
                int temp = nums[r];
                nums[r] = 2;
                nums[j] = temp;
                r--;
            }
            else{
                j++;
            }
        }
    }
}