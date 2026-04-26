class Solution {
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        char[] fst = strs[0].toCharArray();
        char[] lst = strs[strs.length-1].toCharArray();
        StringBuilder res = new StringBuilder();
        int n = fst.length;
        for(int i=0; i<n; i++){
            if(fst[i]!=lst[i]) break;
            res.append(fst[i]);
        }
        return res.toString();
    }
}