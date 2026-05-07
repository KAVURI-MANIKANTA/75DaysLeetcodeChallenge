class Solution {
    public int totalFruit(int[] fruits) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        int l=0;
        int n=fruits.length;
        int max = 0;
        for(int r=0; r<n; r++){
            int inc = fruits[r];
            hm.put(inc,hm.getOrDefault(inc,0)+1);
            while(hm.size()>2){
                int dec = fruits[l];
                hm.put(dec,hm.get(dec)-1);
                if(hm.get(dec)==0){
                    hm.remove(dec);
                }
                l++;
            }
            max = Math.max(max,r-l+1);
        }
        return max;
    }
}