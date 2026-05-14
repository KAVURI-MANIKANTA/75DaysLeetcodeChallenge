class Solution {
    public long midv(int[] piles,int num){
        long s = 0;
        for(int i=0; i<piles.length; i++){
            s = s + (piles[i]/num);
            if(piles[i]%num!=0){
                s = s + 1;
            }
        }
        return s;
    }
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1;
        int r = 1000000000;
        int m = 0;
        while(l<=r){
            m = l + (r-l)/2;
            if(midv(piles,m)>h){
                l = m + 1;
            }
            else{
                r = m - 1;
            }
        }
        return l;
    }
}