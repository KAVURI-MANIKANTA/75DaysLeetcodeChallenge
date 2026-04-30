class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> hs = new HashSet<>();
        for(int num:nums){
            hs.add(num);
        }
        int ln = 0;
        for(int num:hs){
            if(!hs.contains(num-1)){
                int cl = 1;
                int nn = num+1;
                while(hs.contains(nn)){
                    nn++;
                    cl++;
                }
                ln = Math.max(ln,cl);
            }
        }
        return ln;
    }
}
// ln : longest length, cl : current length, nn : next number, bn : before number