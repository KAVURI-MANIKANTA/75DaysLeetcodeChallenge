class Solution {
    public int longestConsecutive(int[] nums) {
        HashMap<Integer,Boolean> hm = new HashMap<>();
        int ln = 0;
        for(int num:nums){
            hm.put(num,false);
        }
        for(int num:nums){
            int cl = 1;
            int nn = num+1;
            while(hm.containsKey(nn) && hm.get(nn)==false){
                cl++;
                hm.put(nn,true);
                nn++;
            }
            int bn = num-1;
            while(hm.containsKey(bn) && hm.get(bn)==false){
                cl++;
                hm.put(bn,true);
                bn--;
            }
            ln = Math.max(ln,cl);
        }
        return ln;
    }
}
// ln : longest length, cl : current length, nn : next number, bn : before number