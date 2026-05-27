class Solution {
    public int findJudge(int n, int[][] trust) {
        HashMap<Integer,Integer> out = new HashMap<>();
        HashMap<Integer,Integer> in = new HashMap<>();
        for(int t[]:trust){
            int a = t[0];
            int b = t[1];
            out.put(a,out.getOrDefault(a,0)+1);
            in.put(b,in.getOrDefault(b,0)+1);
        }
        for(int i=1; i<=n; i++){
            int outd = out.getOrDefault(i,0);
            int ind = in.getOrDefault(i,0);
            if(outd==0 && ind == n-1){
                return i;
            }
        }
        return -1;
    }
}