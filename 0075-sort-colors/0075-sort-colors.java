class Solution {
    public void sortColors(int[] nums) {
        int l=0;
        int r=nums.length-1;
        int i=0;
        while(i<=r){
            if(nums[i]==0){
                int temp = nums[l];
                nums[l] = 0;
                nums[i] = temp;
                i++;
                l++;
            }
            else if(nums[i]==2){
                int temp = nums[r];
                nums[r] = 2;
                nums[i] = temp;
                r--;
            }
            else{
                i++;
            }
        }
    }
}