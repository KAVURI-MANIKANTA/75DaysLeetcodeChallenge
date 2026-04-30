class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums.length==0) return 0;
        HashSet<Integer> hs = new HashSet<>();
        for(int num:nums){
            hs.add(num);
        }
        int ln = 0;
        for(int num:hs){
            if(!hs.contains(num-1)){
                int cl = 1;
                int curr = num;
                while(hs.contains(curr+1)){
                    curr++;
                    cl++;
                }
                if(cl>ln) ln = cl;
            }
        }
        return ln;
    }
}
// ln : longest length, cl : current length, nn : next number, bn : before number