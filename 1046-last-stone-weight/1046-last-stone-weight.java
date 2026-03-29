class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int n = stones.length;
        for(int i=0; i<n; i++){
            pq.add(stones[i]);
        }
        while(pq.size()>1){
            int l1 = pq.poll();
            int l2 = pq.poll();
            if(l1!=l2){
                pq.add(l1-l2);
            }
        }
        return pq.isEmpty()?0:pq.poll();
    }
}