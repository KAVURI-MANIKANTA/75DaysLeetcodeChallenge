class Solution {
    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int l=0;
        int r=0;
        int ln = word1.length();
        int rn = word2.length();
        while(l<ln && r<rn){
            sb.append(word1.charAt(l)).append(word2.charAt(r));
            l++;
            r++;
        }
        while(l<ln){
            sb.append(word1.charAt(l));
            l++;
        }
        while(r<rn){
            sb.append(word2.charAt(r));
            r++;
        }
        return sb.toString();
    }
}