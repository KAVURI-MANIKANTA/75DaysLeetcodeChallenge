class Solution {
    public int characterReplacement(String s, int k) {
        int l = 0;
        int maxFreq = 0;
        int res = 0;
        int[] freq = new int[26];
        for(int r=0; r<s.length(); r++){
            int ch = s.charAt(r)-'A';
            freq[ch]++;
            maxFreq = Math.max(maxFreq,freq[ch]);
            while((r-l+1)-maxFreq>k){
                freq[s.charAt(l)-'A']--;
                l++;
            }
            res = Math.max(res,(r-l+1));
        }
        return res;
    }
}