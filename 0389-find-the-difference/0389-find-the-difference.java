class Solution {
    public char findTheDifference(String s, String t) {
        int result = 0;
        int sl = s.length();
        for(int i=0; i<sl; i++) result = result^s.charAt(i)^t.charAt(i);
        result = result^t.charAt(t.length()-1);
        return (char)result;
    }
}