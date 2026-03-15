class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        int prefixSum = 0;
        int count = 0;
        hm.put(0,1);
        for(int r=0; r<nums.length; r++){
            prefixSum = prefixSum + nums[r];
            int rem = prefixSum%k;
            if(rem<0){
                rem = rem + k;
            }
            if(hm.containsKey(rem)){
                count = count + hm.get(rem);
            }
            hm.put((rem),hm.getOrDefault(rem,0)+1);
        }
        return count;
    }
}