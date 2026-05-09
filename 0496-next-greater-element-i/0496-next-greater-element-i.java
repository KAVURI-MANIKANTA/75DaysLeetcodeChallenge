class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        Deque<Integer> st = new ArrayDeque<>();
        int[] result = new int[n1];
        HashMap<Integer,Integer> hm = new HashMap<>();
        for(int i=0; i<n2; i++){
            while(!st.isEmpty() && st.peek()<nums2[i]){
                hm.put(st.pop(),nums2[i]);
            }
            st.push(nums2[i]);
        }
        while(!st.isEmpty()){
            hm.put(st.pop(),-1);
        }
        for(int i=0; i<n1; i++){
            result[i]= hm.get(nums1[i]);
        }
        return result;
    }
}