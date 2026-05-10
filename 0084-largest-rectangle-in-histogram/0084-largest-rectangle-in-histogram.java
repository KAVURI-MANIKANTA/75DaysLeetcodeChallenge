class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> st = new Stack<>();
        int maxA = 0;
        for(int i = 0; i <= n; i++){
            while(!st.isEmpty() && (i == n || heights[st.peek()] >= heights[i])){
                int height = heights[st.pop()];
                int nse = i;
                int pse;
                if(st.isEmpty()){
                    pse = -1;
                } else {
                    pse = st.peek();
                }
                int width = nse - pse - 1;
                int area = height * width;
                maxA = Math.max(maxA, area);
            }
            st.push(i);
        }
        return maxA;
    }
}