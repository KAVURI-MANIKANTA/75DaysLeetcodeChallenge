class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->(b-a));
        int r = matrix.length;
        int c = matrix[0].length;
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                pq.add(matrix[i][j]);
                if(pq.size()>k) pq.poll();
            }
        }
        return pq.poll();
    }
}