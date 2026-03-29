class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i<k; i++){
            pq.add(nums[i]);
        }
        int n = nums.length; 
        for(int i=k; i<n; i++){
            if(nums[i]>pq.peek()){
                pq.poll();
                pq.add(nums[i]);
            }
        }
        return pq.poll();


        /*
        class Solution {
            public int findKthLargest(int[] nums, int k) {
                Arrays.sort(nums);
                return nums[nums.length-k];
            }
        }
        */

        /*
        class Solution {
            public int findKthLargest(int[] nums, int k) {
                PriorityQueue<Integer> pq = new PriorityQueue<>();
                for(int n:nums){
                    pq.add(n);
                    if(pq.size()>k){
                        pq.poll();
                    }
                }
                return pq.poll();
            }
        }
        */


        /*
        class Solution {
            public int findKthLargest(int[] nums, int k) {
                PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
                int n = nums.length;
                for(int i=0; i<n; i++){
                    pq.add(nums[i]);
                }
                int res = 0;
                for(int i=0; i<k; i++){
                    res = pq.poll();
                }
                return res;
            }
        }
        */

    }
}