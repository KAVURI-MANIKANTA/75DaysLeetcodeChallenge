class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character,Integer> hm = new HashMap<>();
        int l = 0;
        int r = 0;
        int n = s.length();
        int maxl = 0;
        while(r<n){
            char ch = s.charAt(r);
            if(hm.containsKey(ch)){
                if(l<=hm.get(ch)){
                    l = hm.get(ch)+1;
                }
            }
            hm.put(ch,r);
            maxl = Math.max(maxl,r-l+1);
            r++;
        }
        return maxl;
    }
}