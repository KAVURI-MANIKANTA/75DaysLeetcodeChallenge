//tc:O(logn) sc:O(1)
class Solution {
    public int findMin(int[] nums) {
        int l=0;
        int r=nums.length-1;
        if(nums[l]<=nums[r]) return nums[l];
        int mid = 0;
        while(l<r){
            mid = l+(r-l)/2;
            if(nums[mid]<nums[r]) r=mid;
            else l=mid+1;
        }
        return nums[l];
    }
}



/* tc:O(n) sc:O(1)
class Solution {
    public int findMin(int[] nums) {
        if(nums[0]<=nums[nums.length-1]) return nums[0];
        int min = Integer.MAX_VALUE;
        for(int i=0; i<nums.length; i++){
            if(nums[i]<min) min = nums[i];
        }
        return min;
    }
}
*/

/* tc:O(nlogn) sc:O(logn) sorting uses recursion internally
class Solution {
    public int findMin(int[] nums) {
        if(nums[0]<=nums[nums.length-1]) return nums[0];
        Arrays.sort(nums);
        return nums[0];
    }
}
*/