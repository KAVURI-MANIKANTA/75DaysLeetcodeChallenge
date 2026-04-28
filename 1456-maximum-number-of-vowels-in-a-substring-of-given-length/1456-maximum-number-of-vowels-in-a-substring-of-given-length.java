class Solution {
    public int maxVowels(String s, int k) {
        int count = 0;
        int n = s.length();
        for(int r=0; r<k; r++){
            if(isVowel(s.charAt(r))){
                count++;
            }
        }
        int l=0;
        int max = count;
        for(int r=k; r<n; r++){
            if(isVowel(s.charAt(r))){
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