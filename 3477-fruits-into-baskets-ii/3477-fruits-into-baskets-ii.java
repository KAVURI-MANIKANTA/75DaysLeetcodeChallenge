class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        boolean[] b = new boolean[n];
        int count = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!b[j] && baskets[j]>=fruits[i]){
                    b[j] = true;
                    count++;
                    break;
                }
            }
        }
        return n-count;
    }
}