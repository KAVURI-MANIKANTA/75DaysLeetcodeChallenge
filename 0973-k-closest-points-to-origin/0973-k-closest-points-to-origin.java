class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->(dis(b)-dis(a)));
        for(int[] point:points){
            pq.offer(point);
            if(pq.size()>k){
                pq.poll();
            }
        }
        int[][] res = new int[k][2];
        int i=0;
        while(pq.size()>0){
            res[i++] = pq.poll();
        }
        return res;
    }
    public int dis(int[] point){
        return point[0]*point[0]+point[1]*point[1];
    }
}