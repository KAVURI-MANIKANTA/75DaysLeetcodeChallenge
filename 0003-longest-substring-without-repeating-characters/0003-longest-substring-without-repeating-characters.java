class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character,Integer> hm = new HashMap<>();
        int l = 0;
        int r = 0;
        int n = s.length();
        int maxl = 0;
        while(r<n){
            if(hm.containsKey(s.charAt(r))){
                if(l<=hm.get(s.charAt(r))){
                    l = hm.get(s.charAt(r))+1;
                }
            }
            hm.put(s.charAt(r),r);
            maxl = Math.max(maxl,r-hm.get(s.charAt(l))+1);
            r++;
        }
        return maxl;
    }
}