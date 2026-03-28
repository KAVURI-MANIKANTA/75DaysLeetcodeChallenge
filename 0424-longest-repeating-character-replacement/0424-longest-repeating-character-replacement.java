class Solution {
    public int characterReplacement(String s, int k) {
        int l = 0;
        int maxFreq = 0;
        int res = 0;
        HashMap<Character,Integer> hm = new HashMap<>();
        for(int r=0; r<s.length(); r++){
            char ch = s.charAt(r);
            hm.put(ch,hm.getOrDefault(ch,0)+1);
            maxFreq = Math.max(maxFreq,hm.get(ch));
            if((r-l+1)-maxFreq>k){
                hm.put(s.charAt(l),hm.get(s.charAt(l))-1);
                l++;
            }
            res = Math.max(res,(r-l+1));
        }
        return res;
    }
}