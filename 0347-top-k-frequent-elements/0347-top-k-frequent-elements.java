class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        int n = nums.length;
        for(int i=0; i<n; i++){
            hm.put(nums[i],hm.getOrDefault(nums[i],0)+1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->hm.get(b)-hm.get(a));
        pq.addAll(hm.keySet());
        int[] res = new int[k];
        for(int i=0; i<k; i++){
            res[i] = pq.poll();
        }
        return res;
        /*
        HashMap<Integer,Integer> hm = new HashMap<>();
        int n = nums.length;
        for(int i=0; i<n; i++){
            hm.put(nums[i],hm.getOrDefault(nums[i],0)+1);
        }
        List<Integer>[] sorthm = new List[n+1];
        for(int key: hm.keySet()){
            int freq = hm.get(key);
            if(sorthm[freq]==null){
                sorthm[freq]= new ArrayList<>();
            }
            sorthm[freq].add(key);
        }
        int[] res = new int[k];
        int count = 0;
        for(int i=sorthm.length-1; i>=0 && count<k; i--){
            if(sorthm[i]!=null){
                for(Integer integer:sorthm[i]){
                    res[count++] = integer;
                    if(count==k) return res;
                }
            }
        }
        return res;
        */
    }
}