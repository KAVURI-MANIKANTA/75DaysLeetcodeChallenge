class Solution {
    public int[] replaceElements(int[] arr) {
        int maxRight = -1;
        int n = arr.length-1;
        for(int i=n; i>=0; i--){
            int current = arr[i];
            arr[i] = maxRight;
            if(maxRight<current){
                maxRight = current;
            }
        }
        return arr;
    }
}