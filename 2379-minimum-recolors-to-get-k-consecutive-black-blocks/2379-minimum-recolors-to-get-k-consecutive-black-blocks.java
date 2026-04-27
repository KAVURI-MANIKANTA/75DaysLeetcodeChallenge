class Solution {
    public int minimumRecolors(String blocks, int k) {
        int b = 0;
        int w = 0;
        int n = blocks.length();
        for(int i=0; i<k; i++){
            char ch = blocks.charAt(i);
            if(ch=='B'){
                b++;
            }
            else{
                w++;
            }
        }
        int min = w;
        for(int i=k; i<n; i++){
            char cch = blocks.charAt(i);
            char ch = blocks.charAt(i-k);
            if(ch=='W' && cch=='B'){
                b++;
                w--;
            }
            else if(ch=='B' && cch == 'W'){
                w++;
                b--;
            }
            min = Math.min(min,w);
        }
        return min;
    }
}