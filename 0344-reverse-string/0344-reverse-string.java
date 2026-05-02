class Solution {
    public void reverseString(char[] s) {
        int n = s.length;
        char[] temp = new char[n];
        for(int i=n-1; i>=0; i--){
            temp[n-i-1] = s[i];
        }
        for(int i=0; i<n; i++){
            s[i] = temp[i];
        }
    }
}