class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int dif = arr[1]-arr[0];
        for(int i=2; i<n; i++){
            int curr = arr[i]-arr[i-1];
            if(curr!=dif) return false;
        }
        return true;
    }
}