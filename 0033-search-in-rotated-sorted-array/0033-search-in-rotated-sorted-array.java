class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length-1;
        for(int i=0; i<=n; i++){
            if(nums[i]==target) return i;
        }
        return -1;
    }
}



/*
class Solution {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length-1;
        int mid = 0;
        while(l<=r){
            mid = l+(r-l)/2;
            if(nums[mid]==target) return mid;
            if(nums[l]<=nums[mid]){
                if(target>=nums[l] && target<=nums[mid]) r=mid;
                else l=mid+1;
            }
            else{
                if(target>nums[mid] && target<=nums[r]) l = mid+1;
                else r=mid;
            }
        }
        return -1;
    }
}
*/