class Solution {
    public int maxVowels(String s, int k) {
        int count = 0;
        int n = s.length();
        for(int r=0; r<k; r++){
            char ch = s.charAt(r);
            if(isVowel(ch)){
                count++;
            }
        }
        int l=0;
        int max = count;
        for(int r=k; r<n; r++){
            char ch = s.charAt(r);
            if(isVowel(ch)){
                count++;
            }
            if(isVowel(s.charAt(r-k))){
                count--;
            }
            max = Math.max(max,count);
        }
        return max;
    }
    public boolean isVowel(char ch){
        if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u' ){
            return true;
        }
        return false;
    }
}