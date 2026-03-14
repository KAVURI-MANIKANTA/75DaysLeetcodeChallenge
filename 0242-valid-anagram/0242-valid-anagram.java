class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()) return false;
        int[] frequ = new int[26];
        for(int i=0; i<s.length(); i++){
            frequ[s.charAt(i)-'a']++;
            frequ[t.charAt(i)-'a']--;
        }
        for(int i=0; i<26; i++){
            if(frequ[i]!=0) return false;
        }
        return true;
    }
}