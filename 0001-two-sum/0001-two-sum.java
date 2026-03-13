class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        int[] fin = new int[2];
        for(int i=0; i<nums.length; i++){
            int dif = target - nums[i];
            if(hm.containsKey(dif)){
                fin[0] = hm.get(dif);
                fin[1] = i;
                return fin;
            }
            hm.put(nums[i],i);
        }
        return fin;
    }
}