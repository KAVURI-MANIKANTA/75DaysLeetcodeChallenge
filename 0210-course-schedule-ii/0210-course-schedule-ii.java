class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0; i<numCourses; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] neighbor:prerequisites){
           adj.get(neighbor[1]).add(neighbor[0]);
        }
        int[] inDigrees = new int[numCourses];
        for(int i=0; i<numCourses; i++){
            for(int neighbor:adj.get(i)){
                inDigrees[neighbor]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<numCourses; i++){
            if(inDigrees[i]==0){
                q.add(i);
            }
        }
        int[] result = new int[numCourses];
        int k=0;
        while(!q.isEmpty()){
            int curr = q.poll();
            result[k]= curr;
            k++;
            for(int neighbor:adj.get(curr)){
                inDigrees[neighbor]--;
                if(inDigrees[neighbor]==0){
                    q.add(neighbor);
                }
            }
        }
        if(k!=numCourses) return new int[0];
        return result;
    }
}