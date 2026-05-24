class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int j=0; j<k; j++){
            pq.add(nums[j]);
        }
        int n = nums.length; 
        for(int i=k; i<n; i++){
            if(nums[i]>pq.peek()){
                pq.poll();
                pq.add(nums[i]);
            }
        }
        return pq.poll();
    }
}