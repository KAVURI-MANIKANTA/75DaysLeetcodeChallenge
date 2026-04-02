class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        ArrayDeque<Integer> st = new ArrayDeque<>();
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
Simple Comparison
---------------------------------------------
Feature	        Stack	    ArrayDeque
---------------------------------------------
Thread-safe	    ✅ Yes	    ❌ No
Speed	        ❌ Slow	    ✅ Fast
Recommended	    ❌ No	    ✅ Yes
Internal DS	    Vector	      Dynamic Array
---------------------------------------------
*/



/*
//ArrayDeque beats 96%    tc:O(n), sc:O(n)
// Stack beats 22%   tc:O(n), sc:O(n)

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
*/


/*  tc: O(n^2), sc: O(1)   result array is mentioned in the method so it is not considered
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