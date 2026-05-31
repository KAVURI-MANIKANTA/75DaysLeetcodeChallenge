class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adjc = new ArrayList<>();
        for(int i=0; i<numCourses; i++){
            adjc.add(new ArrayList<>());
        }
        for(int i=0; i<numCourses; i++){
            for(int[] e:prerequisites){
                adjc.get(e[1]).add(e[0]);
            }
        }
        int[] inD = new int[numCourses];
        for(int i=0; i<numCourses; i++){
            for(int neighbor:adjc.get(i)){
                inD[neighbor]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<numCourses; i++){
            if(inD[i]==0){
                q.add(i);
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        while(!q.isEmpty()){
            int curr = q.poll();
            result.add(curr);
            for(int neighbor:adjc.get(curr)){
                inD[neighbor]--;
                if(inD[neighbor]==0){
                    q.add(neighbor);
                }
            }
        }
        if(result.size()==numCourses) return true;
        return false;
    }
}