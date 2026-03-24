class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length-1;
        int answer=0;
        int value=0;
        while(left<right){
            int minh=Math.min(height[left],height[right]);
            value = (right-left)*minh;
            answer=Math.max(answer,value);
            while(left<right && height[left]<=minh) left++;
            while(left<right && height[right]<=minh) right--;
        }
        return answer;
    }
}