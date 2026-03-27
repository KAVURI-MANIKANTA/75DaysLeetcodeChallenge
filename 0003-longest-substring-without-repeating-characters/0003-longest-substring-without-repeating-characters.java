class Solution {
    public int lengthOfLongestSubstring(String s) {
        int l=0;
        int maxl=0;
        HashSet<Character> hs = new HashSet<>();
        for(int r=0; r<s.length(); r++){
            while(hs.contains(s.charAt(r))){
                hs.remove(s.charAt(l));
                l++;
            }
            hs.add(s.charAt(r));
            maxl=Math.max(maxl,r-l+1);
        }
        return maxl;
    }
}