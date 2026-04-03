class Solution {
    public int[][] kClosest(int[][] points, int k) {
        int n = points.length;
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->(a.dis-b.dis));
        for(int i=0; i<n; i++){
            int[] point = points[i];
            int x = point[0];
            int y = point[1];
            int dis = x*x+y*y;
            pq.add(new Pair(dis,i));
        }
        int[][] res = new int[k][2];
        for(int j=0; j<k; j++){
            res[j] = points[pq.poll().idx];
        }
        return res;
    }
    class Pair{
        int dis;
        int idx;
        Pair(int dis, int idx){
            this.dis = dis;
            this.idx = idx;
        }
    }
}

/*
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
*/