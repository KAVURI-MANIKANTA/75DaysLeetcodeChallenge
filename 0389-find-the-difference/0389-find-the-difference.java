class Solution {
    public char findTheDifference(String s, String t) {
        int[] freq = new int[26];
        int sl = s.length();
        for(int i=0; i<sl; i++){
            freq[s.charAt(i)-'a']++;
        }
        int tl = t.length();
        for(int i=0; i<tl; i++){
            if(freq[t.charAt(i)-'a']==0){
                return t.charAt(i);
            }
            freq[t.charAt(i)-'a']--;
        }
        return ' ';
    }
}