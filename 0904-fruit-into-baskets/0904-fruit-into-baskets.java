class Solution {
    public int totalFruit(int[] fruits) {
        int dis = 0;
        int l=0;
        int max = 0;
        int n = fruits.length;
        int[] c = new int[n+1];
        for(int r=0; r<n; r++){
            if(c[fruits[r]]==0){
                dis++;
            }
            c[fruits[r]]++;
            while(dis>2){
                c[fruits[l]]--;
                if(c[fruits[l]]==0){
                    dis--;
                }
                l++;
            }
            max = Math.max(max,r-l+1);
        }
        return max;
    }
}