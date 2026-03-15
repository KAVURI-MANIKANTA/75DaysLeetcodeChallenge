class Solution {
    public int trailingZeroes(int n) {
        int count = 0;
        int sum = 0;
        while(n>0){
            sum = sum + n/5;
            n=n/5;
        }
        return sum;
    }
}