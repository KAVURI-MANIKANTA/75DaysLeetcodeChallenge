class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        int[] res = new int[2];
        for(int i=0; i<nums.length; i++){
            int dif = target - nums[i];
            if(hm.containsKey(dif)){
                res[0] = hm.get(dif);
                res[1] = i;
                return res;
            }
            hm.put(nums[i],i);
        }
        return res;
    }
}