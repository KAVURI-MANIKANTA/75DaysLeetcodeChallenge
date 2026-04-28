class Solution {
    public int majorityElement(int[] nums) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        int n = nums.length;
        for(int num:nums){
            hm.put(num,hm.getOrDefault(num,0)+1);
            if(hm.get(num)>(n/2)){
                return num;
            }
        }
        return -1;
    }
}