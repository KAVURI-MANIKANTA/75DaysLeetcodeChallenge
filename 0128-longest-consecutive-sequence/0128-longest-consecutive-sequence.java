class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums.length==0) return 0;
        Arrays.sort(nums);
        int k = 1;
        int ln = 1;
        int n = nums.length;
        for(int i=1;i<n; i++){
            if(nums[i]==nums[i-1]){
                continue;
            }
            if(nums[i]==nums[i-1]+1){
                k++;
            }
            else{
                k=1;
            }
            if(k>ln) ln = k;
        }
        return ln;
    }
}