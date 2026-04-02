class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> st = new Stack<>();
        int n = temperatures.length;
        int[] res = new int[n];
        for(int i=n-1; i>=0; i--){
            while(!st.isEmpty() && temperatures[i]>=temperatures[st.peek()]){
                st.pop();
            }
            res[i] = st.isEmpty()?0:st.peek()-i;
            st.push(i);
        }
        return res;
    }
}

/*
        int n = temperatures.length;
        int[] res = new int[n];
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                if(temperatures[i]<temperatures[j]){
                    res[i] = j-i;
                    break;
                }
            }
        }
        return res;
        */