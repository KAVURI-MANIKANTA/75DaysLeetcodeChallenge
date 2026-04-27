class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int sum = 0;
        int n = cardPoints.length-1;
        for(int i=0; i<k; i++){
            sum = sum + cardPoints[i];
        }
        int max = sum;
        for(int i=k-1; i>=0; i--){
            sum = sum - cardPoints[i] + cardPoints[n--];
            max = Math.max(max,sum);
        }
        return max;
    }
}