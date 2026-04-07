class Solution {
    public String frequencySort(String s) {
        HashMap<Character,Integer> hm = new HashMap<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->(b.v-a.v));
        int n = s.length()-1;
        for(int i=0; i<=n; i++){
            char ch = s.charAt(i);
            hm.put(ch,hm.getOrDefault(ch,0)+1);
        }
        for(char ch:hm.keySet()){
            pq.add(new Pair(ch,hm.get(ch)));
        }
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            int li = pq.peek().v;
            while(li>0){
                sb.append(pq.peek().ch);
                li--;
            }
            pq.poll();
        }
        return sb.toString();
    }
    class Pair{
        char ch;
        int v;
        Pair(char ch,int v){
            this.ch=ch;
            this.v=v;
        }
    }
}