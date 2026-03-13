class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> uni = new HashSet<>();
        for(int j=0; j<nums.length; j++){
            if(uni.contains(nums[j])){
                return true;
            }
            uni.add(nums[j]);
        }
        return false;
    }
}