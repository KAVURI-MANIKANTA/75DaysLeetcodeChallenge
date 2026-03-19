class Solution {
    public boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length()-1;
        while(l<=r){
            char lch = s.charAt(l);
            char rch = s.charAt(r);
            if(!Character.isLetterOrDigit(s.charAt(l))){
                l++;
            }
            else if(!Character.isLetterOrDigit(s.charAt(r))){
                r--;
            }
            else if(Character.toLowerCase(lch)!=Character.toLowerCase(rch)){
                return false;
            }
            else{
                l++;
                r--;
            }
        }
        return true;
    }
}