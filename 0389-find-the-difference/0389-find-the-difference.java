class Solution {
    public char findTheDifference(String s, String t) {
        int[] freq = new int[26];
        int sl = s.length();
        for(int i=0; i<sl; i++){
            freq[s.charAt(i)-'a']++;
        }
        int tl = t.length();
        for(int i=0; i<tl; i++){
            char ch = t.charAt(i);
            if(freq[ch-'a']==0){
                return ch;
            }
            freq[ch-'a']--;
        }
        return ' ';
    }
}