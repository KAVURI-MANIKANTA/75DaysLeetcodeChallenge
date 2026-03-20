class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i=0; i<numCourses; i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0; i<numCourses; i++){
            for(int[] e:prerequisites){
                adj.get(e[1]).add(e[0]);
            }
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
        ArrayList<Integer> result = new ArrayList<>();
        while(!q.isEmpty()){
            int curr = q.poll();
            result.add(curr);
            for(int neighbor:adj.get(curr)){
                inDigrees[neighbor]--;
                if(inDigrees[neighbor]==0){
                    q.add(neighbor);
                }
            }
        }
        if(result.size()==numCourses) return true;
        return false;
    }
}