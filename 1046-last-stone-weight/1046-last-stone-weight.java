class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int stone:stones){
            pq.add(stone);
        }
        while(pq.size()>1){
            int l = pq.poll();
            int l1 = pq.poll();
            if(l!=l1){
                pq.add(l-l1);
            }
        }
        return pq.isEmpty()?0:pq.poll();
    }
}