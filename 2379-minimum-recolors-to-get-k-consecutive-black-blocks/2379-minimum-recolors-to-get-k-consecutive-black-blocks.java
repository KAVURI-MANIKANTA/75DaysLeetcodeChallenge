class Solution {
    public int minimumRecolors(String blocks, int k) {
        int w = 0;
        int n = blocks.length();
        for(int i=0; i<k; i++){
            if(blocks.charAt(i)=='W'){
                w++;
            }
        }
        int min = w;
        for(int i=k; i<n; i++){
            if(blocks.charAt(i)=='W'){
                w++;
            }
            if(blocks.charAt(i-k)=='W'){
                w--;
            }
            min = Math.min(min,w);
        }
        return min;
    }
}