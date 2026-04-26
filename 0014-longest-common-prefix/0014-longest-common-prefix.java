class Solution {
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        char[] first = strs[0].toCharArray();
        char[] last = strs[strs.length-1].toCharArray();
        StringBuilder res = new StringBuilder();
        int n = first.length;
        for(int i=0; i<n; i++){
            if(first[i]!=last[i]) break;
            res.append(first[i]);
        }
        return res.toString();
    }
}