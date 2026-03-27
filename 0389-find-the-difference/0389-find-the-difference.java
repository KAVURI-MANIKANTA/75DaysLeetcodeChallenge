class Solution {
    public char findTheDifference(String s, String t) {
        int result = 0;
        for(char ch:s.toCharArray()) result = result^ch;
        for(char ch:t.toCharArray()) result = result^ch;
        return (char)result;
    }
}